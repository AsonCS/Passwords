package br.com.asoncs.multi.passwords.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Dao : KoinComponent {
    protected val dataStore by inject<DataStore<Preferences>>()
    protected val json by inject<Json>()

    protected suspend inline fun <reified T : Any> Dao.get(
        key: Preferences.Key<String>,
        dataStore: DataStore<Preferences> = this.dataStore,
        json: Json = this.json
    ): T? = dataStore
        .data
        .first()[key]
        ?.let {
            json.decodeFromString(it)
        }

    protected suspend inline fun <reified T : Any> Dao.set(
        key: Preferences.Key<String>,
        value: T?,
        dataStore: DataStore<Preferences> = this.dataStore,
        json: Json = this.json
    ) {
        dataStore.edit { settings ->
            if (value == null) {
                settings.remove(key)
            } else {
                settings[key] = json.encodeToString(value)
            }
        }
    }
}
