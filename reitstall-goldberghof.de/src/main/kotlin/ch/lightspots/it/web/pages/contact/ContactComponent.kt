package ch.lightspots.it.web.pages.contact

import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.*

class ContactComponent : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    h1("title is-1") {
      +"Kontakt"
    }
    map()
    div("level") {
      div("level-left") {
        div {
          h3("title is-3") {
            +"Adresse"
          }
          p {
            +"Stefan Ebner"
            br { }
            +"Waidweg 15"
            br { }
            +"79725 Laufenburg"
          }
        }
      }
      div("level-right") {
        div {
          h3("title is-3") {
            +"Kontaktmöglichkeiten"
          }
          p {
            +"Telefon: 0170 1133540"
            br { }
            +"E-Mail: info[at]reitstall-goldberghof.de"
          }
        }
      }
    }
  }

  private fun RBuilder.map() {
    iframe {
      attrs {
        width = "100%"
        height = "400px"
        src = "https://maps.google.com/maps?q=Waidweg%2015%2C%2079725%20Laufenburg&t=&z=15&ie=UTF8&iwloc=&output=embed"
      }
    }
  }
}