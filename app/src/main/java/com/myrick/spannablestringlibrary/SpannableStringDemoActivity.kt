package com.myrick.spannablestringlibrary

import android.app.PendingIntent
import android.content.Intent
import android.content.res.Resources
import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.SpannableString
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_spannable_string_demo.*
import java.util.*
import android.text.Spannable
import android.view.View.OnLongClickListener
import android.text.InputType
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.myrick.mcspannablestring.*


class SpannableStringDemoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_spannable_string_demo)

    /*
    * Note: Span is drawn according to its order
    * i.e. toAddBullet() -> toAddIcon()
    *      Bullet is added first and followed by the icon
     */

    resources.configuration.setLocale(Locale.TRADITIONAL_CHINESE)
    val drawable = ContextCompat.getDrawable(this, R.drawable.hong_kong_icon_20_20)!!
    drawable.setBounds(0, 0, 100, 100)
    val color = ContextCompat.getColor(this, R.color.cornflower_blue)

    addCategoryTitle("TextSize")
    applySpan(SpannableString("AbsoluteSizeSpan").toChangeAbsoluteTextSize("AbsoluteSizeSpan", 20))
    applySpan(SpannableString("RelativeSizeSpan").toChangeRelativeTextSize("RelativeSizeSpan", 2f))
    val porportion = 2f
    applySpan(SpannableString("ScaleXSpan").toScaleX("ScaleXSpan", porportion))

    addCategoryTitle("Text Colouring")
    applySpan(SpannableString("ForegroundColorSpan").toHighlightWithTextColor("ForegroundColorSpan", ContextCompat.getColor(this, android.R.color.holo_blue_light)))
    applySpan(SpannableString("BackgroundColorSpan").toHighlightWithBackgroundColor("BackgroundColorSpan", ContextCompat.getColor(this, R.color.cornflower_blue_50)))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      applySpan(SpannableString("Android is a mobile operating system based on a modified version of the Linux kernel and other open source software.").toHighlightWholeLineWithBackgroundColor("version", ContextCompat.getColor(this, R.color.cornflower_blue_50)))
    }

    addCategoryTitle("Text Styling")
    applySpan(SpannableString("StyleSpan (Bold)").toBold("StyleSpan (Bold)").toNormal("StyleSpan (Bold)"))
    applySpan(SpannableString("StyleSpan (Italic)").toItalic("StyleSpan (Italic)"))
    applySpan(SpannableString("StyleSpan (Bold & Italic)").toBoldItalic("StyleSpan (Bold & Italic)"))
    applySpan(SpannableString("StyleSpan (Normal)").toNormal("StyleSpan (Normal)"))
    applySpan(SpannableString("UnderlineSpan").toUnderline("UnderlineSpan"))
    applySpan(SpannableString("StrikethroughSpan").toStrikeThrough("StrikethroughSpan"))
    applySpan(SpannableString("SuperscriptSpan1").toSuperscript("1"))
    applySpan(SpannableString("SubscriptSpan2").toSubscript("2"))
    applySpan(SpannableString("TextAppearanceSpan").toTextAppearanceSpan("TextAppearanceSpan", this, R.style.DemoTextViewTextAppearance))
    applySpan(SpannableString("AlignmentSpan.Standard (NOTHING)"))
    applySpan(SpannableString("Typeface").toChangeTypeface("Typeface", Typeface.create(ResourcesCompat.getFont(this, R.font.vibes), Typeface.BOLD)))
    applySpan(SpannableString("Typeface").toChangeTypeface("Typeface", "monospace"))
    val blurRadius = 5f
    val blurMaskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID)
    applySpan(SpannableString("MaskFilterSpan").toAddMaskFilter("MaskFilterSpan", blurMaskFilter))
    applySpan(SpannableString("ALIGN_NORMAL").toAlignNormal("ALIGN_NORMAL"))
    applySpan(SpannableString("ALIGN_CENTER").toAlignCenter("ALIGN_CENTER"))
    applySpan(SpannableString("ALIGN_OPPOSITE").toAlignOpposite("ALIGN_OPPOSITE"))

    addCategoryTitle("Clickable link")
    // Note: url must have schema "http://" or "https://"
    applySpan(SpannableString("URLSpan").toAddUrl("URLSpan", "https://www.google.com")).also {
      it.movementMethod = LinkMovementMethod.getInstance()
    }
    applySpan(SpannableString("ClickableSpan").toClickableWithUnderline("ClickableSpan", object : ClickableSpan() {
      override fun onClick(p0: View) {
        Log.d("TAG_MC", "clickableTextView onClick")
      }
    })).also {
      it.movementMethod = LinkMovementMethod.getInstance()
    }
    applySpan(SpannableString("ClickableSpan").toClickableWithoutUnderline("ClickableSpan", object:
      ClickableWithouUnderlineSpan() {
      override fun onClick(p0: View) {
        Log.d("TAG_MC", "clickableTextView onClick")
      }
    })).also {
      it.movementMethod = LinkMovementMethod.getInstance()
    }

    addCategoryTitle("Leading Decoration")
    applySpan(SpannableString("[ReplaceableTag]ImageSpan (ALIGN_BASELINE)").toAddImageDrawable("[ReplaceableTag]", drawable, DynamicDrawableSpan.ALIGN_BASELINE))
//    applySpan(SpannableString("[ReplaceableTag]ImageSpan (ALIGN_BASELINE)").toAddImageDrawable("[ReplaceableTag]", this, ContextCompat.getDrawable(this, R.drawable.hong_kong_icon_20_20)!!.toBitmap(), DynamicDrawableSpan.ALIGN_BASELINE))
//    applySpan(SpannableString("[ReplaceableTag]ImageSpan (ALIGN_BOTTOM)").toAddImageDrawable("[ReplaceableTag]", this, ContextCompat.getDrawable(this, R.drawable.hong_kong_icon_20_20)!!.toBitmap(), DynamicDrawableSpan.ALIGN_BOTTOM))
//    // Only at API 29
//    applySpan(SpannableString("[ReplaceableTag]ImageSpan (ALIGN_CENTER)").toAddImageDrawable("[ReplaceableTag]", this, ContextCompat.getDrawable(this, R.drawable.hong_kong_icon_20_20)!!.toBitmap(), DynamicDrawableSpan.ALIGN_CENTER))
    applySpan(SpannableString("BulletSpan").toAddBullet("BulletSpan", 8.toPx(), ContextCompat.getColor(this, R.color.cornflower_blue), 8.toPx()))
    val drawablePad = 40 // Px
    applySpan(SpannableString("DrawableMarginSpan").toAddDrawableMargin("DrawableMarginSpan", drawable, drawablePad))
    val iconPad = 40 // Px
    applySpan(SpannableString("IconMarginSpan").toAddIcon("IconMarginSpan", drawable.toBitmap(), iconPad))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      val stripeWidth = 4.toPx()
      val gapWidth = 16.toPx()
      applySpan(SpannableString("QuoteSpan").toQuote("QuoteSpan", color, stripeWidth, gapWidth))
    }
    val leadingMarginInPx = 32.toPx()
    applySpan(SpannableString("LeadingMarginSpan.Standard").toAddLeadingMargin("LeadingMarginSpan.Standard", leadingMarginInPx))
    val offset = 40.toPx()
    applySpan(SpannableString("\tTabStopSpan.Standard").toChangeTabStop("\tTabStopSpan.Standard", offset))

    addCategoryTitle("Other Span")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      val lineHeightInPx = 100
      applySpan(SpannableString("LineHeightSpan").toChangeLineHeight("LineHeightSpan", lineHeightInPx))
    }
    val string = "今天下微雨" // 骨底条今直沿微
    localeTraditionalChineseTextView.text = SpannableString(string).toChangeLocale(string, Locale.TRADITIONAL_CHINESE)
    localeSimpliedChineseTextView.text = SpannableString(string).toChangeLocale(string, Locale.SIMPLIFIED_CHINESE)
    applySpan(SpannableString(string).toChangeLocale(string, Locale.TRADITIONAL_CHINESE))
    applySpan(SpannableString(string).toChangeLocale(string, Locale.SIMPLIFIED_CHINESE))

    val suggestionArray = arrayOf("Suggestion 1", "Suggestion 2", "Suggestion 3", "Suggestion 4", "Suggestion 5", "Suggestion 6", "Suggestion 7", "Suggestion 8", "Suggestion 9", "Suggestion 10")
    editText.setText(SpannableString("Original").toAddSuggestions("Original", Locale.TRADITIONAL_CHINESE, suggestionArray, SuggestionSpan.FLAG_MISSPELLED))
  }

  fun applySpan(span: SpannableString): TextView {
    val textView = TextView(this)
    textView.text = span
    textView.textSize = 14.toFloat()
    textView.setPadding(0, 4.toPx(), 0, 4.toPx())
    linearLayout.addView(textView)
    textView.layoutParams = (textView.layoutParams as ViewGroup.MarginLayoutParams).also {
      it.width = ViewGroup.LayoutParams.MATCH_PARENT
      it.setMargins(0, 16, 0, 0)
    }
    return textView
  }

  fun addCategoryTitle(titleString: String): TextView {
    val textView = TextView(this)
    textView.textSize = 32.toFloat()
    textView.text = titleString
    textView.setTypeface(textView.typeface, BOLD)
    linearLayout.addView(textView)

    val view = View(this)
    view.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
    linearLayout.addView(view)
    view.layoutParams = view.layoutParams.also {
      it.height = 2.toPx()
    }

    return textView
  }
}

fun Int.toPx(): Int {
  val metrics = Resources.getSystem().getDisplayMetrics()
  val px = this * (metrics.densityDpi / 160f)
  return Math.round(px)
}
