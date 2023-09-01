package no.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import no.app.data.api.ApiEndpoints
import no.app.data.api.ApiService
import no.app.data.api.ApiServiceImpl
import no.app.data.api.RetrofitClientProvider

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideApiEndpoints(): ApiEndpoints = RetrofitClientProvider.client

    @Provides
    fun provideApiService(apiEndpoints: ApiEndpoints): ApiService = ApiServiceImpl(apiEndpoints)
}
