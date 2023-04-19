@file:Suppress("PackageDirectoryMismatch")

package androidx.lifecycle

import androidx.annotation.MainThread
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class SingleViewModelProviderImpl<VM : ViewModel>(
    private val viewModelStore: ViewModelStore,
    private val factory: () -> VM,
    modelClass: Class<VM>,
) {

    private val key = "$DEFAULT_KEY:${modelClass.canonicalName}"

    @MainThread
    fun get(): VM = viewModelStore.get(key) as? VM
        ?: factory.invoke().also { viewModelStore.put(key, it) }

    operator fun getValue(thisRef: VM?, property: KProperty<*>): VM = this.get()

    companion object {
        private const val DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey"
    }
}
