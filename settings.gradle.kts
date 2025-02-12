pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    }
}

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "MVIKotlin-IntelliJ-Plugin"

include(":plugin")
include(":core")
