package com.hassan.softxperttask.presentation.di

import android.app.Application
import com.hassan.softxperttask.BuildConfig
import com.hassan.softxperttask.data.datasource.CarsAPI
import com.hassan.softxperttask.data.repository.CarsRepositoryImpl
import com.hassan.softxperttask.presentation.repository.CarsRepository
import com.hassan.softxperttask.presentation.viewmodel.CarsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { createCarsService(get()) }
}

val repoModule = module {
    single<CarsRepository> { CarsRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { CarsViewModel(get()) }
}

// Start
fun start(myApplication: Application) {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(myApplication)
        modules(
                listOf(
                        networkModule,
                        repoModule,
                        viewModelModule
                )
        )

    }
}

private fun createCarsService(retrofit: Retrofit): CarsAPI{
    return retrofit.create(CarsAPI::class.java)
}
private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASED_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun getOkHttp(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                        HttpLoggingInterceptor.Level.BODY
            }).build()
}