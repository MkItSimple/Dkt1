package com.example.dkt1.di.module

import com.example.dkt1.BASE_URL
import com.example.dkt1.TIMEOUT_REQUEST
import com.example.dkt1.di.scope.AppScope
import com.example.dkt1.domain.PokemonUsecase
import com.example.dkt1.network.PokemonApi
import com.example.dkt1.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class AppModule {
    @AppScope
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @AppScope
    @Provides
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .build()

    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @AppScope
    @Provides
    fun provideFeedService(builder: Retrofit.Builder) =
        builder.baseUrl(BASE_URL).build().create(PokemonApi::class.java)

    // PokemonUsecaseModule  Replacement
    @AppScope
    @Provides
    fun providePokemonUsecase(repository :PokemonRepository) = PokemonUsecase(repository)

    @AppScope
    @Provides
    fun provideApi() = PokemonApi::class.java


    // RepositoryModule Replacement
    @AppScope
    @Provides
    fun provideFeedRepository(api: PokemonApi) = PokemonRepository(api)
}