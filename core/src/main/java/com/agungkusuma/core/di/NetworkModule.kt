package com.agungkusuma.core.di

import android.content.Context
import com.agungkusuma.core.data.source.remote.network.ApiService
import com.agungkusuma.core.utils.Constants.ApiComponents.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val context: Context = get()
        val cacheSize = 10L * 1024 * 1024 // 10MB
        Cache(File(context.cacheDir, "http-cache"), cacheSize)
    }

    single<Gson> {
        GsonBuilder().setLenient().create()
    }

    single {
        val hostname = BASE_URL.toHttpUrl().host
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/bdrBhpj38ffhxpubzkINl0rG+UyossdhcBYj+Zx2fcc=")
            .build()
        OkHttpClient.Builder().cache(get()).addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(get()))
            .client(get()).build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}