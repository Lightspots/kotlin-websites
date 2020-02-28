package ch.lightspots.it.web.pages.index

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
import kotlinx.css.left
import kotlinx.css.listStyleType
import kotlinx.css.margin
import kotlinx.css.marginBottom
import kotlinx.css.overflow
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
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.figure
import react.dom.img
import react.setState
import styled.css
import styled.styledDiv
import styled.styledLi
import styled.styledOl

interface SliderProps : RProps {
  var images: Array<String>
}

interface SliderState : RState {
  var activeImage: Int
  var job: Job?
}


class Slider(props: SliderProps) : RComponent<SliderProps, SliderState>(props) {
  private var sliderElem: HTMLElement? = null
  private var job: Job? = null

  override fun SliderState.init(props: SliderProps) {
    activeImage = 0
  }

  override fun componentDidMount() {
    startAutoRotation()
    // todo listen on resize
  }

  override fun componentWillUnmount() {
    job?.cancel()
  }

  override fun RBuilder.render() {
    styledDiv {
      css {
        marginBottom = (-25).px
      }
      styledDiv {
        css {
          overflow = Overflow.hidden
        }
        styledOl {
          css {
            listStyleType = ListStyleType.none
            whiteSpace = WhiteSpace.nowrap
            position = Position.relative
            left = sliderElem?.let { -it.offsetWidth * (state.activeImage) }?.px ?: 0.px
            transition += Transition("left", 1.5.s, Timing.easeInOut, 0.s)
          }
          ref {
            sliderElem = it as? HTMLElement
          }
          for (image in props.images) {
            styledLi {
              css {
                display = Display.inlineBlock
              }
              figure("image") {
                img(alt = "slider", src = image) { }
              }
            }
          }
        }
      }
      dots()
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