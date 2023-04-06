plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
}

repositories {
    maven("https://maven.pkg.github.com/tuProlog/2p-kt")
    mavenCentral()
}

dependencies {
    implementation("it.unibo.tuprolog", "solve-classic", "0.31.1")
    implementation("it.unibo.tuprolog", "parser-theory", "0.31.1")
}
