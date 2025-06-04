package com.github.grainbox.fivemnatives.services

object NativeRegistry {
    private val natives = mutableMapOf<String, FivemNative>()

    fun register(native: FivemNative) {
        natives[native.name] = native
        println("✅ Loaded native: ${native.name}")
    }

    fun getAll(): Collection<FivemNative> = natives.values
    fun find(name: String): FivemNative? = natives[name]
}
