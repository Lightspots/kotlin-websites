import ch.lightspots.it.web.Website
import react.dom.render
import kotlinx.browser.document

fun main() {
  render(document.getElementById("root")) {
    child(Website::class) {}
  }
}