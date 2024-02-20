package com.raven.home.data

import com.google.gson.Gson
import com.raven.home.Utils.ResultStatus
import com.raven.home.data.Model.NewsResponse
import com.raven.home.data.remote.service.HomeService
import com.raven.home.domain.HomeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.raven.home.Utils.Result
import kotlinx.coroutines.flow.catch

import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    val api: HomeService,
    val gson: Gson
) : HomeDataSource {

    override suspend fun getNews(params: String): Flow<Result<NewsResponse>> = flow {
        try {
            val response = api.getNews(params)
            if (response.isSuccessful) {
                val newsResponse = response.body()
                if (newsResponse != null) {
                    //En el caso exitoso , devolvemos el body
                    emit(Result(ResultStatus.SUCCESS, newsResponse))
                } else {
                    //Manejamos el caso si el body es null
                    emit(Result(ResultStatus.FAILURE, error = Exception("Response body is null")))
                }
            } else {
                // Si la respuesta no es exitosa , devolvemos un string con el codigo de error.
                emit(
                    Result(
                        ResultStatus.FAILURE,
                        error = Exception("Unsuccessful response: ${response.code()}")
                    )
                )
            }
        } catch (e: Exception) {
            // Si hay un error en la red u otro tipo de error , manejamos Manejar el caso de error de red u otro tipo de error
            emit(Result(ResultStatus.FAILURE, error = e))
        }
    }.catch { e ->
        emit(Result(ResultStatus.FAILURE, error = e))
    }
}
