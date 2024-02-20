package com.raven.home.domain.usecases

import android.util.Log
import com.raven.core.bases.BaseUseCase
import com.raven.home.Utils.Result
import com.raven.home.Utils.ResultStatus
import com.raven.home.data.Model.News
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeNewsUseCase @Inject constructor(
    private val dataSource: HomeDataSource,
    private val mapper: GetNewsMapper
) : BaseUseCase<String, Result<List<News>>>() {

    override fun execute(params: String): Flow<Result<List<News>>> = flow {
        dataSource.getNews(params).collect { result ->
            when (result.status) {
                ResultStatus.SUCCESS -> {
                    val newsResponse = result.data
                    if (newsResponse != null) {
                        val newsList = mapper.transformDomainToUI(newsResponse)
                        emit(Result(ResultStatus.SUCCESS, newsList))
                    }
                }
                ResultStatus.FAILURE -> {
                    val error = result.error
                    if(error != null){
                        emit(Result(ResultStatus.FAILURE,data = emptyList(),result.error))
                    }
                }
            }
        }
    }.catch { e ->
        emit(Result(ResultStatus.FAILURE, emptyList(),e))
    }.flowOn(Dispatchers.IO)

}

