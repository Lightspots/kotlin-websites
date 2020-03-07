package ch.lightspots.it.web.pages.stables

import ch.lightspots.it.web.shared.slider
import react.RBuilder
import react.RHandler
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.div
import react.dom.figure
import react.dom.img
import react.dom.p

interface StableCardProps : RProps {
  var title: String
  var images: Array<String>
}

class StableCard(props: StableCardProps) : RPureComponent<StableCardProps, RState>(props) {

  override fun RBuilder.render() {
    div("card") {
      div("card-header") {
        p("card-header-title") {
          +props.title
        }
      }
      div("card-image") {
        images()
      }
      div("card-content") {
        props.children()
      }
    }
  }

  private fun RBuilder.images() {
    if (props.images.size == 1) {
      figure("image") {
        img(alt = "Stall Bild", src = props.images[0]) {}
      }
    } else {
      slider(props.images)
    }
  }
}

fun RBuilder.stableCard(title: String, images: Array<String>, handler: RHandler<StableCardProps>) = child(
    StableCard::class) {
  attrs {
    this.title = title
    this.images = images
  }
  handler()
}