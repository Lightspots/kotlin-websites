package ch.lightspots.it.web.slider

import ch.lightspots.it.web.manager.VisibilityManager
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s
import kotlinx.html.currentTimeMillis
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseOverFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.*
import kotlin.coroutines.coroutineContext
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.DurationUnit

enum class Direction {
  LEFT, RIGHT
}

interface SliderProps : RProps {
  var images: Array<String>
  var random: Boolean
  var direction: Direction
  var duration: Duration
}

interface SliderState : RState {
  var activeImage: Int
  var activeDot: Int
  var transitionEnabled: Boolean
  var images: Array<String>
  var percent: Int
  var buttonsDisabled: Boolean
}

class Slider(props: SliderProps) : RComponent<SliderProps, SliderState>(props) {
  companion object {
    private const val animationDuration = 25L
  }

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
    percent = 0
    buttonsDisabled = false
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
        marginBottom = (-60).px
      }
      images()
      dots()
      progressBar()
      buttons()
    }
  }

  private fun RBuilder.images() {
    styledDiv {
      css {
        overflow = Overflow.hidden
      }
      attrs {
        onMouseOverFunction = this@Slider::mouseIn
        onMouseOutFunction = this@Slider::mouseOut
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
      attrs {
        onMouseOverFunction = this@Slider::mouseIn
        onMouseOutFunction = this@Slider::mouseOut
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
                percent = 0
              }
            }
          }
        }
      }
    }
  }

  private fun RBuilder.buttons() {
    styledDiv {
      css {
        position = Position.relative
        top = sliderElem?.let { (-it.offsetHeight / 2 - 60).px } ?: (-150).px
        width = 100.pct
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
      }
      attrs {
        onMouseOverFunction = this@Slider::mouseIn
        onMouseOutFunction = this@Slider::mouseOut
      }
      // left button
      styledButton {
        css {
          height = 2.5.em
          textAlign = TextAlign.center
          backgroundColor = Color.black
          color = Color.white
          opacity = 0.5
          border = "none"
          cursor = Cursor.pointer
          disabled {
            color = Color.black
            opacity = 0.25
            cursor = Cursor.auto
          }
        }
        span("icon is-small") {
          i("fas fa-chevron-left") {}
        }
        attrs {
          disabled = state.buttonsDisabled
          onClickFunction = {
            GlobalScope.launch {
              setState {
                buttonsDisabled = true
              }
              rotateLeft {
                setState {
                  buttonsDisabled = false
                }
              }
              setState {
                percent = 0
              }
            }
          }
        }
      }

      // right button
      styledButton {
        css {
          height = 2.5.em
          textAlign = TextAlign.center
          backgroundColor = Color.black
          color = Color.white
          opacity = 0.5
          border = "none"
          disabled {
            color = Color.black
            opacity = 0.25
          }
        }
        span("icon is-small") {
          i("fas fa-chevron-right") {}
        }
        attrs {
          disabled = state.buttonsDisabled
          onClickFunction = {
            GlobalScope.launch {
              setState {
                buttonsDisabled = true
              }
              rotateRight {
                setState {
                  buttonsDisabled = false
                }
              }
              setState {
                percent = 0
              }
            }
          }
        }
      }
    }
  }

  private fun RBuilder.progressBar() {
    styledProgress {
      css {
        position = Position.relative
        top = (-50).px
        height = 5.px
        width = 100.pct
        border = "none"
      }
      attrs {
        max = "1000"
        value = state.percent.toString()
      }
    }
  }

  private fun startAutoRotation() {
    job?.cancel()
    val steps = (props.duration.toDouble(DurationUnit.MILLISECONDS) / animationDuration).toInt()
    val increment = 1000 / steps
    job = GlobalScope.launch {
      while (isActive) {
        delay(animationDuration)
        val p = (state.percent + increment)
        setState {
          percent = p
        }
        if (p > 1000) {
          when (props.direction) {
            Direction.LEFT -> rotateLeft()
            Direction.RIGHT -> rotateRight()
          }
          setState {
            percent = 0
          }
        }
      }
    }
  }

  private suspend fun rotateRight(cb: (() -> Unit)? = null) {
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
      cb?.invoke()
    }
  }

  private suspend fun rotateLeft(cb: (() -> Unit)? = null) {
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
      cb?.invoke()
    }
  }

  private fun mouseIn(e: Event) {
    job?.cancel()
  }

  private fun mouseOut(e: Event) {
    startAutoRotation()
  }
}

fun RBuilder.slider(images: Array<String>, random: Boolean = false, direction: Direction = Direction.RIGHT) = child(
    Slider::class) {
  attrs {
    this.images = images
    this.random = random
    this.direction = direction
    this.duration = Duration.seconds(5)
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