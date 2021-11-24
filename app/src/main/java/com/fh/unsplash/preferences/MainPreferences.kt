package com.fh.unsplash.preferences

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first

class MainPreferences(context: Context) {

    private val dataStore: DataStore<Preferences> =
        context.createDataStore(name = PREF_NAME)


    suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit {
            it[dataStoreKey] = value
        }
    }


    suspend fun readValue(key: String): String? {
        val datStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[datStoreKey]
    }


    companion object {
        const val PREF_NAME = "filter"
    }

}