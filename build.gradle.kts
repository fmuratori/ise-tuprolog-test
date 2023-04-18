//repositories {
////    maven("https://maven.pkg.github.com/tuProlog/2p-kt")
//}
//
//dependencies {
////    implementation("it.unibo.tuprolog", "solve-classic", "0.31.1")
////    implementation("it.unibo.tuprolog", "parser-theory", "0.31.1")
//}

plugins {
    kotlin("js")
}

repositories {
    mavenCentral() //"https://maven.pkg.github.com/tuProlog/2p-kt"
}

kotlin {
    js {
        useCommonJs()
        compilations.all {
            kotlinOptions {
                main = "noCall"
            }
        }
        browser()
        binaries.executable()
    }

    sourceSets {
        val main by getting {
            dependencies {
//                api(kotlin("stdlib-js"))
//                api(npm("@tuprolog/parser-utils", libs.versions.npm.tuprolog.parserUtils.get()))
                implementation("it.unibo.tuprolog:solve-classic:0.31.1-dev02+0cd8a1ae6")
                implementation("it.unibo.tuprolog:parser-theory:0.31.1-dev02+0cd8a1ae6")
            }
        }
    }
}

dependencies {
    implementation(kotlin("bom"))
    api(kotlin("stdlib-js"))
}