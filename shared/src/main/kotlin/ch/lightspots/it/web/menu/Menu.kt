package ch.lightspots.it.web.menu

import kotlinx.css.Align
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.JustifyContent
import kotlinx.css.alignItems
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.height
import kotlinx.css.justifyContent
import kotlinx.css.paddingLeft
import kotlinx.css.paddingRight
import kotlinx.css.pct
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.Timing
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.css.px
import kotlinx.css.textDecoration
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.a
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledNav
import styled.styledSpan

interface MenuProps : RProps {
  var logo: Logo
  var menuItems: List<MenuItem>
}

interface MenuState : RState

sealed class MenuItem(
    open val title: String
)

data class LinkMenuItem(override val title: String, val href: String) : MenuItem(title)
data class DropdownMenuItem(
    override val title: String,
    val children: List<MenuItem>,
    val href: String? = null
) : MenuItem(title)

data class Logo(val content: ReactElement, val href: String)

class Menu(props: MenuProps) : RComponent<MenuProps, MenuState>(props) {

  override fun RBuilder.render() {
    styledDiv {
      css {
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.stretch
      }
      styledDiv {
        css {
        }
        renderLogo(props.logo)
      }
      styledDiv {
        css {
        }
        styledNav {
          css {
            height = 100.pct
          }

          props.menuItems.forEach {
            renderMenuItem(it)
          }
        }
      }
    }

  }

  private fun RBuilder.renderLogo(logo: Logo) {
    a(href = logo.href, classes = "has-text-centered") {
      child(logo.content)
    }
  }

  private fun RBuilder.renderMenuItem(item: MenuItem) {
    when (item) {
      is LinkMenuItem -> {
        styledA(href = item.href) {
          css {
            +"has-text-centered"
            textDecoration = TextDecoration.none
            color = Color.black
            height = 100.pct
            paddingLeft = 12.px
            paddingRight = 12.px
            display = Display.inlineBlock
            hover {
              transition("background-color", 500.ms, Timing.linear)
              backgroundColor = Color.lightBlue
            }
          }
          styledSpan {
            css {
              display = Display.flex
              justifyContent = JustifyContent.center
              alignItems = Align.center
              height = 100.pct
            }
            +item.title
          }
        }
      }
      else -> {
        TODO()
      }
    }
  }

}

fun RBuilder.menu(logo: Logo,
    menuItems: List<MenuItem>) = child(
    Menu::class) {
  attrs {
    this.logo = logo
    this.menuItems = menuItems
  }
}