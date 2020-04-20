package com.myrick.mcspannablestring

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View


abstract class ClickableWithouUnderlineSpan : ClickableSpan() {
  override fun updateDrawState(ds: TextPaint) {
    super.updateDrawState(ds)
    ds.isUnderlineText = false // set to false to remove underline
  }
}
