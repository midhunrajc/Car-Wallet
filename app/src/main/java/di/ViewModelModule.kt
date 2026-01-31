package di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: HiltViewModelFactory
    ): ViewModelProvider.Factory
}
