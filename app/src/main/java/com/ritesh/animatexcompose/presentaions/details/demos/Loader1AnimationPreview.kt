package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesh.animatexcompose.presentaions.ui.theme.AnimateXComposeTheme

@Composable
fun Loader1AnimationPreview() {
    val infiniteTransition = rememberInfiniteTransition()
    val duration = 1000
    val rotation1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing)
        )
    )
    val rotation2 by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing)
        )
    )

    val colors1 = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    val colors2 = listOf(Color.Cyan, Color.Magenta, Color.Red, Color.Green)

    val animatedColor1 by infiniteTransition.animateColor(
        initialValue = colors1[0],
        targetValue = colors1[1],
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = colors1.size * duration
                colors1.forEachIndexed { index, color ->
                    color at index * duration
                }
            },
            repeatMode = RepeatMode.Reverse
        ), label = "animatedColor"
    )
    val animatedColor2 by infiniteTransition.animateColor(
        initialValue = colors2[0],
        targetValue = colors2[1],
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = colors2.size * duration
                colors2.forEachIndexed { index, color ->
                    color at index * duration
                }
            },
            repeatMode = RepeatMode.Reverse
        ), label = "animatedColor"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LoaderArc(
            color = animatedColor1,
            startAngle = rotation1,
            sweepAngle = 300f,
            radius = 30,
            width = 4
        )
        LoaderArc(
            color = animatedColor2,
            startAngle = rotation2,
            sweepAngle = 300f,
            radius = 44,
            width = 4
        )
    }

}

@Composable
fun LoaderArc(
    color: Color,
    startAngle: Float,
    sweepAngle: Float=300f,
    radius: Int,
    width: Int
) {
    Canvas(modifier = Modifier
        .size(radius.dp)
    ) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            size = size,
            style = Stroke(width.dp.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimePrev() {
    AnimateXComposeTheme {
        Loader1AnimationPreview()
    }
}