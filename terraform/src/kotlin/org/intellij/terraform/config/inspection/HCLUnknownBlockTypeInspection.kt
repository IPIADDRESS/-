// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config.inspection

import com.intellij.codeInsight.FileModificationService
import com.intellij.codeInspection.*
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.progress.ProgressIndicatorProvider
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiParserFacade
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.containers.toArray
import org.intellij.terraform.config.TerraformFileType
import org.intellij.terraform.config.actions.TFInitAction
import org.intellij.terraform.config.codeinsight.ModelHelper
import org.intellij.terraform.config.codeinsight.TerraformCompletionUtil
import org.intellij.terraform.config.model.BlockType
import org.intellij.terraform.config.patterns.TerraformPatterns
import org.intellij.terraform.hcl.HCLBundle
import org.intellij.terraform.hcl.psi.*

class HCLUnknownBlockTypeInspection : LocalInspectionTool() {

  override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
    val ft = holder.file.fileType
    if (ft != TerraformFileType) {
      return PsiElementVisitor.EMPTY_VISITOR
    }

    return MyEV(holder)
  }

  inner class MyEV(val holder: ProblemsHolder) : HCLElementVisitor() {
    override fun visitBlock(block: HCLBlock) {
      ProgressIndicatorProvider.checkCanceled()
      val type = block.getNameElementUnquoted(0) ?: return
      doCheck(block, holder, type)
    }
  }

  private fun doCheck(block: HCLBlock, holder: ProblemsHolder, type: String) {
    if (type.isEmpty()) return
    // It could be root block OR block inside Object.
    // Object could be value of some property or right part of other object
    val parent = PsiTreeUtil.getParentOfType(block, HCLBlock::class.java, HCLProperty::class.java, HCLFile::class.java) ?: return
    ProgressIndicatorProvider.checkCanceled()
    when (parent) {
      is HCLFile -> {
        if (TerraformCompletionUtil.RootBlockKeywords.contains(type)) return
        holder.registerProblem(block.nameElements.first(),
                               HCLBundle.message("unknown.block.type.inspection.unknown.block.type.error.message", type),
                               ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                               *listOfNotNull(TFInitAction.createQuickFixNotInitialized(block)).toArray(LocalQuickFix.EMPTY_ARRAY))
      }
      is HCLBlock -> {
        parent.getNameElementUnquoted(0) ?: return
        parent.`object` ?: return
        if (TerraformPatterns.DynamicBlock.accepts(block)) return
        if (TerraformPatterns.DynamicBlockContent.accepts(block)) return
        val properties = ModelHelper.getBlockProperties(parent)
        // TODO: (?) For some reason single name block could be represented as 'property' in model
        if (properties[type] is BlockType) return

        // Check for non-closed root block (issue #93)
        if (TerraformPatterns.RootBlock.accepts(parent) && TerraformCompletionUtil.RootBlockKeywords.contains(type)) {
          holder.registerProblem(block.nameElements.first(),
                                 HCLBundle.message("unknown.block.type.inspection.missing.closing.brace.error.message"),
                                 ProblemHighlightType.GENERIC_ERROR, AddClosingBraceFix(block.nameElements.first()))
          return
        }

        holder.registerProblem(block.nameElements.first(),
                               HCLBundle.message("unknown.block.type.inspection.unknown.block.type.error.message", type),
                               ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                               *listOfNotNull(TFInitAction.createQuickFixNotInitialized(block)).toArray(LocalQuickFix.EMPTY_ARRAY))
      }
      is HCLProperty -> {
        // TODO: Add some logic
      }
      else -> return
    }
    // TODO: Add 'Register as known block type' quick fix
  }
}

class AddClosingBraceFix(before: PsiElement) : LocalQuickFixAndIntentionActionOnPsiElement(before) {
  override fun getText(): String {
    return HCLBundle.message("unknown.block.type.inspection.add.closing.brace.quick.fix.test")
  }

  override fun getFamilyName(): String {
    return text
  }

  override fun startInWriteAction(): Boolean {
    return false
  }

  override fun invoke(project: Project, file: PsiFile, editor: Editor?, startElement: PsiElement, endElement: PsiElement) {
    if (!FileModificationService.getInstance().prepareFileForWrite(file)) return
    WriteCommandAction.writeCommandAction(project).run<Throwable> {
      CodeStyleManager.getInstance(project).performActionWithFormatterDisabled {
        if (editor != null) {
          editor.document.insertString(startElement.node.startOffset, "}\n")
        }
        else {
          startElement.parent.addBefore(HCLElementGenerator(project).createObject("").lastChild, startElement)
          startElement.parent.addBefore(PsiParserFacade.getInstance(project).createWhiteSpaceFromText("\n"), startElement)
          file.subtreeChanged()
        }
      }
    }
  }
}