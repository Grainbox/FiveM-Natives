package com.github.grainbox.fivemnatives

import com.github.grainbox.fivemnatives.services.NativeRegistry
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.tang.intellij.lua.lang.LuaLanguage

class FiveMCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(LuaLanguage.INSTANCE),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    for (native in NativeRegistry.getAll()) {
                        result.addElement(
                            LookupElementBuilder.create(native.name)
                                .withTypeText(native.signature)
                                .withTailText(" - ${native.description.take(80)}", true)
                                .withPresentableText(native.name)
                                .withBoldness(true)
                        )
                    }
                }
            }
        )
    }
}