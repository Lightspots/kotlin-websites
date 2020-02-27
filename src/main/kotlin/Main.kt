import ch.lightspots.it.web.Application
import react.dom.render
import kotlin.browser.document

fun main() {
  render(document.getElementById("root")) {
    child(Application::class) {}
  }
}