
# MCSpannableStringExtension

**MCSpannableStringExtension** is an easy to use library to add `Spans` to `SpannableString`. It uses Kotlin extension to extend the functionality of `SpannableString`.

To understanding more about the `Spans` included in this library, please refer to this Medium post -- <a href="https://medium.com/@myrickchow32/android-official-spans-all-in-one-6d23167b1bb9">Android Official Spans All-In-One</a>.

# Getting Started
1. Add JitPack to  build.gradle (project)
 ```kotlin
allprojects {  
    repositories {   
        maven { url 'https://jitpack.io' }  
    }  
} 
 ```
2. Add `SpannableStringExtension` to build.gradle (module)
```
dependencies {  
  implementation 'com.github.myrickchow32:SpannableStringExtension:1.0.1'  
}
```

# Supported Spans list
**TextSize**
 1. AbsoluteSizeSpan
 2. RelativeSizeSpan
 3. ScaleXSpan

**Text Colouring**

1. ForegroundColorSpan
2. BackgroundColorSpan
3. LineBackgroundSpan


**Text Styling**
1. StyleSpan
2. UnderlineSpan
3. StrikethroughSpan
4. SuperscriptSpan
5. SubscriptSpan
6. TextAppearanceSpan
7. TypefaceSpan
8. MaskFilterSpan
9. AlignmentSpan

**Clickable link**
1. URLSpan
2. ClickableSpan
3. ClickableWithoutUnderlineSpan (CustomSpan)
 
**Leading Decoration**
1. ImageSpan
2. BulletSpan
3. DrawableMarginSpan
4. IconMarginSpan
5. QuoteSpan
6. LeadingMarginSpan
7. TabStopSpan

**Other Span**
1. LocaleSpan
2. SuggestionSpan
3. LineHeightSpan

# Example

## 1. Change the foreground color (text color):

![Result](https://miro.medium.com/max/411/1*evuhOKV1HVq107vj8wd-0g.png)
```kotlin
val color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
val spannableString = SpannableString("ForegroundColorSpan")
						.toHighlightWithTextColor("ForegroundColorSpan", color)
textViewInstance.text = spannableString
```

## 2. Nested `Spans`:

![enter image description here](https://firebasestorage.googleapis.com/v0/b/baseandroid-247513.appspot.com/o/Screenshot%202020-04-20%20at%206.44.25%20PM.png?alt=media&token=3d45db25-b7e7-498f-ba2f-8f51df20365d)
```kotlin
val color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
val demoString = " Demo String "  
val spannableString = SpannableString(demoString)  
					  .toHighlightWithBackgroundColor(demoString, color)  
					  .toUnderline(demoString)  
					  .toItalic(demoString)  
textViewInstance.text = spannableString
```

# Author
Name: Myrick Chow

Medium: [myrickchow32](https://medium.com/@myrickchow32)

Twitter: [myrick_chow](https://twitter.com/myrick_chow)


