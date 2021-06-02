plugins {
  kotlin("js") version "1.5.10" apply false
}

subprojects {
  group = "ch.lightspots.it.web"

  repositories {
    mavenCentral()
  }
}
