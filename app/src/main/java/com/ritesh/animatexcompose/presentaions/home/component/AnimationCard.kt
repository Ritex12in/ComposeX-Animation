package com.ritesh.animatexcompose.presentaions.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ritesh.animatexcompose.R
import com.ritesh.animatexcompose.domain.model.AnimationItem
import com.ritesh.animatexcompose.domain.model.AnimationType

@Composable
fun AnimationCard(
    animation: AnimationItem,
    onItemClick: (AnimationItem) -> Unit
) {
    val cardColor = when (animation.animationType) {
        AnimationType.FADE -> Color(0xFF00BF63)
        AnimationType.SCALE -> MaterialTheme.colorScheme.secondary
        AnimationType.ROTATE -> Color(0xFF672FD7)
        AnimationType.SLIDE -> Color(0xFF72c7d0)
        AnimationType.COLOR_CHANGE -> Color(0xFFe0b0a4)
        AnimationType.SHIMMER -> Color(0xFFd7a3b7)
        AnimationType.BOUNCE -> Color(0xFFd8789e)
        AnimationType.PULSE -> Color(0xFFae6873)
        AnimationType.EXPAND -> Color(0xFFae6873)
        AnimationType.SPLASH -> Color(0xFF8da9c4)
        AnimationType.LOADER -> Color(0xFFc4bca9)
        AnimationType.RANDOM -> Color(0xFF3772ff)
        AnimationType.FLIP -> Color(0xFF2afc98)
        AnimationType.MORPH -> Color(0xFF6ccff6)
    }
    val icon = when(animation.animationType){
        AnimationType.FADE -> R.drawable.fade
        AnimationType.ROTATE -> R.drawable.rotate
        else -> R.drawable.baseline_animation_24
    }
    Card(
        onClick = {onItemClick(animation)},
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = animation.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(56.dp)
            )

            Text(
                text = animation.title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}
