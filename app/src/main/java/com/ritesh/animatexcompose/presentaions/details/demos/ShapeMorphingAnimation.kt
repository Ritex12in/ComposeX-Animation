package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ShapeMorphingAnimation() {
    var currentShapeIndex by remember { mutableStateOf(0) }
    val shapeNames = listOf("Circle", "Square", "Hexagon")

    // Create animated transition
    val transition = updateTransition(
        targetState = currentShapeIndex,
        label = "ShapeTransition"
    )

    // Animation duration
    val animationDuration = 1000

    // UI state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Canvas for the shape
        Surface(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            MorphingShape(transition, animationDuration)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                currentShapeIndex = (currentShapeIndex + 1) % shapeNames.size
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Change Shape")
        }
    }
}

@Composable
fun MorphingShape(
    transition: Transition<Int>,
    duration: Int
) {
    val animationSpec = tween<Float>(
        durationMillis = duration,
        easing = FastOutSlowInEasing
    )

    // Animated values for morphing
    val circleWeight by transition.animateFloat(
        transitionSpec = { animationSpec },
        label = "circleWeight"
    ) { shapeIndex ->
        when (shapeIndex) {
            0 -> 1.0f  // Full circle
            else -> 0.0f  // No circle
        }
    }

    val squareWeight by transition.animateFloat(
        transitionSpec = { animationSpec },
        label = "squareWeight"
    ) { shapeIndex ->
        when (shapeIndex) {
            1 -> 1.0f  // Full square
            else -> 0.0f  // No square
        }
    }

    val hexagonWeight by transition.animateFloat(
        transitionSpec = { animationSpec },
        label = "hexagonWeight"
    ) { shapeIndex ->
        when (shapeIndex) {
            2 -> 1.0f  // Full hexagon
            else -> 0.0f  // No hexagon
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width.coerceAtMost(size.height) * 0.4f

        // Draw shape based on animated values
        drawMorphedShape(
            center = center,
            radius = radius,
            circleWeight = circleWeight,
            squareWeight = squareWeight,
            hexagonWeight = hexagonWeight,
            color = Color(0xFF6200EA)
        )
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawMorphedShape(
    center: Offset,
    radius: Float,
    circleWeight: Float,
    squareWeight: Float,
    hexagonWeight: Float,
    color: Color
) {
    val path = Path()

    // Number of points to sample around the shape
    val numPoints = 120 // Higher resolution sampling

    for (i in 0 until numPoints) {
        val t = i.toFloat() / numPoints
        val angle = (2.0f * PI * t).toFloat()

        // Calculate point for each shape type
        val circlePoint = getCirclePoint(center, radius, angle)
        val squarePoint = getSquarePoint(center, radius, angle)
        val hexagonPoint = getHexagonPoint(center, radius, angle)

        // Blend points based on animation values (normalized weights)
        val totalWeight = circleWeight + squareWeight + hexagonWeight
        val normalizedCircleWeight = if (totalWeight > 0) circleWeight / totalWeight else 0f
        val normalizedSquareWeight = if (totalWeight > 0) squareWeight / totalWeight else 0f
        val normalizedHexagonWeight = if (totalWeight > 0) hexagonWeight / totalWeight else 0f

        val blendedX =
            circlePoint.x * normalizedCircleWeight +
                    squarePoint.x * normalizedSquareWeight +
                    hexagonPoint.x * normalizedHexagonWeight

        val blendedY =
            circlePoint.y * normalizedCircleWeight +
                    squarePoint.y * normalizedSquareWeight +
                    hexagonPoint.y * normalizedHexagonWeight

        val blendedPoint = Offset(blendedX, blendedY)

        if (i == 0) {
            path.moveTo(blendedPoint.x, blendedPoint.y)
        } else {
            path.lineTo(blendedPoint.x, blendedPoint.y)
        }
    }

    path.close()

    // Draw the path
    drawPath(
        path = path,
        color = color,
        style = Fill
    )
}

// Improved shape point calculation functions
private fun getCirclePoint(center: Offset, radius: Float, angle: Float): Offset {
    return Offset(
        x = center.x + radius * cos(angle),
        y = center.y + radius * sin(angle)
    )
}

private fun getSquarePoint(center: Offset, radius: Float, angle: Float): Offset {
    val halfSide = radius * 0.9f

    // Define the four corners of the square
    val topRight = Offset(center.x + halfSide, center.y - halfSide)
    val bottomRight = Offset(center.x + halfSide, center.y + halfSide)
    val bottomLeft = Offset(center.x - halfSide, center.y + halfSide)
    val topLeft = Offset(center.x - halfSide, center.y - halfSide)

    // Normalize angle to 0-2π
    val ang = angle % (2f * PI.toFloat())

    // Determine which side we're on and the progress along that side
    return when {
        // Top-right to bottom-right (0 to π/2)
        ang < PI.toFloat() / 2f -> {
            val t = ang / (PI.toFloat() / 2f)
            interpolate(topRight, bottomRight, t)
        }
        // Bottom-right to bottom-left (π/2 to π)
        ang < PI.toFloat() -> {
            val t = (ang - PI.toFloat() / 2f) / (PI.toFloat() / 2f)
            interpolate(bottomRight, bottomLeft, t)
        }
        // Bottom-left to top-left (π to 3π/2)
        ang < 3f * PI.toFloat() / 2f -> {
            val t = (ang - PI.toFloat()) / (PI.toFloat() / 2f)
            interpolate(bottomLeft, topLeft, t)
        }
        // Top-left to top-right (3π/2 to 2π)
        else -> {
            val t = (ang - 3f * PI.toFloat() / 2f) / (PI.toFloat() / 2f)
            interpolate(topLeft, topRight, t)
        }
    }
}

// Helper function to interpolate between two points
private fun interpolate(start: Offset, end: Offset, t: Float): Offset {
    return Offset(
        start.x + (end.x - start.x) * t,
        start.y + (end.y - start.y) * t
    )
}


private fun getHexagonPoint(center: Offset, radius: Float, angle: Float): Offset {
    // A regular hexagon has 6 sides with 60 degree angles (PI/3 radians)
    val sides = 6
    val sideAngle = (2 * PI / sides).toFloat()

    // Normalize angle to 0-2π
    val normalizedAngle = angle % (2 * PI.toFloat())

    // Find which segment of the hexagon we're in
    val segment = (normalizedAngle / sideAngle).toInt()
    val segmentAngle = segment * sideAngle
    val nextSegmentAngle = (segment + 1) * sideAngle

    // Get hexagon corners for this segment
    val point1 = Offset(
        center.x + radius * cos(segmentAngle),
        center.y + radius * sin(segmentAngle)
    )

    val point2 = Offset(
        center.x + radius * cos(nextSegmentAngle),
        center.y + radius * sin(nextSegmentAngle)
    )

    // Calculate position between the two corners based on normalized angle
    val segmentProgress = (normalizedAngle - segmentAngle) / sideAngle

    return Offset(
        x = point1.x + (point2.x - point1.x) * segmentProgress,
        y = point1.y + (point2.y - point1.y) * segmentProgress
    )
}
