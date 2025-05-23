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
        ),
        AnimationItem(
            id = 9,
            title = "Expand Animation",
            description = "An expanding effect that smoothly reveals hidden content or UI elements. Expand animations are ideal for dropdowns, accordions, and section transitions, enhancing the sense of hierarchy and flow in the interface.",
            previewTitle = "Expanding Element",
            animationType = AnimationType.EXPAND,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/ExpandAnimationPreview.kt"
        ),
        AnimationItem(
            id = 10,
            title = "Splash Animation",
            description = "A splash screen animation featuring a shrinking app logo paired with a linear progress bar. This effect " +
                    "creates a polished entry point for the app, subtly drawing attention to branding while indicating loading progress. Ideal for establishing visual rhythm and preparing users for a smooth transition into the main interface.",
            previewTitle = "Splash Animation",
            animationType = AnimationType.SPLASH,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/Splash1AnimationPreview.kt"
        ),
        AnimationItem(
            id = 11,
            title = "Loader1 Animation",
            description = "This composable creates a colorful, rotating loader animation made of two circular arcs that rotate in opposite directions while smoothly transitioning colors through a set of predefined hues.",
            previewTitle = "Loader Animation",
            animationType = AnimationType.LOADER,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/Loader1AnimationPreview.kt"
        ),
        AnimationItem(
            id = 12,
            title = "Loader2 Animation",
            description = "A sleek, animated spiral that continuously rotates like a spinning thread. It’s created using polar " +
                    "coordinates and drawn with a Canvas, making it perfect for creative or modern loading indicators. Fully customizable with color, size, and spiral turns, this loader adds a stylish touch to any Compose UI.",
            previewTitle = "Loader Animation",
            animationType = AnimationType.LOADER,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/Loader2AnimationPreview.kt"
        ),
        AnimationItem(
            id = 13,
            title = "Sin Wave Animation",
            description = "A continuous line renders a full‑width sine curve while its phase shifts over time, making every point on the wave " +
                    "rise and fall in place—like a rope fixed at both ends and vibrating. The result is a smooth, endlessly looping “wiggling” waveform that stays fully drawn yet appears alive as crests and troughs oscillate vertically.",
            previewTitle = "Sin Wave",
            animationType = AnimationType.RANDOM,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/SinWaveAnimation.kt"
        ),
        AnimationItem(
            id = 14,
            title = "Sin Wave with Ball",
            description = "A cyan sine wave stretches across the view, and a magenta ball rides its surface: it eases up each crest, accelerates down each slope, and loops back to start—showcasing synchronized motion and variable speed on a single, endless path.",
            previewTitle = "Sin Wave Ball Animation",
            animationType = AnimationType.RANDOM,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/SineWave2Animation.kt"
        ),
        AnimationItem(
            id = 15,
            title = "Flip Animation",
            description = "The Flip Animation creates a smooth 3D-like transition where a composable appears to " +
                    "rotate around its vertical or horizontal axis, mimicking the effect of flipping a card. This animation is often used for revealing hidden content—like showing the back of a card when tapped.",
            previewTitle = "Card Flip",
            animationType = AnimationType.FLIP,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/FlipAnimationPreview.kt"
        ),
        AnimationItem(
            id = 16,
            title = "Shape Morphing",
            description = "The Shape Morphing Animation visually transforms one geometric shape into another (e.g., circle → square → hexagon) through smooth, continuous interpolation. " +
                    "This type of animation brings life to UI elements, adding a sense of motion, fluidity, and engagement.",
            previewTitle = "Shape Morphing Animation",
            animationType = AnimationType.MORPH,
            gitHubLink = "https://github.com/Ritex12in/ComposeX-Animation/blob/main/app/src/main/java/com/ritesh/animatexcompose/presentaions/details/demos/ShapeMorphingAnimation.kt"
        )
    )

    fun getAnimations(): Flow<List<AnimationItem>> {
        return flowOf(animations)
    }

    fun getAnimationById(id: Int): Flow<AnimationItem?> {
        return flowOf(animations.find { it.id == id })
    }
}