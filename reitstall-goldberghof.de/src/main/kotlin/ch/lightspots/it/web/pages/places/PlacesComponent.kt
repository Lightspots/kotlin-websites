package ch.lightspots.it.web.pages.places

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
import react.dom.h1
import react.dom.img
import react.dom.p
import styled.css
import styled.styledSpan

class PlacesComponent : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    div {
      h1("title is-1") {
        +"Plätze"
      }
      reitplatzMitTeilueberdachung()
      reitplatz()
    }
  }

  private fun RBuilder.reitplatzMitTeilueberdachung() {
    div("card") {
      div("card-header") {
        p("card-header-title") {
          +"Reitplatz mit Teilüberdachung"
        }
      }
      div("card-image") {
        figure("image") {
          img(alt = "Reitplatz mit Teilüberdachung",
              src = "images/hallen/offene_Reithalle/offene_Reithalle_small.jpg") {}
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
          +"innen: 600qm, aussen: 1250qm"
        }
        br { }
        b {
          +"Baujahr:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"2014"
        }
        br { }
        b {
          +"Bodenbelag:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"Nordsand GmbH, Sand Flies gemisch"
        }
      }
    }
  }

  private fun RBuilder.reitplatz() {
    div("card") {
      div("card-header") {
        p("card-header-title") {
          +"Reitplatz"
        }
      }
      div("card-image") {
        figure("image") {
          img(alt = "Reitplatz", src = "images/hallen/reitplatz/reitplatz_small.jpg") {}
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
          +"1999"
        }
        br { }
        b {
          +"Bodenbelag:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"Sand"
        }
        p {
          +"longieren & frei laufen erlaubt"
        }
      }
    }
  }
}