package com.ritesh.animatexcompose.presentaions.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritesh.animatexcompose.R
import com.ritesh.animatexcompose.presentaions.ui.theme.AnimateXComposeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashScreenFinish: () -> Unit
) {
    // States for controlling animations
    var isLogoVisible by remember { mutableStateOf(false) }
    var isTextVisible by remember { mutableStateOf(false) }
    var isAnimationFinished by remember { mutableStateOf(false) }

    // Logo animation controls
    val logoScale by animateFloatAsState(
        targetValue = if (isLogoVisible) 1f else 0.3f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "logoScale"
    )

    // Continuous rotation animation for the logo
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Pulsating effect for the text
    val textScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "textScale"
    )

    // Manage animation sequence
    LaunchedEffect(key1 = true) {
        // Start with logo animation
        delay(300)
        isLogoVisible = true

        // Then show text
        delay(1000)
        isTextVisible = true

        // Keep splash screen visible for a while
        delay(2000)
        isAnimationFinished = true

        // Navigate away
        delay(500)
        onSplashScreenFinish()
    }

    // Main splash screen container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Animated logo
            Box(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .scale(logoScale)
                    .rotate(if (isLogoVisible) rotation else 0f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(130.dp)
                )
            }

            // Animated app name
            AnimatedVisibility(
                visible = isTextVisible,
                enter = fadeIn(animationSpec = tween(1000)) +
                        slideInVertically(
                            initialOffsetY = { it / 2 },
                            animationSpec = tween(1000)
                        ),
                exit = fadeOut()
            ) {
                Text(
                    text = "ComposeX Animation",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.scale(textScale)
                )
            }

            // Optional animated tagline
            AnimatedVisibility(
                visible = isTextVisible,
                enter = fadeIn(animationSpec = tween(1500)),
                exit = fadeOut()
            ) {
                Text(
                    text = "Crafted with Jetpack Compose ✨",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashPrev() {
    AnimateXComposeTheme {
        SplashScreen {  }
    }
}