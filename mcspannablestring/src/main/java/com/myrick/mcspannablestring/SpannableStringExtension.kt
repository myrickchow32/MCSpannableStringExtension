package com.myrick.mcspannablestring

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.MaskFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Layout
import android.text.SpannableString
import android.text.style.*
import androidx.annotation.RequiresApi
import java.util.*

fun SpannableString.applySpan(highlightStr: String, characterStyle: Any) = apply {
    val startIndex = indexOf(highlightStr)
    val endIndex = startIndex + highlightStr.length
    val isValidHighlighStr = startIndex != -1
    if (isValidHighlighStr) {
        setSpan(characterStyle, startIndex, endIndex, 0)
    }
}

// TextSize
fun SpannableString.toChangeAbsoluteTextSize(highlightStr: String, textSizeInDp: Int): SpannableString = applySpan(highlightStr, AbsoluteSizeSpan(textSizeInDp, true))
fun SpannableString.toChangeRelativeTextSize(highlightStr: String, multiplier: Float): SpannableString = applySpan(highlightStr, RelativeSizeSpan(multiplier))

// Text Coloring
fun SpannableString.toHighlightWithTextColor(highlightStr: String, color: Int): SpannableString = applySpan(highlightStr, ForegroundColorSpan(color))
fun SpannableString.toHighlightWithBackgroundColor(highlightStr: String, color: Int): SpannableString = applySpan(highlightStr, BackgroundColorSpan(color))
@RequiresApi(Build.VERSION_CODES.Q)
fun SpannableString.toHighlightWholeLineWithBackgroundColor(highlightStr: String, color: Int): SpannableString = applySpan(highlightStr, LineBackgroundSpan.Standard(color))

// TextStyle
fun SpannableString.toBold(highlightStr: String): SpannableString = applySpan(highlightStr, StyleSpan(Typeface.BOLD))
fun SpannableString.toItalic(highlightStr: String): SpannableString = applySpan(highlightStr, StyleSpan(Typeface.ITALIC))
fun SpannableString.toBoldItalic(highlightStr: String): SpannableString = applySpan(highlightStr, StyleSpan(Typeface.BOLD_ITALIC))
fun SpannableString.toNormal(highlightStr: String): SpannableString = applySpan(highlightStr, StyleSpan(Typeface.NORMAL))
fun SpannableString.toTextAppearanceSpan(highlightStr: String, context: Context, appearance: Int): SpannableString = applySpan(highlightStr, TextAppearanceSpan(context, appearance))
fun SpannableString.toUnderline(highlightStr: String): SpannableString = applySpan(highlightStr, UnderlineSpan())
fun SpannableString.toStrikeThrough(highlightStr: String): SpannableString = applySpan(highlightStr, StrikethroughSpan())
fun SpannableString.toSuperscript(highlightStr: String): SpannableString = applySpan(highlightStr, SuperscriptSpan())
fun SpannableString.toSubscript(highlightStr: String): SpannableString = applySpan(highlightStr, SubscriptSpan())

// TextAlignment
fun SpannableString.toAlignCenter(highlightStr: String): SpannableString = applySpan(highlightStr, AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER))
fun SpannableString.toAlignNormal(highlightStr: String): SpannableString = applySpan(highlightStr, AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL))
fun SpannableString.toAlignOpposite(highlightStr: String): SpannableString = applySpan(highlightStr, AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)) // Left-to-Right Vs Right-to-Left

/* Annotated by @UnsupportedAppUsage since API 29
  fun SpannableString.toAlignLeft(highlightStr: String): SpannableString = applySpan(highlightStr, AlignmentSpan.Standard(Layout.Alignment.ALIGN_LEFT))
  fun SpannableString.toAlignRight(highlightStr: String): SpannableString = applySpan(highlightStr, AlignmentSpan.Standard(Layout.Alignment.ALIGN_RIGHT))
  Reference about Layout.Alignment: https://developer.android.com/reference/android/text/style/AlignmentSpan.Standard
*/

// Text formatting
@RequiresApi(Build.VERSION_CODES.P)
fun SpannableString.toQuote(highlightStr: String, color: Int, stripeWidth: Int, gapWidth: Int): SpannableString = applySpan(highlightStr, QuoteSpan(color, stripeWidth, gapWidth))


/*
* Clickable link
* * Url MUST have schema "http" or "https"
* * fooTextViewInstance.movementMethod = LinkMovementMethod.getInstance(), else it is not clickable
* * Color of link: `android:textColorLink`
* Reference: https://developer.android.com/reference/android/text/style/ClickableSpan
 */
fun SpannableString.toAddUrl(highlightStr: String, url: String): SpannableString = applySpan(highlightStr, URLSpan(url))
fun SpannableString.toClickableWithUnderline(highlightStr: String, clickableSpan: ClickableSpan): SpannableString = applySpan(highlightStr, clickableSpan)
fun SpannableString.toClickableWithoutUnderline(highlightStr: String, clickableWithouUnderlineSpan: ClickableWithouUnderlineSpan): SpannableString = applySpan(highlightStr,
    clickableWithouUnderlineSpan)

// Leading decoration
fun SpannableString.toAddBullet(highlightStr: String, gapWidth: Int, color: Int, bulletRadius: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    applySpan(highlightStr, BulletSpan(gapWidth, color, bulletRadius))
} else {
    applySpan(highlightStr, BulletSpan(gapWidth, color))
}

fun SpannableString.toAddDrawableMargin(highlightStr: String, drawable: Drawable, pad: Int): SpannableString = applySpan(highlightStr, DrawableMarginSpan(drawable, pad))
fun SpannableString.toAddIcon(highlightStr: String, bitmap: Bitmap, pad: Int): SpannableString = applySpan(highlightStr, IconMarginSpan(bitmap, pad))
fun SpannableString.toAddImageDrawable(highlightStr: String, drawable: Drawable, verticalAlignment: Int): SpannableString = applySpan(highlightStr, ImageSpan(drawable, verticalAlignment))
fun SpannableString.toAddImageDrawable(highlightStr: String, context: Context, bitmap: Bitmap, verticalAlignment: Int): SpannableString = applySpan(highlightStr, ImageSpan(context, bitmap, verticalAlignment))
fun SpannableString.toAddLeadingMargin(highlightStr: String, leadingMarginInPx: Int): SpannableString = applySpan(highlightStr, LeadingMarginSpan.Standard(leadingMarginInPx))

@RequiresApi(Build.VERSION_CODES.Q)
fun SpannableString.toChangeLineHeight(highlightStr: String, heightInPx: Int): SpannableString = applySpan(highlightStr, LineHeightSpan.Standard(heightInPx))
fun SpannableString.toChangeLocale(highlightStr: String, locale: Locale): SpannableString = applySpan(highlightStr, LocaleSpan(locale))
fun SpannableString.toAddMaskFilter(highlightStr: String, filter: MaskFilter): SpannableString = applySpan(highlightStr, MaskFilterSpan(filter))
fun SpannableString.toChangeTabStop(highlightStr: String, offset: Int): SpannableString = applySpan(highlightStr, TabStopSpan.Standard(offset))
fun SpannableString.toScaleX(highlightStr: String, proportion: Float): SpannableString = applySpan(highlightStr, ScaleXSpan(proportion))
fun SpannableString.toAddSuggestions(highlightStr: String, locale: Locale, suggestionStringArray: Array<String>, flag: Int): SpannableString = applySpan(highlightStr, SuggestionSpan(locale, suggestionStringArray, flag))

@TargetApi(Build.VERSION_CODES.P)
fun SpannableString.toChangeTypeface(highlightStr: String, typeface: Typeface): SpannableString = applySpan(highlightStr, TypefaceSpan(typeface))
fun SpannableString.toChangeTypeface(highlightStr: String, family: String): SpannableString = applySpan(highlightStr, TypefaceSpan(family))

