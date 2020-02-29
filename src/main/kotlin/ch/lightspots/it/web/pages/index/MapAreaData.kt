package ch.lightspots.it.web.pages.index

import ch.lightspots.it.web.Link
import kotlinx.css.rgba
import kotlinx.html.AreaShape
import org.w3c.dom.CanvasRenderingContext2D
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
  override fun scale(scale: Double) = Point((x * scale).toInt(),
      (y * scale).toInt())

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