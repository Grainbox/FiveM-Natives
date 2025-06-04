package com.github.grainbox.fivemjetbrains.services

object NativeParser {
    fun parse(lines: List<String>, namespace: String, fileName: String): FivemNative? {
        val rawSignature = lines.indexOfFirst { it.trim() == "```c" }
            .takeIf { it != -1 }
            ?.let { lines.getOrNull(it + 1)?.trim() } ?: ""

        val description = lines.indexOfFirst { it.contains("**Description**") }
            .takeIf { it != -1 }
            ?.let { start ->
                lines.drop(start + 1).takeWhile { it.trim().isNotBlank() }.joinToString("\n").trim()
            } ?: ""

        val functionName = fileName.removeSuffix(".md")

        val docUrl = "https://github.com/citizenfx/natives/blob/master/$namespace/$fileName"

        return FivemNative(
            name = functionName,
            signature = rawSignature,
            description = description,
            namespace = namespace,
            docUrl = docUrl
        )
    }
}