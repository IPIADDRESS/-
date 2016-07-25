package org.intellij.plugins.postcss.usages;

import org.intellij.plugins.postcss.PostCssFixtureTestCase;

public class PostCssFindUsagesTest extends PostCssFixtureTestCase {
  public void testCustomSelectors() {
    assertEquals(2, myFixture.testFindUsages("customSelectors.pcss").size());
  }
}