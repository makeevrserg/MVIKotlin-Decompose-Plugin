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
}

intellij {
    version.set("2022.2.5")
    type.set("IC")
}
