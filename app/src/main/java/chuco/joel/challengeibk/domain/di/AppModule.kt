package chuco.joel.challengeibk.domain.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import chuco.joel.challengeibk.data.network.ChallengeService
import chuco.joel.challengeibk.data.repository.ChallengeRepository
import chuco.joel.challengeibk.data.repository.ChallengeRepositoryImpl
import chuco.joel.challengeibk.domain.usecase.CuentasUseCase
import chuco.joel.challengeibk.domain.usecase.LoginUseCase
import chuco.joel.challengeibk.domain.usecase.MovimientosUseCase
import chuco.joel.challengeibk.presentation.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApiService(
        gsonConverterFactory: GsonConverterFactory,
    ): ChallengeService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ChallengeService.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
            .create(ChallengeService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ChallengeService): ChallengeRepository {
        return ChallengeRepositoryImpl(apiService)
    }

    @Provides
    fun provideLoginUseCase(repository: ChallengeRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideCuentasUseCase(repository: ChallengeRepository): CuentasUseCase {
        return CuentasUseCase(repository)
    }

    @Provides
    fun provideMovimientosUseCase(repository: ChallengeRepository): MovimientosUseCase {
        return MovimientosUseCase(repository)
    }

    @Provides
    fun provideLoginViewModelFactory(useCase: LoginUseCase): ViewModelProvider.Factory {
        return LoginViewModelFactory(useCase)
    }

    /*
              @Provides
              fun providePokemonDetailViewModelFactory(pokemonUseCase: PokemonUseCase, pokemonDetailUseCase: PokemonDetailUseCase): ViewModelProvider.Factory {
                  return PokemonDetailViewModelFactory(pokemonUseCase, pokemonDetailUseCase)
              }
          */
}