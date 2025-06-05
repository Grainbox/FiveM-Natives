package com.github.grainbox.fivemnatives.services

object NativeParser {
    fun parse(fileContent: String): List<FivemNative>? {
        val lines = fileContent.lines()
        val natives = mutableListOf<FivemNative>()

        var currentDoc = StringBuilder()
        val currentParams = mutableListOf<Pair<String, String>>()
        var returnType: String? = null

        for (i in lines.indices) {
            val line = lines[i].trim().removePrefix("---").trim()

            when {
                line.startsWith("@param") -> {
                    val match = Regex("""@param (\w+) (\w+)""").find(line)
                    if (match != null) {
                        currentParams.add(match.groupValues[1] to match.groupValues[2])
                    }
                }

                line.startsWith("@return") -> {
                    returnType = line.removePrefix("@return").trim()
                }

                line.startsWith("function ") -> {
                    val match = Regex("""function (\w+)\((.*?)\)""").find(line)
                    if (match != null) {
                        val name = match.groupValues[1]
                        val native = FivemNative(name, currentParams.toList(), returnType, currentDoc.toString().trim())
                        natives.add(native)
                        // reset
                        currentDoc = StringBuilder()
                        currentParams.clear()
                        returnType = null
                    }
                }

                else -> currentDoc.appendLine(line)
            }
        }

        return natives
    }
}