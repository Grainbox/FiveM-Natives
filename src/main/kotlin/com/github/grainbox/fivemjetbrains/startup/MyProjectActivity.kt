package com.github.grainbox.fivemjetbrains.startup

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.github.grainbox.fivemjetbrains.services.NativeRegistry
import com.github.grainbox.fivemjetbrains.services.NativeParser

class PluginStartupService : ProjectActivity {

    override suspend fun execute(project: Project) {
        val indexStream = javaClass.getResourceAsStream("/natives/index.txt")
        val paths = indexStream?.bufferedReader()?.readLines() ?: emptyList()

        for (relativePath in paths) {
            val stream = javaClass.getResourceAsStream("/natives/$relativePath")
            if (stream != null) {
                val lines = stream.bufferedReader().readLines()
                val namespace = relativePath.substringBefore('/')
                NativeParser.parse(lines, namespace, relativePath.substringAfterLast('/'))?.let {
                    NativeRegistry.register(it)
                }
            } else {
                thisLogger().warn("❌ Failed to load: $relativePath")
            }
        }
    }
}