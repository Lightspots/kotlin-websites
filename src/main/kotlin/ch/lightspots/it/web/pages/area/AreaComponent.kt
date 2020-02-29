package ch.lightspots.it.web.pages.area

import kotlinx.css.em
import kotlinx.css.marginLeft
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.b
import react.dom.br
import react.dom.figure
import react.dom.h1
import react.dom.img
import react.dom.p
import styled.css
import styled.styledSpan

class AreaComponent : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    h1("title is-1") {
      +"Gelände"
    }
    figure("image") {
      img(alt = "Luftaufnahme", src = "images/gelaende/LuftAufnahme_small.jpg") {}
    }
    p {
      b {
        +"Höhenlage:"
      }
      styledSpan {
        css {
          marginLeft = 0.25.em
        }
        +"360-380m über NN"
      }
      br { }
      b {
        +"Gesamtfläche:"
      }
      styledSpan {
        css {
          marginLeft = 0.25.em
        }
        +"80ha"
      }
      br { }
      b {
        +"Weideland:"
      }
      styledSpan {
        css {
          marginLeft = 0.25.em
        }
        +"20ha"
      }
    }
  }
}