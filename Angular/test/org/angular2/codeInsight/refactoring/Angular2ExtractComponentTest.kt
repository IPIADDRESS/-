// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.angular2.codeInsight.refactoring

import com.intellij.refactoring.util.CommonRefactoringUtil.RefactoringErrorHintException
import com.intellij.testFramework.UsefulTestCase
import org.angular2.Angular2TestCase
import org.angular2.Angular2TestModule
import org.angular2.Angular2TsConfigFile

class Angular2ExtractComponentTest : Angular2TestCase("refactoring/extractComponent", true) {

  // TODO WEB-67260 - fix issues with Person being replaced with record type
  fun _testSingleElementMultiLineFromCaret() {
    doMultiFileTest()
  }

  // TODO WEB-67260 - problem with type evaled to any
  fun _testSingleElementSingleLine() {
    doMultiFileTest()
  }

  // TODO WEB-67260 - error
  fun _testMultiElement() {
    doMultiFileTest()
  }

  // TODO WEB-67260 - problem with type evaled to any
  fun _testNoElement() {
    doMultiFileTest()
  }

  // TODO WEB-67260 - problem with type evaled to any
  fun _testNameClashes() {
    doMultiFileTest()
  }

  fun testExtractFromInlineTemplate() {
    doMultiFileTest("src/app/app.component.ts")
  }

  fun testUnsupportedSelection() {
    doFailedTest()
  }

  fun testUnsupportedSelection2() {
    doFailedTest()
  }

  fun testUnsupportedSelection3() {
    doFailedTest()
  }

  fun testUnsupportedSelection4() {
    doFailedTest()
  }

  private fun doMultiFileTest(source: String = "src/app/app.component.html") {
    doConfiguredTest(Angular2TestModule.TS_LIB,
                     Angular2TestModule.ANGULAR_CORE_16_2_8,
                     Angular2TestModule.ANGULAR_COMMON_16_2_8,
                     Angular2TestModule.ANGULAR_FORMS_16_2_8,
                     dir = true, checkResult = true, configureFileName = source,
                     configurators = listOf(Angular2TsConfigFile(strictTemplates = true))) {
      myFixture.performEditorAction("Angular2ExtractComponentAction")
    }
  }

  private fun doFailedTest() {
    UsefulTestCase.assertThrows(RefactoringErrorHintException::class.java) { doMultiFileTest() }
  }

}
