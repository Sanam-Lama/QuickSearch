package com.example.quicksearch

import android.os.Build
import android.text.Spanned
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {

    private const val DATE_FORMAT =  "MMM-dd-yyyy"

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(input: String): String {

        val zonedDateTime = ZonedDateTime.parse(input, DateTimeFormatter.ISO_ZONED_DATE_TIME)

        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

        val formattedDate = zonedDateTime.format(formatter)

        return formattedDate
    }

    fun toHtmlString(input: String): Spanned {
       return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}