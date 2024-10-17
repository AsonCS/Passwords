@file:Suppress("UNCHECKED_CAST")

package br.com.asoncs.multi.passwords.data

import androidx.datastore.preferences.core.*
import br.com.asoncs.multi.passwords.data.PlatformDataModule.DataStore
import io.ktor.client.engine.apache5.Apache5
import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath
import kotlin.reflect.KClass

internal actual val platform = object : PlatformDataModule(
    engine = Apache5
) {
    override val dataStore by lazy {
        getDataStore(dataStoreFileName)
    }
}

private fun getDataStore(
    fileName: String
) = object : DataStore {

    private val _dataStore = PreferenceDataStoreFactory
        .createWithPath(
            produceFile = {
                fileName.toPath()
            }
        )

    override fun <T : Any> createPreference(
        cls: KClass<T>,
        key: String
    ) = object : DataStore.Preference<T> {

        private val preferenceKey = when (cls) {
            Boolean::javaClass -> booleanPreferencesKey(key)
            Int::javaClass -> intPreferencesKey(key)
            else -> stringPreferencesKey(key)
        } as Preferences.Key<T>

        override suspend fun set(
            value: T?
        ) {
            _dataStore.edit { settings ->
                if (value == null) {
                    settings.remove(preferenceKey)
                } else {
                    settings[preferenceKey] = value
                }
            }
        }

        override suspend fun get() = _dataStore
            .data
            .first()[preferenceKey]
    }

}
