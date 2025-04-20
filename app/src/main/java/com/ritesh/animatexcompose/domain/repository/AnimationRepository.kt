package com.ritesh.animatexcompose.domain.repository

import com.ritesh.animatexcompose.domain.model.AnimationItem
import kotlinx.coroutines.flow.Flow

interface AnimationRepository {
    fun getAnimations(): Flow<List<AnimationItem>>
    fun getAnimationById(id: Int): Flow<AnimationItem?>
}