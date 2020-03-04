package ch.lightspots.it.web.pages.legalnotice

import kotlinx.css.em
import kotlinx.css.marginTop
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.br
import react.dom.h1
import react.dom.h3
import react.dom.p
import styled.css
import styled.styledH3

class LegalNoticeComponent : RPureComponent<RProps, RState>() {

  override fun RBuilder.render() {
    h1("title is-1") {
      +"Impressum"
    }
    h3("subtitle is-3") {
      +"Angaben gemäß § 5 TMG:"
    }
    p {
      +"Stefan Ebner"
      br { }
      +"Landwirt"
      br { }
      +"Waidweg 15"
      br { }
      +"79725 Laufenburg"
    }
    styledH3 {
      css {
        +"title is-3"
        marginTop = 1.em
      }
      +"Kontakt"
    }
    p {
      +"Telefon: 01701133540"
      br { }
      +"E-Mail: info[at]reitstall-goldberghof.de"
    }
  }
}