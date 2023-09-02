package no.app.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import no.app.data.api.ApiEndpoints
import no.app.data.api.ApiService
import no.app.data.api.ApiServiceImpl
import no.app.data.api.RetrofitClientProvider
import no.app.data.db.AppDatabase
import no.app.data.repository.UserRepository
import no.app.data.repository.UserRepositoryImpl
import no.app.data.store.KeyStore

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideApiEndpoints(): ApiEndpoints = RetrofitClientProvider.client

    @Provides
    fun provideApiService(apiEndpoints: ApiEndpoints): ApiService = ApiServiceImpl(apiEndpoints)

    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository =
        UserRepositoryImpl(apiService)

    @Provides
    fun provideKeyStore(@ApplicationContext context: Context): KeyStore = KeyStore(context)

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
}
