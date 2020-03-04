package ch.lightspots.it.web.pages.services

import kotlinx.css.em
import kotlinx.css.marginTop
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.h1
import react.dom.p
import styled.css
import styled.styledH3

class ServicesComponent : RPureComponent<RProps, RState>() {

  override fun RBuilder.render() {
    h1("title is-1") {
      +"Dienstleistungen"
    }
    styledH3 {
      css {
        +"title is-3"
      }
      +"Fütterung"
    }
    p {
      +"Das Grundfutter besteht wahlweise aus Heu, Silage, Pellets (derby Standart) oder ganzem Hafer."
    }
    p {
      +"Gefüttert wird Morgens und Abends. Selbstverständlich besteht die Möglichkeit auch eigenes Kraftfutter (Müsli) zu verabreichen."
    }
    styledH3 {
      css {
        +"title is-3"
        marginTop = 1.em
      }
      +"Einstreu"
    }
    p {
      +"Als Einstreu stellen wir Stroh und Sägespäne zur Verfügung."
    }
    styledH3 {
      css {
        +"title is-3"
        marginTop = 1.em
      }
      +"Weidegang"
    }
    p {
      +"Zu jeder Box oder offen Stall gehört eine Weide, es besteht aber auch die Möglichkeit die Pferde bei Verträglichkeit zusammenzustellen. Wir bringen Ihre Pferde je nach Wunsch halbtags oder ev. Stundenweise auf die Weide, je nach Vereinbarung. Die Weidedienstleistung wird nur zwischen Mitte April und Anfang November angeboten, je nach Witterung."
    }
  }
}