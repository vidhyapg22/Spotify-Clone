package com.plcoding.spotifycloneyt.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.exoplayer.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)         //This ApplicationComponent means that, all the dependencies inside this App module will live as long as the application does
object AppModule {

    @Singleton
    @Provides
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context
    ) =MusicServiceConnection(context)

    //Module 1
    @Singleton      //This specific instance "Glide instance has only a single instance throughout the app lifecycle
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    )= Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)       //placeholder madhe hi default vali image asel
            .error(R.drawable.ic_image)             //image load hou shakli nahi tar mag he default vali image disel
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}