package com.ritesh.animatexcompose.util

import android.content.Context
import android.content.Intent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.ritesh.animatexcompose.domain.model.AnimationItem

object Utils {
    fun shareAnimation(context: Context, animation: AnimationItem) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this ${animation.title} in Jetpack Compose!")
            putExtra(Intent.EXTRA_TEXT, "I found this cool ${animation.title} in the Animation Demos app:\n\n${animation.description}\n\nExplore more animations in the Animation Demos app!")
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Animation"))
    }

    // Simple Kotlin syntax highlighter
    fun highlightKotlinSyntax(code: String): AnnotatedString {
        val keywords = setOf(
            "fun", "val", "var", "if", "else", "when", "for", "while", "do", "return",
            "true", "false", "null", "class", "object", "interface", "override", "private",
            "public", "internal", "protected", "import", "package", "by", "lazy", "remember",
            "composable", "suspend", "companion"
        )

        val annotations = setOf(
            "@Composable", "@OptIn", "@HiltViewModel", "@Inject", "@Module",
            "@InstallIn", "@Singleton", "@Binds"
        )

        return buildAnnotatedString {
            val text = code.split(" ", "\n", "(", ")", "{", "}", "[", "]", ",", ":", ";", ".")

            var currentPosition = 0
            for (word in text) {
                val start = code.indexOf(word, currentPosition)
                if (start == -1) continue

                val end = start + word.length

                when {
                    word in keywords -> {
                        withStyle(SpanStyle(color = androidx.compose.ui.graphics.Color.Blue)) {
                            append(code.substring(currentPosition, start))
                            append(word)
                        }
                    }
                    word in annotations -> {
                        withStyle(SpanStyle(color = androidx.compose.ui.graphics.Color.Green)) {
                            append(code.substring(currentPosition, start))
                            append(word)
                        }
                    }
                    word.matches(Regex("\".*\"")) -> {
                        withStyle(SpanStyle(color = androidx.compose.ui.graphics.Color(0xFFA31515))) {
                            append(code.substring(currentPosition, start))
                            append(word)
                        }
                    }
                    word.matches(Regex("[0-9]+")) -> {
                        withStyle(SpanStyle(color = androidx.compose.ui.graphics.Color.Magenta)) {
                            append(code.substring(currentPosition, start))
                            append(word)
                        }
                    }
                    else -> {
                        append(code.substring(currentPosition, end))
                    }
                }

                currentPosition = end
            }

            // Append any remaining text
            if (currentPosition < code.length) {
                append(code.substring(currentPosition))
            }
        }
    }
}