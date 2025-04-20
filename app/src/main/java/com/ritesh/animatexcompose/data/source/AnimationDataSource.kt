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
            codeSnippet = """
                @Composable
                fun FadeAnimation() {
                    var visible by remember { mutableStateOf(true) }
                    val alpha by animateFloatAsState(
                        targetValue = if (visible) 1f else 0f,
                        animationSpec = tween(durationMillis = 1000)
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .alpha(alpha)
                                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(onClick = { visible = !visible }) {
                            Text(text = if (visible) "Fade Out" else "Fade In")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 2,
            title = "Scale Animation",
            description = "An animation that changes the size of a component. Scale animations can create emphasis, add playfulness, or guide user attention to specific elements.",
            previewTitle = "Scale Up/Down",
            animationType = AnimationType.SCALE,
            codeSnippet = """
                @Composable
                fun ScaleAnimation() {
                    var expanded by remember { mutableStateOf(false) }
                    val scale by animateFloatAsState(
                        targetValue = if (expanded) 1.5f else 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .scale(scale)
                                .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp))
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(onClick = { expanded = !expanded }) {
                            Text(text = if (expanded) "Scale Down" else "Scale Up")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 3,
            title = "Rotate Animation",
            description = "An animation that rotates a component around its center. Rotation can be used for loading indicators, toggling states, or adding visual interest to UI elements.",
            previewTitle = "Rotate Element",
            animationType = AnimationType.ROTATE,
            codeSnippet = """
                @Composable
                fun RotateAnimation() {
                    var rotated by remember { mutableStateOf(false) }
                    val rotation by animateFloatAsState(
                        targetValue = if (rotated) 360f else 0f,
                        animationSpec = tween(durationMillis = 1000)
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .rotate(rotation)
                                .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Rotate",
                                tint = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(onClick = { rotated = !rotated }) {
                            Text(text = "Rotate")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 4,
            title = "Slide Animation",
            description = "An animation that moves a component from one position to another. Slide animations are effective for transitions, revealing content, or shifting elements into view.",
            previewTitle = "Slide In/Out",
            animationType = AnimationType.SLIDE,
            codeSnippet = """
                @Composable
                fun SlideAnimation() {
                    var visible by remember { mutableStateOf(true) }
                    val offsetX by animateDpAsState(
                        targetValue = if (visible) 0.dp else 300.dp,
                        animationSpec = tween(durationMillis = 500)
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .offset(x = offsetX)
                                .size(200.dp, 100.dp)
                                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Slide Animation",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(onClick = { visible = !visible }) {
                            Text(text = if (visible) "Slide Out" else "Slide In")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 5,
            title = "Color Change Animation",
            description = "An animation that smoothly transitions from one color to another. Color animations can indicate state changes, highlight important elements, or create visual effects.",
            previewTitle = "Color Transition",
            animationType = AnimationType.COLOR_CHANGE,
            codeSnippet = """
                @Composable
                fun ColorChangeAnimation() {
                    var colorChanged by remember { mutableStateOf(false) }
                    val color by animateColorAsState(
                        targetValue = if (colorChanged) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                        animationSpec = tween(durationMillis = 1000)
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .background(color, shape = RoundedCornerShape(16.dp))
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(onClick = { colorChanged = !colorChanged }) {
                            Text(text = "Change Color")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 6,
            title = "Shimmer Animation",
            description = "A shimmering effect animation commonly used for loading states. Shimmer creates a sense of activity and reassures users that content is loading.",
            previewTitle = "Shimmer Effect",
            animationType = AnimationType.SHIMMER,
            codeSnippet = """
                @Composable
                fun ShimmerAnimation() {
                    val shimmerColors = listOf(
                        Color.LightGray.copy(alpha = 0.6f),
                        Color.LightGray.copy(alpha = 0.2f),
                        Color.LightGray.copy(alpha = 0.6f)
                    )
                    
                    val transition = rememberInfiniteTransition()
                    val translateAnim by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1000f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 1200, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                    
                    val brush = Brush.linearGradient(
                        colors = shimmerColors,
                        start = Offset(translateAnim, translateAnim),
                        end = Offset(translateAnim + 100f, translateAnim + 100f),
                        tileMode = TileMode.Mirror
                    )
                    
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Simulate a card with shimmer effect
                        repeat(3) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(brush, RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 7,
            title = "Bounce Animation",
            description = "A fun, playful animation that creates a bouncing effect. Bounce animations can add character to a UI and draw attention to particular elements.",
            previewTitle = "Bouncing Element",
            animationType = AnimationType.BOUNCE,
            codeSnippet = """
                @Composable
                fun BounceAnimation() {
                    var bouncing by remember { mutableStateOf(false) }
                    val offsetY by animateFloatAsState(
                        targetValue = if (bouncing) -50f else 0f,
                        animationSpec = repeatable(
                            iterations = 5,
                            animation = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        finishedListener = { bouncing = false }
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .offset(y = offsetY.dp)
                                .size(100.dp)
                                .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Bounce",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(100.dp))
                        
                        Button(onClick = { bouncing = true }) {
                            Text(text = "Bounce")
                        }
                    }
                }
            """.trimIndent()
        ),
        AnimationItem(
            id = 8,
            title = "Pulse Animation",
            description = "A pulsing effect that alternates between expanding and contracting. Pulse animations can draw attention to notifications, alerts, or important UI elements.",
            previewTitle = "Pulsing Element",
            animationType = AnimationType.PULSE,
            codeSnippet = """
                @Composable
                fun PulseAnimation() {
                    val infiniteTransition = rememberInfiniteTransition()
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.2f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    val alpha by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 0.6f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .scale(scale)
                                .alpha(alpha)
                                .background(MaterialTheme.colorScheme.error, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Pulse",
                                tint = MaterialTheme.colorScheme.onError,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Text(
                            text = "Pulsing Notification",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            """.trimIndent()
        )
    )

    fun getAnimations(): Flow<List<AnimationItem>> {
        return flowOf(animations)
    }

    fun getAnimationById(id: Int): Flow<AnimationItem?> {
        return flowOf(animations.find { it.id == id })
    }
}