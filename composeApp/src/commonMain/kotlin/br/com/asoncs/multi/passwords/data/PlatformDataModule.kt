package br.com.asoncs.multi.passwords.data

import io.ktor.client.engine.HttpClientEngineFactory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

abstract class PlatformDataModule(
    internal val engine: HttpClientEngineFactory<*>
) {
    internal open val hostIdentify: String = ""
    internal open val hostToken: String = ""
    internal open val webApiKey: String = ""

    // region DataStore

    protected val dataStoreFileName = "dice.preferences_pb"

    internal open val dataStore = object : DataStore {}

    interface DataStore {
        interface Preference<T> {
            suspend fun get(): T? = null
            suspend fun set(
                value: T?
            ) {
            }
        }

        class PreferenceJson<T>(
            dataStore: DataStore,
            val json: Json,
            key: String
        ) {
            val preference = dataStore
                .createPreference(
                    String::class,
                    key
                )

            suspend inline fun <reified T : Any> get(): T? = preference
                .get()
                ?.let {
                    json.decodeFromString(it)
                }

            suspend inline fun <reified T : Any> set(
                value: T?
            ) = preference.set(
                value?.let {
                    json.encodeToString(it)
                }
            )
        }

        fun <T : Any> createPreference(
            cls: KClass<T>,
            key: String
        ): Preference<T> = object : Preference<T> {}
    }

    // endregion
}
