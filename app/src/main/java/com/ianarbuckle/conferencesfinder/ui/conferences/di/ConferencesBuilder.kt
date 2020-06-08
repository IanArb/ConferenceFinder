package com.ianarbuckle.conferencesfinder.ui.conferences.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ianarbuckle.conferencesfinder.di.AppComponent
import com.ianarbuckle.conferencesfinder.di.ViewModelFactoryModule
import com.ianarbuckle.conferencesfinder.di.ViewModelKey
import com.ianarbuckle.conferencesfinder.ui.conferences.ConferencesFragment
import com.ianarbuckle.conferencesfinder.ui.conferences.usecase.ConferencesUseCase
import com.ianarbuckle.conferencesfinder.ui.conferences.view.ConferencesView
import com.ianarbuckle.conferencesfinder.ui.conferences.viewmodel.ConferencesViewModel
import conferences.repository.ConferencesRepository
import dagger.*
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConferencesScope

@Module
class ConferencesModule(private val fragment: ConferencesFragment) {

    @ConferencesScope
    @Provides
    fun provideContext(): Context = fragment.requireContext()

    @ConferencesScope
    @Provides
    fun provideRepository(): ConferencesRepository = ConferencesRepository()

    @ConferencesScope
    @Provides
    fun provideUseCase(repository: ConferencesRepository): ConferencesUseCase = ConferencesUseCase(repository)

    @ConferencesScope
    @Provides
    fun provideView(context: Context): ConferencesView = ConferencesView(context)

}

@Module
abstract class ConferencesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConferencesViewModel::class)
    abstract fun bindsViewModel(conferencesViewModel: ConferencesViewModel): ViewModel

}

@ConferencesScope
@Component(modules = [ConferencesModule::class, ViewModelFactoryModule::class, ConferencesViewModelModule::class], dependencies = [AppComponent::class])
interface ConferencesComponent {
    fun inject(fragment: ConferencesFragment)
}