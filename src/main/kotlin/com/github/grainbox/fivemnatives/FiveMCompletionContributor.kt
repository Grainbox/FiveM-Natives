package com.github.grainbox.fivemnatives

import com.github.grainbox.fivemnatives.services.NativeRegistry
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.tang.intellij.lua.lang.LuaLanguage

class FiveMCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(LuaLanguage.INSTANCE),
            object : CompletionProvider<CompletionParameters>() {
//                override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    result: CompletionResultSet
//                ) {
//                    val file = parameters.originalFile
//                    val lang = file.language
//                    thisLogger().warn("🔍 Completion triggered for file: ${file.name}, language: ${lang.id} (${lang.displayName})")
//
//                    thisLogger().warn("NativeRegistry: ${NativeRegistry.getAll().size}")
////                    for (native in NativeRegistry.getAll()) {
////                        ...
////                    }
//                }
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    thisLogger().warn("NativeRegistry: ${NativeRegistry.getAll().size}")
                    for (native in NativeRegistry.getAll()) {
                        val tail = native.params.joinToString { "${it.second} ${it.first}" }
                        thisLogger().warn("Native: ${native.name} | Params: $tail")
                        result.addElement(
                            LookupElementBuilder.create(native.name)
                                .withPresentableText(native.name)
                                .withTypeText(native.returnType ?: "void", true) // Ce qui s'affiche à droite
                                .withTailText("(${native.params.joinToString { "${it.second} ${it.first}" }})", true) // Affiche les paramètres après le nom
                                .withBoldness(true)
                                .withInsertHandler { context, _ ->
                                    val params = native.params.joinToString(", ") { it.first }
                                    val template = "${native.name}($params)"
                                    context.document.replaceString(
                                        context.startOffset,
                                        context.tailOffset,
                                        template
                                    )
                                    context.editor.caretModel.moveToOffset(context.startOffset + template.length)
                                }
                        )
                    }
                }
            }
        )
    }
}