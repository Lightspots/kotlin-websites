package ch.lightspots.it.web.shared

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.css.Cursor
import kotlinx.css.Display
import kotlinx.css.ListStyleType
import kotlinx.css.Overflow
import kotlinx.css.Position
import kotlinx.css.TextAlign
import kotlinx.css.WhiteSpace
import kotlinx.css.borderRadius
import kotlinx.css.cursor
import kotlinx.css.display
import kotlinx.css.height
import kotlinx.css.listStyleType
import kotlinx.css.margin
import kotlinx.css.marginBottom
import kotlinx.css.overflow
import kotlinx.css.pct
import kotlinx.css.position
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.top
import kotlinx.css.transition
import kotlinx.css.whiteSpace
import kotlinx.css.width
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.figure
import react.dom.img
import react.dom.jsStyle
import react.setState
import styled.css
import styled.styledDiv
import styled.styledLi
import styled.styledOl
import kotlin.browser.window

interface SliderProps : RProps {
  var images: Array<String>
}

interface SliderState : RState {
  var activeImage: Int
}


class Slider(props: SliderProps) : RComponent<SliderProps, SliderState>(props) {
  private var sliderElem: HTMLElement? = null
  private var job: Job? = null

  // we need to add and remove the exact same reference, so save it here
  private val onResize: (Event) -> Unit = {
    // trigger a re render
    setState { }
    // restart auto rotation
    startAutoRotation()
  }

  override fun SliderState.init(props: SliderProps) {
    activeImage = 0
  }

  override fun componentDidMount() {
    startAutoRotation()
    window.addEventListener("resize", onResize)
  }

  override fun componentWillUnmount() {
    window.removeEventListener("resize", onResize)
    job?.cancel()
  }

  override fun RBuilder.render() {
    styledDiv {
      css {
        marginBottom = (-25).px
      }
      images()
      dots()
    }
  }

  private fun RBuilder.images() {
    styledDiv {
      css {
        overflow = Overflow.hidden
      }
      styledOl {
        css {
          listStyleType = ListStyleType.none
          whiteSpace = WhiteSpace.nowrap
          position = Position.relative
          transition += Transition("left", 1.5.s, Timing.easeInOut, 0.s)
        }
        attrs {
          jsStyle {
            left = sliderElem?.let { -it.offsetWidth * (state.activeImage) }?.px ?: 0.px
          }
        }
        ref {
          sliderElem = it as? HTMLElement
        }
        for (image in props.images) {
          styledLi {
            css {
              display = Display.inlineBlock
              width = 100.pct
            }
            figure("image") {
              img(alt = "slider", src = image) { }
            }
          }
        }
      }
    }
  }

  private fun RBuilder.dots() {
    styledOl {
      css {
        listStyleType = ListStyleType.none
        textAlign = TextAlign.center
        position = Position.relative
        top = (-30).px
      }
      for (i in props.images.indices) {
        styledLi {
          css {
            display = Display.inlineBlock
            width = 10.px
            height = 10.px
            borderRadius = 5.px
            cursor = Cursor.pointer
            margin(5.px)
            if (i == state.activeImage) {
              +"has-background-primary"
            } else {
              +"has-background-light"
            }
          }
          attrs {
            onClickFunction = {
              startAutoRotation()
              setState {
                activeImage = i
              }
            }
          }
        }
      }
    }
  }

  private fun startAutoRotation() {
    job?.cancel()
    job = GlobalScope.launch {
      while (isActive) {
        delay(5000)
        setState {
          activeImage = (activeImage + 1) % props.images.size
        }
      }
    }
  }
}

fun RBuilder.slider(images: Array<String>) = child(Slider::class) {
  attrs {
    this.images = images
  }
}