package ch.lightspots.it.web

import ch.lightspots.it.web.pages.NotFoundComponent
import ch.lightspots.it.web.pages.area.AreaComponent
import ch.lightspots.it.web.pages.index.IndexComponent
import ch.lightspots.it.web.pages.services.ServicesComponent
import react.Component
import kotlin.reflect.KClass

enum class Link(
    val displayName: String,
    val component: KClass<out Component<*, *>>,
    private val linkName: String = displayName.toLowerCase(),
    val headerEntry: Boolean = true) {
  INDEX("Startseite", IndexComponent::class, linkName = "", headerEntry = false),
  STABLES("Stallungen", NotFoundComponent::class),
  HALLS("Hallen", NotFoundComponent::class),
  PLACES("Plätze", NotFoundComponent::class, linkName = "plaetze"),
  SERVICES("Dienstleistungen", ServicesComponent::class),
  AREA("Gelände", AreaComponent::class, linkName = "gelaende"),
  CONTACT("Kontakt", NotFoundComponent::class),
  PRIVACY("Datenschutz", NotFoundComponent::class, headerEntry = false),
  LEGAL_NOTICE("Impressum", NotFoundComponent::class, headerEntry = false);

  val href: String
    get() = "#/$linkName"

  val route: String
    get() = "/$linkName"
}
