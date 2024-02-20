package com.raven.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.Logger
import com.raven.home.LoggerImpl
import com.raven.home.Utils.ResultStatus
import com.raven.home.data.Model.News
import com.raven.home.domain.usecases.GeNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase:GeNewsUseCase,
) : ViewModel(){

   private val _uiStateHome = MutableStateFlow<List<News>>(emptyList())
   val uiStateHome: StateFlow<List<News>> = _uiStateHome


    private val logger : Logger = LoggerImpl

    /**
     * Obtiene noticias según la consulta especificada.
     *
     * @param query La consulta de búsqueda de dias de noticias.
     */
    fun getMoreProducts(query: String) {
        logger.debug("query: $query")

        viewModelScope.launch() {
            getNewsUseCase.execute(query)
                .onStart { showLoading(true) }
                .catch { e ->
                    showLoading(false)
                    showError(e.message.toString()) }
                .collect { response ->
                    showLoading(false)
                    when (response.status) {
                        ResultStatus.SUCCESS -> {
                            _uiStateHome.value = response.data!!
                        }
                        ResultStatus.FAILURE -> {
                            _uiStateHome.value = emptyList()
                        }
                }
                }
        }
    }
}

