package com.ritesh.animatexcompose.data.repository

import com.ritesh.animatexcompose.data.source.AnimationDataSource
import com.ritesh.animatexcompose.domain.model.AnimationItem
import com.ritesh.animatexcompose.domain.repository.AnimationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimationRepositoryImpl @Inject constructor(
    private val dataSource: AnimationDataSource
) : AnimationRepository {
    override fun getAnimations(): Flow<List<AnimationItem>> {
        return dataSource.getAnimations()
    }

    override fun getAnimationById(id: Int): Flow<AnimationItem?> {
        return dataSource.getAnimationById(id)
    }
}