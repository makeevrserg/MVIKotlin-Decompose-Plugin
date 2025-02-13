import ru.astrainteractive.gradleplugin.property.PropertyValue.Companion.gradleProperty
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.requireString

plugins {
    id("java")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.intellij)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.coroutines.swing)
    api(libs.klibs.kstorage)
    api(libs.klibs.mikro.core)
}

intellij {
    version.set(gradleProperty("intellij.version").requireString)
    type.set(gradleProperty("intellij.type").requireString)
}
