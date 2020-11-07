plugins {
  kotlin("js")
}

version = "1.0-SNAPSHOT"

dependencies {
  implementation(project(":shared"))

  //React, React DOM + Wrappers
  implementation(npm("react", Versions.react))
  implementation(npm("react-dom", Versions.react))
  implementation(npm("react-router-dom", Versions.reactRouter))

  //Kotlin Styled
  implementation(npm("styled-components", Versions.styled))
  implementation(npm("inline-style-prefixer", Versions.inlineStylePrefixer))
}

kotlin {
  js {
    useCommonJs()
    // To build distributions and run tests for browser or Node.js use one or both of:
    browser()
    binaries.executable()
  }
  sourceSets.all {
    languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack> {
  devServer = devServer?.copy(port = 8888)
}
