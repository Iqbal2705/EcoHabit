
package com.example.mobile_iqbal.Activity

import android.content.Context

object ActivityStore {

    private const val PREF_NAME = "activity_store"
    private const val KEY_FAVORITES = "favorites"
    private const val KEY_HISTORY = "history"

    private fun preferences(context: Context) =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    fun getFavorites(context: Context): Set<String> {
        return preferences(context)
            .getStringSet(KEY_FAVORITES, emptySet())
            ?.toSet() ?: emptySet()
    }

    fun toggleFavorite(context: Context, nama: String) {
        val prefs = preferences(context)
        val favorites = getFavorites(context).toMutableSet()

        if (favorites.contains(nama)) {
            favorites.remove(nama)
        } else {
            favorites.add(nama)
        }

        prefs.edit()
            .putStringSet(KEY_FAVORITES, favorites)
            .apply()
    }

    fun getHistory(context: Context): Set<String> {
        return preferences(context)
            .getStringSet(KEY_HISTORY, emptySet())
            ?.toSet() ?: emptySet()
    }

    fun addHistory(context: Context, nama: String) {
        val history = getHistory(context).toMutableSet()
        history.add(nama)

        preferences(context).edit()
            .putStringSet(KEY_HISTORY, history)
            .apply()
    }
}
