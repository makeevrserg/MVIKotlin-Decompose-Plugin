pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "MVIKotlin-IntelliJ-Plugin"

include(":plugin")
include(":core")
