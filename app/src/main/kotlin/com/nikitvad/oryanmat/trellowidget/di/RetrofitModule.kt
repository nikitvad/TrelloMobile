package com.nikitvad.oryanmat.trellowidget.di

import com.nikitvad.oryanmat.trellowidget.data.Api
import com.nikitvad.oryanmat.trellowidget.util.PreferencesUtil
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetrofitModule() {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        callAdapterFactory: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, preferencesUtil: PreferencesUtil): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(RequestInterceptor(preferencesUtil))
                .cache(null)
                .build()
    }

    @Provides
    @Singleton
    fun provideLoginingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    inner class RequestInterceptor(private val preferencesUtil: PreferencesUtil) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val httpUrl = request.url()

            var newHttp = httpUrl.newBuilder()
                    .addQueryParameter("key", Api.APP_KEY)
                    .addQueryParameter("token", preferencesUtil.getToken())
                    .build()

            val requestBuilder = request.newBuilder().url(newHttp)
            val newRequest = requestBuilder.build()
            return chain.proceed(newRequest)

        }

    }


}