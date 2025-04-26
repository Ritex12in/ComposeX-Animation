package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GlowingGradientText(
    text: String,
    textStyle: TextStyle = TextStyle.Default,
    gradientColors: List<Color> = listOf(
        Color(0xFF9C27B0), // Purple
        Color(0xFF3F51B5), // Indigo
        Color(0xFF2196F3), // Blue
        Color(0xFF00BCD4), // Cyan
        Color(0xFF4CAF50), // Green
        Color(0xFFFF9800), // Orange
        Color(0xFFFF5722)  // Deep Orange
    ),
    glowRadius: Float = 15f,
    animationDuration: Int = 3000
) {
    // Create a TextMeasurer to measure text dimensions
    val textMeasurer = rememberTextMeasurer()

    // Measure the text layout once
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = textStyle
    )

    // Create infinite transition for animating the gradient
    val infiniteTransition = rememberInfiniteTransition(label = "gradientAnimation")

    // Animate the gradient progress - this will trigger recomposition
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progressAnimation"
    )

    // Using Canvas instead of drawWithContent for more direct control
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier) {
            // Create animated gradient by rotating colors based on progress
            val shiftedColors = mutableListOf<Color>()
            val totalColors = gradientColors.size

            // Calculate the correct shift amount for smooth animation
            for (i in 0 until totalColors) {
                val index = ((i + (progress * totalColors)) % totalColors).toInt()
                shiftedColors.add(gradientColors[index])
            }

            // Create the animated brush
            val brush = Brush.horizontalGradient(shiftedColors)

            // Calculate position to center text
            val textWidth = textLayoutResult.size.width
            val textHeight = textLayoutResult.size.height
            val topLeft = Offset(
                x = (size.width - textWidth) / 2,
                y = (size.height - textHeight) / 2
            )

            // Draw glow effect
            drawGlowEffect(text, textStyle, topLeft, glowRadius)

            // Draw the gradient text
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = textStyle.copy(brush = brush),
                topLeft = topLeft
            )
        }
    }
}

private fun DrawScope.drawGlowEffect(
    text: String,
    textStyle: TextStyle,
    topLeft: Offset,
    glowRadius: Float
) {
    drawIntoCanvas { canvas ->
        // Create the paint for glow effect
        val nativePaint = android.graphics.Paint().apply {
            isAntiAlias = true
            textSize = textStyle.fontSize.toPx()
            color = android.graphics.Color.WHITE
        }

        // Draw multiple layers with different glow settings
        for (i in 5 downTo 1) {
            val alpha = 0.15f * i
            val radius = glowRadius * (i / 5f)

            nativePaint.setShadowLayer(
                radius,
                0f,
                0f,
                android.graphics.Color.argb(
                    (alpha * 255).toInt(),
                    255, 255, 255
                )
            )

            // Draw the glowing text
            canvas.nativeCanvas.drawText(
                text,
                topLeft.x,
                topLeft.y + textStyle.fontSize.toPx() * 0.8f, // Adjust for baseline
                nativePaint
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun GlowingGradientTextPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        GlowingGradientText(
            text = "Glowing Text",
            textStyle = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.White.copy(alpha = 0.3f),
                    blurRadius = 8f
                )
            ),
            glowRadius = 20f,
            animationDuration = 2000
        )
    }
}

// Example usage with cyberpunk theme
@Composable
fun CyberpunkGlowingText() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val cyberpunkColors = listOf(
            Color(0xFFFF00FF), // Pink
            Color(0xFF00FFFF), // Cyan
            Color(0xFFFFA500), // Orange
            Color(0xFF00FF00)  // Green
        )

        GlowingGradientText(
            text = "CYBERPUNK",
            textStyle = TextStyle(
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold
            ),
            gradientColors = cyberpunkColors,
            glowRadius = 24f,
            animationDuration = 3000
        )
    }
}