package ch.lightspots.it.web.pages.index

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState


val images = arrayOf(
    "images/slider/slider_image_01.jpg",
    "images/slider/slider_image_02.jpg",
    "images/slider/slider_image_03.jpg")

class IndexComponent : RComponent<RProps, RState>() {

  override fun RBuilder.render() {
    slider(images)
  }

}