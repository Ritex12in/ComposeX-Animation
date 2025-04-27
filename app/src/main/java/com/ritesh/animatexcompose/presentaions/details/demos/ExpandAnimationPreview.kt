package com.ritesh.animatexcompose.presentaions.details.demos

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpandAnimationPreview() {
    var expanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFa7c957)
            ),
            modifier = Modifier.padding(16.dp)) {
            Text(
                "In a parallel universe where coffee brews itself and code writes developers, " +
                        "a rubber duck named Jeff accidentally discovered the meaning of life in a misplaced semicolon. "+
                        "Ever since that moment, Jeff’s fame skyrocketed—he now tours the cloud, giving motivational " +
                        "quacks while it rains emojis from the newly updated sky software.",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (expanded) Int.MAX_VALUE else 4,
                modifier = Modifier
                    .padding(16.dp)
                    .animateContentSize(
                    animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {expanded = !expanded},
            shape = RoundedCornerShape(8.dp)
        ) {
            Row {
                Text(if (expanded) "Show Less" else "Show More")
            }
        }
    }
}