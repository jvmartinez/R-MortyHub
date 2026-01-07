package com.jvmartinez.r_mortyhub.core.di

import com.jvmartinez.r_mortyhub.BuildConfig
import com.jvmartinez.r_mortyhub.core.repositories.CharacterService
import com.jvmartinez.r_mortyhub.core.repositories.IRepository
import com.jvmartinez.r_mortyhub.core.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Suppress("FunctionOnlyReturningConstant")
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun apiCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)


    @Provides
    @Singleton
    fun provideRepository(characterService: CharacterService): IRepository = Repository(characterService)

}