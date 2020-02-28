package ch.lightspots.it.web

import ch.lightspots.it.web.pages.IndexComponent
import ch.lightspots.it.web.pages.NotFoundComponent
import react.Component
import kotlin.reflect.KClass

enum class Links(
    val displayName: String,
    val component: KClass<out Component<*, *>>,
    private val linkName: String = displayName.toLowerCase(),
    val headerEntry: Boolean = true) {
  INDEX("Startseite", IndexComponent::class, linkName = "", headerEntry = false),
  STABLES("Stallungen", NotFoundComponent::class),
  HALLS("Hallen", NotFoundComponent::class),
  PLACES("Plätze", NotFoundComponent::class, linkName = "plaetze"),
  SERVICES("Dienstleistungen", NotFoundComponent::class),
  AREA("Gelände", NotFoundComponent::class, linkName = "gelaende"),
  CONTACT("Kontakt", NotFoundComponent::class),
  PRIVACY("Datenschutz", NotFoundComponent::class, headerEntry = false),
  LEGAL_NOTICE("Impressum", NotFoundComponent::class, headerEntry = false);

  val href: String
    get() = "#/$linkName"

  val route: String
    get() = "/$linkName"
}
