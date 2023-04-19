//package com.example.myapplication.ui
//import com.example.myapplication.base.CoroutineViewModel
//import com.example.myapplication.base.getCoroutineViewModel
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.FlowPreview
//import javax.inject.Provider
//
//@Module
//@InstallIn(SingletonComponent::class)
//object HomeModule {
//
//        @Provides
//        fun initialState(): HomeContract.State {
//            return HomeContract.State()
//        }
//
//        @Provides
//        fun viewModel(activity: MainActivity, viewModelProvider: Provider<MainViewModel>,
//        ): CoroutineViewModel<HomeContract.State, HomeContract.ViewEvent, HomeContract.Intent> =
//            activity.getCoroutineViewModel(viewModelProvider)
//}
