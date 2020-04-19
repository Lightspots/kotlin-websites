plugins {
  id("org.jetbrains.kotlin.js")
}

version = "1.0-SNAPSHOT"

dependencies {
  implementation(project(":shared"))

  //React, React DOM + Wrappers
  implementation(npm("react", Versions.react))
  implementation(npm("react-dom", Versions.react))
  implementation(npm("react-router-dom", Versions.reactRouter))

  //Kotlin Styled
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
