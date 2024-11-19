package com.example.quicksearch.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.quicksearch.model.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val ItemType = object : NavType<Item>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Item? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Item {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Item): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Item) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}