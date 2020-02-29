package ch.lightspots.it.web.pages.halls

import ch.lightspots.it.web.shared.slider
import kotlinx.css.em
import kotlinx.css.marginLeft
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.b
import react.dom.br
import react.dom.div
import react.dom.figure
import react.dom.img
import react.dom.p
import styled.css
import styled.styledSpan

class HallsComponent : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    div {
      reithalle()
      longierHalle()
    }
  }

  private fun RBuilder.reithalle() {
    div("card") {
      div("card-header") {
        p("card-header-title") {
          +"Reithalle"
        }
      }
      div("card-image") {
        figure("image") {
          img(alt = "Reithalle", src = "images/hallen/reithalle/reithalle_Innen_small.jpg") {}
        }
      }
      div("card-content") {
        b {
          +"Grösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"800qm"
        }
        br { }
        b {
          +"Baujahr:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"2004"
        }
        br { }
        b {
          +"Bodenbelag:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"Sand Flies gemisch"
        }
      }
    }
  }

  private fun RBuilder.longierHalle() {
    div("card") {
      div("card-header") {
        p("card-header-title") {
          +"Longier Halle"
        }
      }
      div("card-image") {
        slider(arrayOf(
            "images/hallen/longier_Halle/longierHalle_2_small.jpg",
            "images/hallen/longier_Halle/longierHalle_1_small.jpg"
        ))
      }
      div("card-content") {
        b {
          +"Grösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"Ø 16m"
        }
        br { }
        b {
          +"Baujahr:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"2010"
        }
        br { }
        b {
          +"Bodenbelag:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"Swingground"
        }
      }
    }
  }
}