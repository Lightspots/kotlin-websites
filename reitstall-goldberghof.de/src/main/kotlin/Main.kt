import ch.lightspots.it.web.Website
import react.dom.render
import kotlin.browser.document

fun main() {
  render(document.getElementById("root")) {
    child(Website::class) {}
  }
}