package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Loader2AnimationPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        SpinningYarnLoader(strokeWidth = 8f)
    }
}

@Composable
fun SpinningYarnLoader(
    modifier: Modifier = Modifier,
    color: Color = Color.Cyan,
    strokeWidth: Float = 4f,
    spiralTurns: Int = 5,
    maxRadius: Float = 100f,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing)
        ), label = "angleAnim"
    )

    Canvas(
        modifier = modifier
            .size((maxRadius * 2).dp)
            .graphicsLayer(rotationZ = angle)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val path = Path()

        val steps = 300
        for (i in 0..steps) {
            val t = i / steps.toFloat()
            val angleT = 2 * Math.PI * spiralTurns * t
            val radiusT = t * maxRadius

            val x = (centerX + cos(angleT) * radiusT).toFloat()
            val y = (centerY + sin(angleT) * radiusT).toFloat()

            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
}

