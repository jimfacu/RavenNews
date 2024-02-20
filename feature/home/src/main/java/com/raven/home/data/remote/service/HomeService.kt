package com.raven.home.data.remote.service

import com.raven.home.data.Model.NewsResponse
import com.raven.home.di.HomeModule
import dagger.Component
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @Throws(Exception::class)
    @GET("svc/mostpopular/v2/viewed/{NUMBER_DAYS}.json?api-key=f4zL4tULmghgTFeT1A4HrZ8rzeKbzKjO")
    suspend fun getNews( @Path("NUMBER_DAYS") days: String): Response<NewsResponse>

}
