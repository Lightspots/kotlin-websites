plugins {
  kotlin("js")
}

version = "1.0-SNAPSHOT"

dependencies {
  api(kotlin("stdlib-js"))
  api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}")

  api("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
  //React, React DOM + Wrappers
  api("org.jetbrains:kotlin-react:${Versions.reactKotlin}")
  api("org.jetbrains:kotlin-react-dom:${Versions.reactKotlin}")
  api("org.jetbrains:kotlin-react-router-dom:${Versions.reactRouterKotlin}")
  implementation(npm("react", Versions.react))
  implementation(npm("react-dom", Versions.react))
  implementation(npm("react-router-dom", Versions.reactRouter))

  //Kotlin Styled
  api("org.jetbrains:kotlin-styled:${Versions.styledKotlin}")
  api("org.jetbrains:kotlin-css:${Versions.cssKotlin}")
  implementation(npm("styled-components", Versions.styled))
  implementation(npm("inline-style-prefixer", Versions.inlineStylePrefixer))
}

kotlin {
  js {
    useCommonJs()
    browser()
  }
  sourceSets.all {
    languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
  }
}
