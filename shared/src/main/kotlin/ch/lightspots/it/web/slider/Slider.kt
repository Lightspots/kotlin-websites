package ch.lightspots.it.web.slider

import ch.lightspots.it.web.manager.VisibilityManager
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
import kotlinx.html.currentTimeMillis
import kotlinx.html.js.onClickFunction
import org.w3c.dom.DataTransferItemList
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
import kotlin.coroutines.coroutineContext
import kotlin.js.Date
import kotlin.random.Random

enum class Direction {
  LEFT, RIGHT
}

interface SliderProps : RProps {
  var images: Array<String>
  var random: Boolean
  var direction: Direction
}

interface SliderState : RState {
  var activeImage: Int
  var activeDot: Int
  var transitionEnabled: Boolean
  var images: Array<String>
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

  private val onVisibilityChange: (VisibilityManager.Visibility) -> Unit = {
    when (it) {
      VisibilityManager.Visibility.HIDDEN -> {
        // stop rotation
        job?.cancel()
      }
      VisibilityManager.Visibility.VISIBLE -> {
        // start rotation
        startAutoRotation()
      }
    }
  }

  override fun SliderState.init(props: SliderProps) {
    activeImage = 1
    activeDot = 1
    transitionEnabled = false
    images = if (props.random) {
      val shuffled = props.images.copyOf()
      shuffled.shuffle(Random(currentTimeMillis()))
      shuffled
    } else {
      props.images
    }
  }

  override fun componentDidMount() {
    startAutoRotation()
    window.addEventListener("resize", onResize)
    VisibilityManager.registerListener(onVisibilityChange)
    // fix that wrong image is displayed as first
    GlobalScope.launch {
      do {
        delay(100)
      } while (sliderElem == null)
      setState { }
    }
  }

  override fun componentWillUnmount() {
    window.removeEventListener("resize", onResize)
    VisibilityManager.unRegisterListener(onVisibilityChange)
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
          if (state.transitionEnabled) {
            transition += Transition("left", 1.5.s, Timing.easeInOut, 0.s)
          }
        }
        attrs {
          jsStyle {
            left = sliderElem?.let { -it.offsetWidth * (state.activeImage) }?.px ?: 0.px
          }
        }
        ref {
          sliderElem = it as? HTMLElement
        }
        styledLi {
          css {
            display = Display.inlineBlock
            width = 100.pct
          }
          figure("image") {
            img(alt = "slider", src = state.images.last()) { }
          }
        }
        for (image in state.images) {
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
        styledLi {
          css {
            display = Display.inlineBlock
            width = 100.pct
          }
          figure("image") {
            img(alt = "slider", src = state.images.first()) { }
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
      for (i in state.images.indices.map { it + 1 }) {
        styledLi {
          css {
            display = Display.inlineBlock
            width = 10.px
            height = 10.px
            borderRadius = 5.px
            cursor = Cursor.pointer
            margin(5.px)
            if (i == state.activeDot) {
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
                activeDot = i
                transitionEnabled = true
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
        when (props.direction) {
          Direction.LEFT -> rotateLeft()
          Direction.RIGHT -> rotateRight()
        }
      }
    }
  }

  private suspend fun rotateRight() {
    val newActiveImage = (state.activeImage) % (state.images.size) + 1
    GlobalScope.launch(coroutineContext) {
      if (newActiveImage == 1) {
        setState {
          activeImage += 1
          activeDot = newActiveImage
        }
        delay(2000)
      }
      setState {
        activeImage = newActiveImage
        activeDot = newActiveImage
        transitionEnabled = newActiveImage != 1
      }
    }
  }

  private suspend fun rotateLeft() {
    var newActiveImage = (state.activeImage - 1)
    GlobalScope.launch(coroutineContext) {
      if (newActiveImage == 0) {
        setState {
          activeImage = 0
          activeDot = state.images.size
        }
        newActiveImage = state.images.size
        delay(2000)
      }
      setState {
        activeImage = newActiveImage
        activeDot = newActiveImage
        transitionEnabled = newActiveImage != state.images.size
      }
    }
  }
}

fun RBuilder.slider(images: Array<String>, random: Boolean = false, direction: Direction = Direction.RIGHT) = child(
    Slider::class) {
  attrs {
    this.images = images
    this.random = random
    this.direction = direction
  }
}

fun <T> Array<T>.shuffle(rnd: Random) {
  // Fisher-Yates shuffle algorithm
  for (i in this.size - 1 downTo 1) {
    val j = rnd.nextInt(i + 1)
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
  }
}