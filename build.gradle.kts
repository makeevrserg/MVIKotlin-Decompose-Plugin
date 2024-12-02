buildscript {
    dependencies {
        classpath("ru.astrainteractive.gradleplugin:convention:1.4.0")
        classpath("ru.astrainteractive.gradleplugin:minecraft:1.4.0")
    }
}

plugins {
    java
    `java-library`
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.intellij) apply false
}

apply(plugin = "ru.astrainteractive.gradleplugin.dokka.root")
apply(plugin = "ru.astrainteractive.gradleplugin.detekt")
apply(plugin = "ru.astrainteractive.gradleplugin.root.info")

subprojects.forEach {
    it.apply(plugin = "ru.astrainteractive.gradleplugin.dokka.module")
    it.plugins.withId("org.jetbrains.kotlin.jvm") {
        it.apply(plugin = "ru.astrainteractive.gradleplugin.java.core")
    }
}
