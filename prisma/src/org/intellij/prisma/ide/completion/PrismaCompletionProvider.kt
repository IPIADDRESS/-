package org.intellij.prisma.ide.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.ElementPattern
import com.intellij.psi.PsiElement
import org.intellij.prisma.ide.schema.PrismaSchemaDeclaration
import org.intellij.prisma.ide.schema.PrismaSchemaElement
import org.intellij.prisma.ide.schema.PrismaSchemaKind
import org.intellij.prisma.lang.presentation.icon

abstract class PrismaCompletionProvider : CompletionProvider<CompletionParameters>() {
  abstract val pattern: ElementPattern<out PsiElement>

  protected fun createLookupElement(
    label: String,
    schemaElement: PrismaSchemaElement,
    element: PsiElement,
  ): LookupElementBuilder {
    var builder = LookupElementBuilder.create(label)
      .withPsiElement(element)
      .withPrismaInsertHandler(schemaElement.insertHandler)
      .withIcon(schemaElement.icon)
      .withTypeText(schemaElement.type)

    if (schemaElement is PrismaSchemaDeclaration && schemaElement.kind == PrismaSchemaKind.FUNCTION) {
      builder = builder.withTailText("()")
    }

    return builder
  }
}