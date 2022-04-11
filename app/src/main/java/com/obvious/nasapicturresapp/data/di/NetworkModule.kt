package com.obvious.nasapicturresapp.data.di

import com.obvious.nasapicturresapp.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import com.obvious.nasapicturresapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Developed by Kajal Kukdeja on 10,April,2022
 * SingletonComponents for network calls
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Singleton
	@Provides
	fun provideHttpClient(): OkHttpClient {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		return OkHttpClient
			.Builder()
			.addInterceptor(interceptor)
			.readTimeout(5, TimeUnit.MINUTES)
			.connectTimeout(5, TimeUnit.MINUTES)
			.writeTimeout(5, TimeUnit.MINUTES)
			.build()
	}

	@Singleton
	@Provides
	fun provideConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create(GsonBuilder().setLenient().create())

	@Singleton
	@Provides
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(gsonConverterFactory)
			.build()
	}

	@Singleton
	@Provides
	fun provideService(retrofit: Retrofit): ApiService =
		retrofit.create(ApiService::class.java)
}
