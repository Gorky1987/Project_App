package de.syntaxinstitut.project_app.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.project_app.BuildConfig
import de.syntaxinstitut.project_app.data.datamodels.GymSearchList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://serpapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("search.json?engine=google_maps&google_domain=google.com&hl=de&type=search")
    suspend fun getGymSearch(
        @Query("q") plzText: String,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): GymSearchList


}

object GymSearchApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}