package com.ritesh.animatexcompose.presentaions.details.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ritesh.animatexcompose.R
import com.ritesh.animatexcompose.domain.model.AnimationType
import com.ritesh.animatexcompose.presentaions.details.demos.BounceAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.ColorChangeAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.ExpandAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.FadeAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.Loader1AnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.Loader2AnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.PulseAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.RotateAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.ScaleAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.ShimmerAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.SlideAnimationPreview
import com.ritesh.animatexcompose.presentaions.details.demos.Splash1AnimationPreview
import com.ritesh.animatexcompose.presentaions.details.viewmodel.DetailsUiState
import com.ritesh.animatexcompose.presentaions.details.viewmodel.DetailsViewModel
import com.ritesh.animatexcompose.util.Utils.openGithubLink
import com.ritesh.animatexcompose.util.Utils.shareAnimation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onNavigateBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    when (val state = uiState) {
                        is DetailsUiState.Success -> Text(state.animation.title)
                        else -> Text("Animation Details")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        when (val state = uiState) {
            is DetailsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is DetailsUiState.Success -> {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Preview title
                        Text(
                            text = state.animation.previewTitle,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Animation preview
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                AnimationPreview(state.animation.id)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Description
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = state.animation.description,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        HorizontalDivider()

                        Spacer(modifier = Modifier.height(24.dp))

                        // Action buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Button(onClick = { shareAnimation(context, state.animation) }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(text = "Share")
                            }

                            Button(onClick = { openGithubLink(context, state.animation.gitHubLink) }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.baseline_code_24),
                                    contentDescription = "View Code",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(text = "Code")
                            }
                        }
                    }
                }
            }
            is DetailsUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun AnimationPreview(id: Int) {
    when (id) {
        1 -> FadeAnimationPreview()
        2 -> ScaleAnimationPreview()
        3 -> RotateAnimationPreview()
        4 -> SlideAnimationPreview()
        5 -> ColorChangeAnimationPreview()
        6 -> ShimmerAnimationPreview()
        7 -> BounceAnimationPreview()
        8 -> PulseAnimationPreview()
        9 -> ExpandAnimationPreview()
        10 -> Splash1AnimationPreview()
        11 -> Loader1AnimationPreview()
        12-> Loader2AnimationPreview()
    }
}