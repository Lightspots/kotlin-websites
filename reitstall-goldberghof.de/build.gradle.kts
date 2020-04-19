plugins {
  id("org.jetbrains.kotlin.js")
}

version = "1.0-SNAPSHOT"

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}")

  //React, React DOM + Wrappers
  implementation("org.jetbrains:kotlin-react:${Versions.reactKotlin}")
  implementation("org.jetbrains:kotlin-react-dom:${Versions.reactKotlin}")
  implementation("org.jetbrains:kotlin-react-router-dom:${Versions.reactRouterKotlin}")
  implementation(npm("react", Versions.react))
  implementation(npm("react-dom", Versions.react))
  implementation(npm("react-router-dom", Versions.reactRouter))

  //Kotlin Styled
  implementation("org.jetbrains:kotlin-styled:${Versions.styledKotlin}")
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

// afterEvaluate {
//   val content = """
//     |registry=https://registry.npmjs.org
//     |always-auth=false
//     |
//   """.trimMargin()
//   val file = File(project.buildDir, "js/.npmrc")
//   file.writeText(content)
// }
