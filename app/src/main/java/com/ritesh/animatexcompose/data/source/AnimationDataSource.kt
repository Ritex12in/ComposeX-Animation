package com.ritesh.animatexcompose.data.source

import com.ritesh.animatexcompose.domain.model.AnimationItem
import com.ritesh.animatexcompose.domain.model.AnimationType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AnimationDataSource {

    private val animations = listOf(
        AnimationItem(
            id = 1,
            title = "Fade Animation",
            description = "A simple fade in/out animation that changes the opacity of a component over time. This is commonly used for smooth transitions when showing or hiding UI elements.",
            previewTitle = "Fade In/Out",
            animationType = AnimationType.FADE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/FadeAnimationPreview.kt"
        ),
        AnimationItem(
            id = 2,
            title = "Scale Animation",
            description = "An animation that changes the size of a component. Scale animations can create emphasis, add playfulness, or guide user attention to specific elements.",
            previewTitle = "Scale Up/Down",
            animationType = AnimationType.SCALE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/ScaleAnimationPreview.kt"
        ),
        AnimationItem(
            id = 3,
            title = "Rotate Animation",
            description = "An animation that rotates a component around its center. Rotation can be used for loading indicators, toggling states, or adding visual interest to UI elements.",
            previewTitle = "Rotate Element",
            animationType = AnimationType.ROTATE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/RotateAnimationPreview.kt"
        ),
        AnimationItem(
            id = 4,
            title = "Slide Animation",
            description = "An animation that moves a component from one position to another. Slide animations are effective for transitions, revealing content, or shifting elements into view.",
            previewTitle = "Slide In/Out",
            animationType = AnimationType.SLIDE,
            gitHubLink = ""
        ),
        AnimationItem(
            id = 5,
            title = "Color Change Animation",
            description = "An animation that smoothly transitions from one color to another. Color animations can indicate state changes, highlight important elements, or create visual effects.",
            previewTitle = "Color Transition",
            animationType = AnimationType.COLOR_CHANGE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/SlideAnimationPreview.kt"
        ),
        AnimationItem(
            id = 6,
            title = "Shimmer Animation",
            description = "A shimmering effect animation commonly used for loading states. Shimmer creates a sense of activity and reassures users that content is loading.",
            previewTitle = "Shimmer Effect",
            animationType = AnimationType.SHIMMER,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/ShimmerAnimationPreview.kt"
        ),
        AnimationItem(
            id = 7,
            title = "Bounce Animation",
            description = "A fun, playful animation that creates a bouncing effect. Bounce animations can add character to a UI and draw attention to particular elements.",
            previewTitle = "Bouncing Element",
            animationType = AnimationType.BOUNCE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/BounceAnimationPreview.kt"
        ),
        AnimationItem(
            id = 8,
            title = "Pulse Animation",
            description = "A pulsing effect that alternates between expanding and contracting. Pulse animations can draw attention to notifications, alerts, or important UI elements.",
            previewTitle = "Pulsing Element",
            animationType = AnimationType.PULSE,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/PulseAnimationPreview.kt"
        )
    )

    fun getAnimations(): Flow<List<AnimationItem>> {
        return flowOf(animations)
    }

    fun getAnimationById(id: Int): Flow<AnimationItem?> {
        return flowOf(animations.find { it.id == id })
    }
}