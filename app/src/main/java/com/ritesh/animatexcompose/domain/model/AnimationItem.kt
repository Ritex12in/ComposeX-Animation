package com.ritesh.animatexcompose.domain.model

data class AnimationItem(
    val id: Int,
    val title: String,
    val description: String,
    val previewTitle: String,
    val animationType: AnimationType,
    val codeSnippet: String
)