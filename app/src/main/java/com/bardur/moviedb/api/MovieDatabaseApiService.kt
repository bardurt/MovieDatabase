package com.bardur.moviedb.api


import com.bardur.moviedb.data.Movie
import com.bardur.moviedb.data.MovieDatabaseResponseList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

// get your API Key from https://www.themoviedb.org/ and replace this
private const val API_KEY = "43ad9df8f9e03181f4ca41b5e00c8222"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieDatabaseApiService {

    @GET("discover/movie?api_key=$API_KEY&sort_by=popularity.desc")
    suspend fun mostPopular(@Query("page") page : Int): MovieDatabaseResponseList

    @GET("movie/latest?api_key=$API_KEY&language=en-US")
    suspend fun latest(): Movie

    @GET("movie/top_rated?api_key=$API_KEY&language=en-US")
    suspend fun topRated(@Query("page") page : Int): MovieDatabaseResponseList

    @GET("search/movie?api_key=$API_KEY")
    suspend fun search(@Query("query") query: String): MovieDatabaseResponseList

}

object MovieDatabaseApi {
    val retrofitService: MovieDatabaseApiService by lazy { retrofit.create(MovieDatabaseApiService::class.java) }
}
