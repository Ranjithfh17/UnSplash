package com.fh.unsplash.depinjection

import android.content.Context
import com.fh.unsplash.api.UnSplashApi
import com.fh.unsplash.preferences.MainPreferences
import com.fh.unsplash.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance():Retrofit=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideUnSplashApi(retrofit: Retrofit):UnSplashApi=
        retrofit.create(UnSplashApi::class.java)



    @Singleton
    @Provides
    fun provideMainPreferences(@ApplicationContext context: Context)=MainPreferences(context)



}