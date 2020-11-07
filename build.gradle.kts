plugins {
  id("org.jetbrains.kotlin.js") version "1.4.10" apply false
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

afterEvaluate {
  val content = """
     |registry=https://registry.npmjs.org
     |always-auth=false
     |
   """.trimMargin()
  File(project.buildDir, "js").mkdirs()
  val file = File(project.buildDir, "js/.npmrc")
  file.writeText(content)
}
