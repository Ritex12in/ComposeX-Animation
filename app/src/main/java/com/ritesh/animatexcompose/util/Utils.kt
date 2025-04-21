package com.ritesh.animatexcompose.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import com.ritesh.animatexcompose.domain.model.AnimationItem

object Utils {
    fun shareAnimation(context: Context, animation: AnimationItem) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this ${animation.title} in Jetpack Compose!")
            putExtra(Intent.EXTRA_TEXT, "I found this cool ${animation.title} in the AnimateX Compose app:\nLink: ${animation.gitHubLink}\n\n${animation.description}\n\nExplore more animations in the AnimateX Compose app!")
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Animation"))
    }

    fun openGithubLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }

        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "No app found to open this link", Toast.LENGTH_SHORT).show()
        }
    }

}