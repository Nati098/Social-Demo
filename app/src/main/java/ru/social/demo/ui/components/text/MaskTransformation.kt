package ru.social.demo.ui.components.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue


class MaskTransformation(
    private val mask: String,
    private val maxLength: Int = Int.MAX_VALUE
) : VisualTransformation {

    private val maskSym = '#'
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != maskSym }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        val textTrim = if (text.text.length >= maxLength) text.text.substring(0..< maxLength) else text.text
        textTrim.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == maskSym) numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int =
            mask.take(offset.absoluteValue).count { it == maskSym }

    }


    companion object {

        const val DATA_MASK = "##.##.####"
        const val DATA_MASK_COUNT = 8

    }

}
