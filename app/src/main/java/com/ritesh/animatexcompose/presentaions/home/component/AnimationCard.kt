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
    index: Int,
    animation: AnimationItem,
    onItemClick: (AnimationItem) -> Unit
) {
    val icon = when(animation.animationType){
        AnimationType.FADE -> R.drawable.fade
        AnimationType.ROTATE -> R.drawable.rotate
        AnimationType.SCALE -> R.drawable.scale
        AnimationType.SLIDE -> R.drawable.slide
        AnimationType.COLOR_CHANGE -> R.drawable.color_change
        AnimationType.BOUNCE -> R.drawable.bounce
        AnimationType.PULSE -> R.drawable.pulse
        AnimationType.EXPAND -> R.drawable.expand
        AnimationType.SPLASH -> R.drawable.splash
        AnimationType.LOADER -> R.drawable.loading
        AnimationType.RANDOM -> R.drawable.random
        AnimationType.FLIP -> R.drawable.flip
        AnimationType.MORPH -> R.drawable.morphic
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
            containerColor = if ((index%4==0) || (index+1)%4==0){
                Color(0xFFe07a5f)
            }else{
                Color(0xFFa7c957)
            }
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
