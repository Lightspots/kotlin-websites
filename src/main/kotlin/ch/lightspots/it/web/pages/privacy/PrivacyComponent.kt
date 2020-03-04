package ch.lightspots.it.web.pages.privacy

import kotlinx.css.em
import kotlinx.css.marginTop
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.h1
import styled.css
import styled.styledP

class PrivacyComponent : RPureComponent<RProps, RState>() {

  override fun RBuilder.render() {
    h1("title is-1") {
      +"Datenschutz"
    }
    styledP {
      +"Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener Daten möglich. Soweit auf unseren Seiten personenbezogene Daten (beispielsweise Name, Anschrift oder eMail-Adressen) erhoben werden, erfolgt dies, soweit möglich, stets auf freiwilliger Basis. Diese Daten werden ohne Ihre ausdrückliche Zustimmung nicht an Dritte weitergegeben."
    }
    styledP {
      css {
        marginTop = 0.5.em
      }
      +"Wir weisen darauf hin, dass die Datenübertragung im Internet (z.B. bei der Kommunikation per E-Mail) Sicherheitslücken aufweisen kann. Ein lückenloser Schutz der Daten vor dem Zugriff durch Dritte ist nicht möglich."
    }
    styledP {
      css {
        marginTop = 0.5.em
      }
      +"Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten durch Dritte zur Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien wird hiermit ausdrücklich widersprochen. Die Betreiber der Seiten behalten sich ausdrücklich rechtliche Schritte im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor."
    }
  }
}