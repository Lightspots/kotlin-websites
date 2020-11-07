package ch.lightspots.it.web.manager

import org.w3c.dom.events.Event
import org.w3c.dom.get
import kotlinx.browser.document

/**
 * Wrapper around the Page Visibility Api
 * https://developer.mozilla.org/en-US/docs/Web/API/Page_Visibility_API
 */
object VisibilityManager {
  enum class Visibility {
    VISIBLE,
    HIDDEN
  }

  private var visibility: Visibility = Visibility.VISIBLE

  private val hiddenFieldName: String
  private val visibilityChangeEventName: String

  private val listeners = mutableSetOf<(Visibility) -> Unit>()

  init {
    when {
      jsTypeOf(document["hidden"]) != "undefined" -> {
        hiddenFieldName = "hidden"
        visibilityChangeEventName = "visibilitychange"
      }
      jsTypeOf(document["msHidden"]) != "undefined" -> {
        hiddenFieldName = "msHidden"
        visibilityChangeEventName = "msvisibilitychange"
      }
      jsTypeOf(document["webkitHidden"]) != "undefined" -> {
        hiddenFieldName = "webkitHidden"
        visibilityChangeEventName = "webkitvisibilitychange"
      }
      else -> {
        console.warn("Page Visibility Api is not supported")
        hiddenFieldName = ""
        visibilityChangeEventName = ""
      }
    }

    onVisibilityChange(null)
    document.addEventListener(visibilityChangeEventName, this::onVisibilityChange, false)
  }

  private fun onVisibilityChange(e: Event?) {
    visibility = if (document[hiddenFieldName] as Boolean) Visibility.HIDDEN else Visibility.VISIBLE
    listeners.forEach { it(visibility) }
  }

  fun registerListener(l: (v: Visibility) -> Unit) {
    l(visibility)
    listeners += l
  }

  fun unRegisterListener(l: (v: Visibility) -> Unit) {
    listeners -= l
  }

}