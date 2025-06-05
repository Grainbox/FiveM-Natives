package com.github.grainbox.fivemnatives.startup

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.github.grainbox.fivemnatives.services.NativeRegistry
import com.github.grainbox.fivemnatives.services.NativeParser

class PluginStartupService : ProjectActivity {

    override suspend fun execute(project: Project) {
        val indexStream = javaClass.getResourceAsStream("/fivem-lls-addon/library/index.txt")
        val paths = indexStream?.bufferedReader()?.readLines() ?: emptyList()

        for (relativePath in paths) {
            val stream = javaClass.getResourceAsStream("/fivem-lls-addon/library/$relativePath")
            if (stream != null) {
                val content = stream.bufferedReader().readText()
                NativeParser.parse(content)?.let {
                    for (native in it) {
                        NativeRegistry.register(native)
                    }
                }
            } else {
                thisLogger().warn("❌ Failed to load: $relativePath")
            }
        }
    }
}