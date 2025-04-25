package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritesh.animatexcompose.presentaions.ui.theme.AnimateXComposeTheme

@Composable
fun FlipCardDemo() {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "cardRotation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotation.value
                    cameraDistance = 12 * density
                },
            contentAlignment = Alignment.Center
        ) {
            if (rotation.value <= 90f) {
                FrontCard()
            } else {
                Box(
                    modifier = Modifier.graphicsLayer {
                        rotationY = 180f
                    }
                ) {
                    BackCard()
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { isFlipped = !isFlipped }) {
            Text(text = "Flip")
        }
    }
}

@Composable
fun FrontCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text("Front", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BackCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text("Back", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
