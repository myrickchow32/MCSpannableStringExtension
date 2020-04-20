package com.myrick.mcspannablestring

// String Extension
fun String.getOccuranceIndexList(tagString: String): List<Int> {
  val TAG_STRING_NOT_FOUND = -1
  val occuranceIndexList = arrayListOf<Int>()

  var startIndex = 0
  (0..this.length - 1).forEach {
    val indexOfTagString = this.indexOf(tagString, startIndex)
    val isTagStringFound = indexOfTagString != TAG_STRING_NOT_FOUND
    if (isTagStringFound) {
      occuranceIndexList.add(indexOfTagString)
      startIndex = indexOfTagString + tagString.length
    } else {
      return occuranceIndexList
    }
  }
  return occuranceIndexList
}
