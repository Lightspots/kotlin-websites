plugins {
  id("org.jetbrains.kotlin.js") version "1.3.72" apply false
}

subprojects {
  group = "ch.lightspots.it.web"

  repositories {
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap/")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers")
    maven("https://kotlin.bintray.com/kotlinx")
  }
}
