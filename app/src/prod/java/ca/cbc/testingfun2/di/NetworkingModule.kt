package ca.cbc.testingfun2.di

import ca.cbc.testingfun2.data.GitHubJobsService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkingModule {

    @Provides
    @JvmStatic
    fun retrofitBuilder() = Retrofit.Builder()

    @Reusable
    @Provides
    @JvmStatic
    fun moshi(): Moshi = Moshi.Builder().build()

    @Reusable
    @Provides
    @JvmStatic
    fun moshiConverterFactory(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Reusable
    @Provides
    @JvmStatic
    fun gitHubJobsService(
        builder: Retrofit.Builder,
        moshiConverterFactory: MoshiConverterFactory
    ): GitHubJobsService {
        return createService(
            builder,
            moshiConverterFactory,
            GitHubJobsService.baseUrl,
            GitHubJobsService::class.java
        )
    }

    private fun <T> createService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        baseUrl: String,
        clazz: Class<T>
    ): T {
        return builder
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .build()
            .create(clazz)
    }
}