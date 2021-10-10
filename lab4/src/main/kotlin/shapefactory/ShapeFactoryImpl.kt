package shapefactory

import shape.*

class ShapeFactoryImpl: ShapeFactory {
    enum class ShapeNames {
        Ellipse,
        Rectangle,
        RegularPolygon,
        Triangle
    }

    override fun createShape(description: String): Shape {
        val splitDescription = description.split(" ")
        if (splitDescription.isEmpty()) {
            throw IllegalArgumentException("Incorrect description format")
        }

        return when(splitDescription.first()) {
            ShapeNames.Ellipse.name -> createEllipse(splitDescription)
            ShapeNames.Rectangle.name -> createRectangle(splitDescription)
            ShapeNames.RegularPolygon.name -> createRegularPolygon(splitDescription)
            ShapeNames.Triangle.name -> createTriangle(splitDescription)
            else -> throw IllegalArgumentException("Incorrect description format")
        }
    }

    private fun createEllipse(description: List<String>): Shape {
        if (description.size < 6) {
            throw IllegalArgumentException("Incorrect description format for ellipse")
        }

        return Ellipse(
            Color.valueOf(description[1]),
            Point(description[2].toInt(), description[3].toInt()),
            description[4].toInt(),
            description[5].toInt()
        )
    }

    private fun createRectangle(description: List<String>): Shape {
        if (description.size < 6) {
            throw IllegalArgumentException("Incorrect description format for rectangle")
        }

        return Rectangle(
            Color.valueOf(description[1]),
            Point(description[2].toInt(), description[3].toInt()),
            Point(description[4].toInt(), description[5].toInt())
        )
    }

    private fun createRegularPolygon(description: List<String>): Shape {
        if (description.size < 6) {
            throw IllegalArgumentException("Incorrect description format for regular polygon")
        }

        return RegularPolygon(
            Color.valueOf(description[1]),
            description[2].toInt(),
            Point(description[3].toInt(), description[4].toInt()),
            description[5].toInt()
        )
    }

    private fun createTriangle(description: List<String>): Shape {
        if (description.size < 8) {
            throw IllegalArgumentException("Incorrect description format for triangle")
        }

        return Triangle(
            Color.valueOf(description[1]),
            Point(description[2].toInt(), description[3].toInt()),
            Point(description[4].toInt(), description[5].toInt()),
            Point(description[6].toInt(), description[7].toInt())
        )
    }
}