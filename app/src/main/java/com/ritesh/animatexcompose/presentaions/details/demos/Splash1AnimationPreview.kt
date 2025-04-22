package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ritesh.animatexcompose.R
import kotlinx.coroutines.delay

@Composable
fun Splash1AnimationPreview() {
    var logoShrunk by remember { mutableStateOf(false) }
    var showProgressbar by remember { mutableStateOf(false) }

    val logoSize by animateDpAsState(
        targetValue = if (logoShrunk) 40.dp else 120.dp,
        animationSpec = tween(durationMillis = 1000),
        label = "logo animation"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = if (showProgressbar) 1f else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = "Progress Animation"
    )

    LaunchedEffect(Unit) {
        delay(500)
        logoShrunk = true
        delay(1000)
        showProgressbar = true
        delay(1500)
        // action after animation end
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "app_logo",
                    modifier = Modifier.size(logoSize)
                )

                AnimatedVisibility(
                    showProgressbar
                ) {
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .width(150.dp)
                            .height(8.dp),
                        color = Color(0xFF1eb4eb),
                        trackColor = Color.Gray,
                        strokeCap = StrokeCap.Round,
                    )
                }
            }
        }

        Button(onClick = {
            logoShrunk = !logoShrunk
            showProgressbar = !showProgressbar
        }) {
            Text(text = if (logoShrunk) "Reset" else "Reload")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}