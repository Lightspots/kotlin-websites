plugins {
  id("org.jetbrains.kotlin.js") version "1.3.70"
}

group = "ch.lightspots.it.web"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven("https://dl.bintray.com/kotlin/kotlin-eap/")
  maven("https://kotlin.bintray.com/kotlin-js-wrappers")
  maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3-1.3.70-eap-42")

  //React, React DOM + Wrappers
  implementation("org.jetbrains:kotlin-react:16.13.0-pre.92-kotlin-1.3.61")
  implementation("org.jetbrains:kotlin-react-dom:16.13.0-pre.92-kotlin-1.3.61")
  implementation("org.jetbrains:kotlin-react-router-dom:4.3.1-pre.92-kotlin-1.3.61")
  implementation(npm("react", "16.13.0"))
  implementation(npm("react-dom", "16.13.0"))
  implementation(npm("react-router-dom", "5.1.2"))

  //Kotlin Styled
  implementation("org.jetbrains:kotlin-styled:1.0.0-pre.92-kotlin-1.3.61")
  implementation(npm("styled-components"))
  implementation(npm("inline-style-prefixer"))
}

kotlin {
  target {
    useCommonJs()
    browser {
    }
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack> {
  devServer = devServer?.copy(port = 8888)
}

afterEvaluate {
  val content = """
    |registry=https://registry.npmjs.org
    |always-auth=false
    |
  """.trimMargin()
  val file = File(project.buildDir, "js/.npmrc")
  file.writeText(content)
}
