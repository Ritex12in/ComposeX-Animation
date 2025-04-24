package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SinWaveAnimation() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StandingWave(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            waveColor = Color.Cyan,
            strokeWidth = 4.dp,
            cycles = 2,
            durationMs = 1500
        )
    }
}
@Composable
fun StandingWave(
    modifier: Modifier = Modifier,
    waveColor: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 3.dp,
    cycles: Int = 1,                 // how many full sine periods fit in the width
    durationMs: Int = 2000           // time for one full phase shift (2π)
) {
    /* Animate the phase value from 0 → 2π repeatedly */
    val infiniteTransition = rememberInfiniteTransition(label = "phaseShift")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            tween(durationMs, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "phaseAnim"
    )

    Canvas(modifier = modifier) {
        val path = Path()
        val w = size.width
        val h = size.height
        val midY = h / 2f
        val amplitude = h * 0.1f

        /* Draw the whole curve: sample N points across the width */
        val samples = 720                            // resolution (more → smoother)
        (0..samples).forEach { i ->
            val x = w * i / samples
            val rad = (2 * PI * cycles * i / samples) + phase
            val y = midY - amplitude * sin(rad).toFloat()

            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path,
            color = waveColor,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}
