package slides.shape.composite

import slides.canvas.Canvas
import slides.canvas.Point
import slides.canvas.RGBAColor
import slides.canvas.StrokeParameters
import slides.shape.Frame
import slides.shape.Shape

class CompositeShapeImpl : CompositeShape {
    private val allShapes: MutableList<Shape> = mutableListOf()

    override fun add(vararg shapes: Shape) {
        shapes.forEach { add(it, allShapes.size) }
    }

    override fun add(shape: Shape, position: Int) {
        if (isChild(shape)) {
            throw IllegalArgumentException("This shape was already added")
        }
        if ((shape is CompositeShape) && isParent(shape)) {
            throw IllegalArgumentException("This shape is parent")
        }
        if (this === shape) {
            throw IllegalArgumentException("Adding shape inside itself is not allowed")
        }
        allShapes.add(position, shape)
    }

    override fun remove(position: Int) {
        allShapes.removeAt(position)
    }

    override fun getAllShapes(): List<Shape> {
        return allShapes.toList()
    }

    override fun isChild(shape: Shape): Boolean {
        return allShapes.any {
            if (it === shape) {
                true
            } else if (it is CompositeShape) {
                return it.isChild(shape)
            } else {
                false
            }
        }
    }

    override fun isParent(shape: CompositeShape): Boolean {
        return shape.isChild(this)
    }

    override fun getFrame(): Frame? {
        if (allShapes.isEmpty()) {
            return null
        }

        val allFrames = allShapes.mapNotNull { it.getFrame() }
        val minX = allFrames.minOf { it.leftTop.x }
        val minY = allFrames.minOf { it.leftTop.y }
        val maxX = allFrames.maxOf { it.leftTop.x + it.width }
        val maxY = allFrames.maxOf { it.leftTop.y + it.height }

        return Frame(Point(minX, minY), maxX - minX, maxY - minY)
    }

    override fun setFrame(newFrame: Frame) {
        val prevFrame = when (val nullableFrame = getFrame()) {
            is Frame -> nullableFrame
            else -> return
        }

        allShapes.forEach {
            val currFrame = it.getFrame()
            if (currFrame != null) {
                val newMinX = newFrame.leftTop.x + (currFrame.leftTop.x - prevFrame.leftTop.x) * newFrame.width /
                        prevFrame.width
                val newMinY = newFrame.leftTop.y + (currFrame.leftTop.y - prevFrame.leftTop.y) * newFrame.height /
                        prevFrame.height
                val newWidth = currFrame.width * newFrame.width / prevFrame.width
                val newHeight = currFrame.height * newFrame.height / prevFrame.height

                it.setFrame(Frame(Point(newMinX, newMinY), newWidth, newHeight))
            }
        }
    }

    override fun getFillColor(): RGBAColor? {
        if (allShapes.isNotEmpty()) {
            val allFillColors = allShapes.mapNotNull { it.getFillColor() }
            val uniqueColors = allFillColors.distinct()
            return when (uniqueColors.size) {
                1 -> uniqueColors.first()
                else -> null
            }
        }
        return null
    }

    override fun setFillColor(newColor: RGBAColor?) {
        allShapes.forEach { it.setFillColor(newColor) }
    }

    override fun getStroke(): StrokeParameters {
        val allStrokeWidths = allShapes.mapNotNull { it.getStroke().width }
        val allStrokeColors = allShapes.mapNotNull { it.getStroke().color }

        val uniqueStrokeWidth = allStrokeWidths.distinct()
        val width = when (uniqueStrokeWidth.size) {
            1 -> uniqueStrokeWidth.first()
            else -> null
        }

        val uniqueStrokeColor = allStrokeColors.distinct()
        val color = when (uniqueStrokeColor.size) {
            1 -> uniqueStrokeColor.first()
            else -> null
        }
        return StrokeParameters(color, width)
    }

    override fun setStroke(newStroke: StrokeParameters) {
        allShapes.forEach {
            val currStroke = it.getStroke()
            it.setStroke(
                StrokeParameters(
                    newStroke.color ?: currStroke.color,
                    newStroke.width ?: currStroke.width
                )
            )
        }
    }

    override fun draw(canvas: Canvas) {
        allShapes.forEach { it.draw(canvas) }
    }
}