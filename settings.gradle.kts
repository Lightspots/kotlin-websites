pluginManagement {
  repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
    gradlePluginPortal()
  }
}

include("shared")
include("reitstall-goldberghof.de")

rootProject.name = "kotlin-websites"
