package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ritesh.animatexcompose.presentaions.ui.theme.AnimateXComposeTheme
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SineWaveWithBallVariableSpeed(
    modifier: Modifier = Modifier,
    waveColor: Color = MaterialTheme.colorScheme.primary,
    waveStroke: Dp = 3.dp,
    ballColor: Color = MaterialTheme.colorScheme.tertiary,
    ballRadius: Dp = 8.dp,
    cycles: Int = 2,
    waveAmplitudeRatio: Float = 0.4f,
    waveDrawResolution: Int = 720,
    travelDurationMs: Int = 2_500
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ballTravel")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(travelDurationMs, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = "progress"
    )

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val midY = h / 2f
        val amplitude = h * waveAmplitudeRatio
        val twoPiCycles = (2 * PI * cycles).toFloat()

        /* 1) Draw sine curve */
        val path = Path()
        for (i in 0..waveDrawResolution) {
            val x = w * i / waveDrawResolution
            val rad = twoPiCycles * i / waveDrawResolution
            val y = midY - amplitude * sin(rad).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        drawPath(
            path = path,
            color = waveColor,
            style = Stroke(waveStroke.toPx(), cap = StrokeCap.Round)
        )

        /* 2) Ball position */
        val ballX = w * progress
        val angle = twoPiCycles * progress
        val ballY = midY - amplitude * sin(angle).toFloat()

        drawCircle(ballColor, ballRadius.toPx(), Offset(ballX, ballY))
    }
}

@Preview(showBackground = true)
@Composable
private fun SinBallPrev() {
    AnimateXComposeTheme {
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            SineWaveWithBallVariableSpeed(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                cycles = 3,
                travelDurationMs = 2500
            )
        }
    }
}
