# FiveM Lua Completion

**FiveM Lua Completion** is a JetBrains plugin for IntelliJ / Rider that adds autocompletion, documentation, and contextual info for **FiveM Lua natives**.

> Works alongside [EmmyLua](https://plugins.jetbrains.com/plugin/9768-emmylua) for full Lua syntax support.

---

## ✨ Features

- ✅ Autocompletion for all **FiveM natives** (e.g. `GetPlayerPed`, `TriggerServerEvent`, etc.)
- 📄 Inline documentation when available
- 🔁 Dynamically loaded from the [official FiveM natives documentation](https://github.com/citizenfx/natives)
- 🚀 Compatible with **Rider**, **IntelliJ IDEA**, **CLion**, etc.

---

## 📦 Manual Local Installation

### 1. Clone the repository

```bash
git clone https://github.com/Grainbox/FiveM-Lua-Completion.git
cd FiveM-Lua-Completion
```

### 2. Requirements

- Java JDK **17** (or 21, if supported by your setup)
- IntelliJ **Ultimate** or Rider
- [EmmyLua plugin](https://plugins.jetbrains.com/plugin/9768-emmylua)
- Gradle (no need to install globally, `./gradlew` is provided)

---

## 🔨 Building the Plugin

### On Linux / macOS:

```bash
./gradlew buildPlugin
```

### On Windows (PowerShell):

```powershell
$env:JAVA_HOME = "C:\Users\yourname\.jdks\openjdk-17"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
./gradlew buildPlugin
```

👉 The plugin `.zip` will be generated at:

```
build/distributions/FiveM-Natives-*.zip
```

---

## 📥 Installing in JetBrains IDE

1. Open your JetBrains IDE (Rider, IntelliJ, etc.)
2. Go to `Settings > Plugins`
3. Click ⚙️ > `Install Plugin from Disk`
4. Select the `.zip` file you just built
5. Restart the IDE

---

## ⚠️ Limitations

- The plugin currently loads raw native definitions (not exact Lua syntax).
- Not all natives have descriptions or structured parameters yet.
- Natives are loaded from `*.md` files in resources; JAR-based loading is not implemented yet.

---

## 🔧 Roadmap

- [X] Transform C-style signatures into valid Lua
- [ ] Show tooltips with native parameters
- [ ] Add quick links to native documentation
- [ ] Optional plugin configuration panel
- [ ] EmmyLua type inference integration

---

## 👨‍💻 Author

Developed by [Grainbox](https://github.com/Grainbox)  
License: MIT
