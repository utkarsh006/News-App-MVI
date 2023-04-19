package tech.okcredit.base.delegates

import androidx.lifecycle.SingleViewModelProviderImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified T : ViewModel> ViewModelStoreOwner.singleViewModelProvider(
    noinline factory: () -> T,
) = SingleViewModelProviderImpl(viewModelStore, factory, T::class.java)
