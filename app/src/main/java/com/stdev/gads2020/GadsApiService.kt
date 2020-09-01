package com.stdev.gads2020

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://gadsapi.herokuapp.com/"
private const val SUBMIT_BASE_URL = "https://docs.google.com/forms/d/e/"

//moshi retrofit support
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//to build the main retrofit gann
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

private val submitRetrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(SUBMIT_BASE_URL)
    .build()

interface GadsApiService{
    @GET("api/hours")
    fun getLearningHoursLeadersAsync(): Deferred<List<LearningLeaders>>

    @GET("api/skilliq")
    fun getSkillsIqLeadersAsync() : Deferred<List<SkillIQ>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitProject(
        @Field("entry.1824927963") EmailAddress:String?,
        @Field("entry.1877115667") Name:String?,
        @Field("entry.2006916086") LastName:String?,
        @Field("entry.284483984")  LinktoProject:String?
    ): Call<Void>?


}

object GadsApi{
    val retrofitService : GadsApiService by lazy { retrofit.create(GadsApiService::class.java) }
    val retrofitSubmitService : GadsApiService by lazy { submitRetrofit.create(GadsApiService::class.java) }
}