package ch.lightspots.it.web.pages.index

import ch.lightspots.it.web.slider.slider
import kotlinx.html.IFRAME
import kotlinx.html.attributes.StringAttribute
import kotlinx.html.attributes.TickerAttribute
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*


val images = arrayOf(
    "images/slider/slider_image_01.jpg",
    "images/slider/slider_image_02.jpg",
    "images/slider/slider_image_03.jpg")

class IndexComponent : RComponent<RProps, RState>() {

  override fun RBuilder.render() {
    slider(images)
    div {
      h1("title is-1") {
        +"Reitstall Goldberghof"
      }
      p {
        +"Wie der Name bereits verlauten lässt, liegt der ehemalige Kuhmilch-Bauernhof auf einem kleinen Berg im Süden Deutschlands."
      }
      p {
        +"Als Familienbetrieb geben wir unser Bestes, um Ihnen und Ihren Pferden das bestmögliche zu bieten."
      }
      div("video-container") {
        // <iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/QaO0c1SfxY8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        // <iframe width="560" height="315" src="https://www.youtube.com/embed/QaO0c1SfxY8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        iframe {
          attrs {
            src = "https://www.youtube-nocookie.com/embed/QaO0c1SfxY8"
            frameborder = "0"
            allow = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen = true
          }
        }
      }
      p {
        +"Unser Hof setzt sich aus diversen unterschiedlichen Pferdeboxen, zwei Reitpätzen, einer Reithalle, sowie einer Longierhalle zusammen"
      }
      overviewMap()
    }
  }

}

var IFRAME.frameborder: String
  get() = StringAttribute()[this, "frameborder"]
  set(newValue) {
    StringAttribute()[this, "frameborder"] = newValue
  }

var IFRAME.allow: String
  get() = StringAttribute()[this, "allow"]
  set(newValue) {
    StringAttribute()[this, "allow"] = newValue
  }

var IFRAME.allowfullscreen: Boolean
  get() = TickerAttribute()[this, "allowfullscreen"]
  set(newValue) {
    TickerAttribute()[this, "allowfullscreen"] = newValue
  }