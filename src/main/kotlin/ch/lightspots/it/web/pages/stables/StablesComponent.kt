package ch.lightspots.it.web.pages.stables

import kotlinx.css.em
import kotlinx.css.marginLeft
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.b
import react.dom.br
import react.dom.div
import react.dom.h1
import react.dom.p
import styled.css
import styled.styledSpan

class StablesComponent : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    div {
      h1("title is-1") {
        +"Stallungen"
      }
      stableCard(
          title = "Paddockboxen an der Reithalle",
          images = arrayOf("images/stallungen/PaddockBoxen_Halle_1_small.jpg",
              "images/stallungen/PaddockBoxen_Halle_2_small.jpg")) {
        b {
          +"Boxengrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"12qm"
        }
        br { }
        b {
          +"Paddockgrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"21qm"
        }
      }
      stableCard(
          title = "Paddockboxen im ehemaligen Kuhstall",
          images = arrayOf("images/stallungen/PaddockBoxen_kuh_1_small.jpg",
              "images/stallungen/PaddockBoxen_kuh_2_small.jpg")) {
        b {
          +"Boxengrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"bis 16qm"
        }
        br { }
        b {
          +"Paddockgrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"bis 18qm"
        }
      }
      stableCard(
          title = "Aussenboxen mit Paddock",
          images = arrayOf("images/stallungen/AussenBoxen_Paddock_1_small.jpg")) {
        b {
          +"Boxengrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"13qm"
        }
        br { }
        b {
          +"Paddockgrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"15qm"
        }
      }
      stableCard(
          title = "Paddockboxen mit Überdachung",
          images = arrayOf("images/stallungen/PaddockBoxen_Ueberachung_1_small.jpg",
              "images/stallungen/PaddockBoxen_Ueberachung_2_small.jpg")) {
        b {
          +"Boxengrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"12qm"
        }
        br { }
        b {
          +"Paddockgrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"15qm"
        }
      }
      stableCard(
          title = "Innenboxen",
          images = arrayOf("images/stallungen/Innenboxen_small.jpg")) {
        p {
          +"Mit seperatem Winterpaddock"
        }
        br { }
        b {
          +"Boxengrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"12qm"
        }
        br { }
        b {
          +"Paddockgrösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"ca. 50qm"
        }
      }
      stableCard(
          title = "Offenstallungen für 2 Pferde mit dazugehörigem Auslauf",
          images = arrayOf("images/stallungen/aussenstallungen_small.jpg")) {
        b {
          +"Grösse:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"32qm"
        }
      }
      stableCard(
          title = "Laufstallungen für 2 Pferde am Reitplatz mit Teilüberdachung",
          images = arrayOf("images/stallungen/Laufstallungen_1_small.jpg")) {
        p {
          +"Mit seperatem Winterpaddock"
        }
        br { }
        b {
          +"Überdachtefläche:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"50qm"
        }
        br { }
        b {
          +"Auslauf:"
        }
        styledSpan {
          css {
            marginLeft = 0.25.em
          }
          +"84qm"
        }
      }
    }
  }
}