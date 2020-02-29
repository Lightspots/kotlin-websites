package ch.lightspots.it.web.pages.index

import ch.lightspots.it.web.Link
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.css.PointerEvents
import kotlinx.css.Position
import kotlinx.css.pointerEvents
import kotlinx.css.position
import kotlinx.css.rgba
import kotlinx.html.AREA
import kotlinx.html.AreaShape
import kotlinx.html.attributes.enumEncode
import kotlinx.html.attributesMapOf
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseOverFunction
import kotlinx.html.title
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.h2
import react.dom.img
import react.dom.jsStyle
import react.dom.map
import react.dom.tag
import react.setState
import styled.css
import styled.styledCanvas
import kotlin.browser.window
import kotlin.math.PI

interface CoordEntry {
  fun scale(scale: Double): CoordEntry
  fun toCoordsString(): String
}

data class Radius(val r: Int) : CoordEntry {
  override fun scale(scale: Double) = Radius((r * scale).toInt())
  override fun toCoordsString() = r.toString()
}

data class Point(val x: Int, val y: Int) : CoordEntry {
  override fun scale(scale: Double) = Point((x * scale).toInt(), (y * scale).toInt())

  override fun toCoordsString() = "$x,$y"
}

data class Area(val name: String, val link: Link, val shape: AreaShape = AreaShape.poly, val points: List<CoordEntry>) {
  fun draw(ctx: CanvasRenderingContext2D, scale: Double, isActive: Boolean) {
    val p = points.map { it.scale(scale) }
    val alpha = if (isActive) 0.25 else 0.15
    if (shape == AreaShape.circle) {
      val center = p[0] as Point
      val r = p[1] as Radius
      ctx.beginPath()
      ctx.arc(center.x.toDouble(), center.y.toDouble(), r.r.toDouble(), 0.0, 2 * PI, false)
      ctx.closePath()
      ctx.fillStyle = rgba(100, 0, 255, alpha).toString()
      ctx.fill()
    }
    if (shape == AreaShape.poly) {
      ctx.beginPath()
      val firstPoint = p[0] as Point
      ctx.moveTo(firstPoint.x.toDouble(), firstPoint.y.toDouble())
      for (i in 1 until p.size) {
        val point = p[i] as Point
        ctx.lineTo(point.x.toDouble(), point.y.toDouble())
      }
      ctx.closePath()
      ctx.fillStyle = rgba(100, 0, 255, alpha).toString()
      ctx.fill()
    }
  }
}

fun List<CoordEntry>.toCoords(scale: Double): String {
  return this.map { it.scale(scale) }.joinToString(",") { it.toCoordsString() }
}

interface OverviewMapState : RState {
  var hoveredArea: Int?
}

class OverviewMap : RComponent<RProps, OverviewMapState>() {
  private var image: HTMLImageElement? = null
  private var canvas: HTMLCanvasElement? = null
  private var job: Job? = null
  private val onResize: (Event) -> Unit = {
    // trigger a re render
    setState { }
  }
  private val areas = listOf(
      Area("Reithalle", Link.HALLS, points = listOf(
          Point(1033, 1141),
          Point(1250, 1102),
          Point(1277, 1216),
          Point(1054, 1256)
      )),
      Area("Reitplatz mit Teilüberdachung", Link.PLACES, points = listOf(
          Point(940, 804),
          Point(1325, 715),
          Point(1354, 856),
          Point(974, 949)
      )),
      Area("Reitplatz", Link.PLACES, points = listOf(
          Point(638, 406),
          Point(619, 372),
          Point(610, 333),
          Point(613, 295),
          Point(619, 268),
          Point(651, 268),
          Point(685, 270),
          Point(708, 277),
          Point(726, 297),
          Point(738, 338),
          Point(740, 363),
          Point(749, 402),
          Point(761, 436),
          Point(769, 459),
          Point(758, 474),
          Point(735, 484),
          Point(711, 484),
          Point(674, 445)
      )),
      Area("Longier Platz", Link.PLACES, shape = AreaShape.circle, points = listOf(
          Point(770, 527),
          Radius(38)
      )),
      Area("Longier Halle", Link.HALLS, shape = AreaShape.circle, points = listOf(
          Point(1386, 1249),
          Radius(48)
      )),
      Area("Paddockboxen an der Reithalle", Link.STABLES, points = listOf(
          Point(1058, 1256),
          Point(1240, 1224),
          Point(1257, 1295),
          Point(1068, 1329)
      )),
      Area("Paddockboxen im ehemaligen Kuhstall", Link.STABLES, points = listOf(
          Point(1472, 1152),
          Point(1600, 1113),
          Point(1639, 1224),
          Point(1509, 1268)
      )),
      Area("Ausenboxen mit Paddock", Link.STABLES, points = listOf(
          Point(1254, 1099),
          Point(1309, 1088),
          Point(1325, 1149),
          Point(1268, 1161)
      )),
      Area("Paddockboxen mit Überdachung", Link.STABLES, points = listOf(
          Point(1522, 961),
          Point(1595, 956),
          Point(1625, 1072),
          Point(1550, 1088)
      )),
      Area("Laufstallungen für 2 Pferde", Link.STABLES, points = listOf(
          Point(1008, 947),
          Point(1020, 999),
          Point(1150, 972),
          Point(1143, 915)
      )),
      Area("Innenboxen", Link.STABLES, points = listOf(
          Point(1627, 1072),
          Point(1670, 1068),
          Point(1682, 1127),
          Point(1641, 1138)
      )),
      Area("Stallungen", Link.STABLES, points = listOf(
          Point(1320, 881),
          Point(1341, 983),
          Point(1627, 938),
          Point(1593, 831),
          Point(1525, 838),
          Point(1514, 795),
          Point(1407, 806),
          Point(1400, 854)
      )),
      Area("Stallungen", Link.STABLES, points = listOf(
          Point(924, 1006),
          Point(986, 986),
          Point(1000, 1022),
          Point(1156, 995),
          Point(1191, 1084),
          Point(968, 1136)
      ))
  )

  override fun OverviewMapState.init() {
    hoveredArea = null
  }

  override fun componentDidMount() {
    GlobalScope.launch {
      // wait for image size
      while (isActive) {
        delay(100)
        image?.let {
          if (it.width > 0 && it.naturalWidth > 0) {
            setState { }
            return@launch
          }
        }
      }
    }
    window.addEventListener("resize", onResize)
  }

  override fun componentWillUnmount() {
    window.removeEventListener("resize", onResize)
    job?.cancel()
  }

  override fun componentDidUpdate(prevProps: RProps, prevState: OverviewMapState, snapshot: Any) {
    val scale = image?.let {
      it.width.toDouble() / it.naturalWidth
    } ?: 1.0
    canvas?.let {
      val context = it.getContext("2d") as CanvasRenderingContext2D
      context.clearRect(0.0, 0.0, it.width.toDouble(), it.height.toDouble())
      areas.forEachIndexed { index, area ->
        area.draw(context, scale, index == state.hoveredArea)
      }
    }
  }

  override fun RBuilder.render() {
    h2("title is-2") {
      +"Übersichtskarte"
    }
    styledCanvas {
      css {
        position = Position.absolute
        pointerEvents = PointerEvents.none
      }
      ref {
        canvas = it as? HTMLCanvasElement
      }
      attrs {
        jsStyle {
          top = image?.offsetTop
          left = image?.offsetLeft
        }
        width = (image?.width ?: 0).toString()
        height = (image?.height ?: 0).toString()
      }
    }
    val scale = image?.let {
      it.width.toDouble() / it.naturalWidth
    } ?: 1.0
    img(alt = "", src = "images/overview.jpg") {
      attrs {
        usemap = "#overview-map"
      }
      ref {
        image = it as? HTMLImageElement
      }
    }
    map("overview-map") {
      areas.forEachIndexed { index, a ->
        area(shape = a.shape, alt = a.name) {
          attrs {
            title = a.name
            href = a.link.href
            coords = a.points.toCoords(scale)
            onMouseOverFunction = {
              setState {
                hoveredArea = index
              }
            }
            onMouseOutFunction = {
              setState {
                hoveredArea = null
              }
            }
          }
        }
      }
    }
  }


}

fun RBuilder.overviewMap() = child(OverviewMap::class) {}

inline fun RBuilder.area(shape: AreaShape? = null, alt: String? = null, classes: String? = null,
    block: RDOMBuilder<AREA>.() -> Unit): ReactElement = tag(block) {
  AREA(
      attributesMapOf("shape", shape?.enumEncode(), "alt", alt, "class", classes), it)
}