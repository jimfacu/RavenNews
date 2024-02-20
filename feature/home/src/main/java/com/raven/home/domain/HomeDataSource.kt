package com.raven.home.domain

import com.raven.home.Utils.Result
import com.raven.home.data.Model.NewsResponse
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface HomeDataSource  {

    suspend fun getNews(params: String): Flow<Result<NewsResponse>>
}
