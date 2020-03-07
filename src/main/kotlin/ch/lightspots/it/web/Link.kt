package ch.lightspots.it.web

import ch.lightspots.it.web.pages.area.AreaComponent
import ch.lightspots.it.web.pages.contact.ContactComponent
import ch.lightspots.it.web.pages.halls.HallsComponent
import ch.lightspots.it.web.pages.index.IndexComponent
import ch.lightspots.it.web.pages.legalnotice.LegalNoticeComponent
import ch.lightspots.it.web.pages.places.PlacesComponent
import ch.lightspots.it.web.pages.privacy.PrivacyComponent
import ch.lightspots.it.web.pages.services.ServicesComponent
import ch.lightspots.it.web.pages.stables.StablesComponent
import react.Component
import kotlin.reflect.KClass

enum class Link(
    val displayName: String,
    val component: KClass<out Component<*, *>>,
    private val linkName: String = displayName.toLowerCase(),
    val headerEntry: Boolean = true) {
  INDEX("Startseite", IndexComponent::class, linkName = "", headerEntry = false),
  STABLES("Stallungen", StablesComponent::class),
  HALLS("Hallen", HallsComponent::class),
  PLACES("Plätze", PlacesComponent::class, linkName = "plaetze"),
  SERVICES("Dienstleistungen", ServicesComponent::class),
  AREA("Gelände", AreaComponent::class, linkName = "gelaende"),
  CONTACT("Kontakt", ContactComponent::class),
  PRIVACY("Datenschutz", PrivacyComponent::class, headerEntry = false),
  LEGAL_NOTICE("Impressum", LegalNoticeComponent::class, headerEntry = false);

  val href: String
    get() = "#/$linkName"

  val route: String
    get() = "/$linkName"
}
