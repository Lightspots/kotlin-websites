package ch.lightspots.it.web

import kotlinx.css.em
import kotlinx.css.margin
import kotlinx.css.marginLeft
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.a
import react.dom.div
import react.dom.footer
import react.dom.p
import styled.css
import styled.styledA
import styled.styledSpan
import kotlin.js.Date

class Footer : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    footer("footer") {
      div("level") {
        div("level-left") {
          div("level-item") {
            p {
              +"© 2014 - ${Date().getFullYear()} Stefan Ebner - All rights reserved, developed by"
              styledA(href = "https://lightspots.ch", target = "_blank") {
                css {
                  marginLeft = 0.25.em
                }
                +"Lightspots GmbH"
              }
            }
          }
        }
        div("level-right") {
          a(href = Link.PRIVACY.href) {
            +"Datenschutz"
          }
          styledSpan {
            css {
              margin(horizontal = 0.25.em)
            }
            +"-"
          }
          a(href = Link.LEGAL_NOTICE.href) {
            +" Impressum"
          }
        }
      }
    }
  }
}

fun RBuilder.footer() = child(Footer::class) {}