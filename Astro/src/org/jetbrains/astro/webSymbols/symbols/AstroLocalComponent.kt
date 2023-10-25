// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.astro.webSymbols.symbols

import com.intellij.model.Pointer
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.createSmartPointer
import com.intellij.util.containers.Stack
import com.intellij.webSymbols.*
import com.intellij.webSymbols.WebSymbol.Companion.KIND_HTML_ATTRIBUTES
import com.intellij.webSymbols.WebSymbol.Companion.NAMESPACE_HTML
import com.intellij.webSymbols.query.WebSymbolsListSymbolsQueryParams
import com.intellij.webSymbols.query.WebSymbolsNameMatchQueryParams
import org.jetbrains.astro.webSymbols.AstroProximity
import org.jetbrains.astro.webSymbols.AstroQueryConfigurator

class AstroLocalComponent(override val name: String,
                          override val source: PsiElement,
                          override val priority: WebSymbol.Priority = WebSymbol.Priority.HIGH) : PsiSourcedWebSymbol {

  override fun getMatchingSymbols(qualifiedName: WebSymbolQualifiedName,
                                  params: WebSymbolsNameMatchQueryParams,
                                  scope: Stack<WebSymbolsScope>): List<WebSymbol> =
    if (qualifiedName.matches(NAMESPACE_HTML, KIND_HTML_ATTRIBUTES) && name.contains(":"))
      emptyList()
    else
      super.getMatchingSymbols(qualifiedName, params, scope)

  override fun getSymbols(qualifiedKind: WebSymbolQualifiedKind,
                          params: WebSymbolsListSymbolsQueryParams,
                          scope: Stack<WebSymbolsScope>): List<WebSymbolsScope> =
    if (qualifiedKind.matches(NAMESPACE_HTML, KIND_HTML_ATTRIBUTES) && !params.expandPatterns)
      listOf(AstroComponentWildcardAttribute)
    else
      emptyList()

  override val origin: WebSymbolOrigin
    get() = AstroProjectSymbolOrigin

  override val namespace: SymbolNamespace
    get() = NAMESPACE_HTML

  override val kind: SymbolKind
    get() = AstroQueryConfigurator.ASTRO_COMPONENTS.kind

  override val properties: Map<String, Any>
    get() = mapOf(AstroQueryConfigurator.PROP_ASTRO_PROXIMITY to AstroProximity.LOCAL)

  override fun createPointer(): Pointer<out PsiSourcedWebSymbol> {
    val name = name
    val sourcePtr = source.createSmartPointer()
    return Pointer {
      sourcePtr.dereference()?.let { AstroLocalComponent(name, it) }
    }
  }

}