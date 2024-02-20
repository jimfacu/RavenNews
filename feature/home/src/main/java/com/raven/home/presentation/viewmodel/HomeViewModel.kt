package com.raven.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.Logger
import com.raven.home.data.Model.News
import com.raven.home.domain.usecases.GeNewsUseCase
import com.raven.home.LoggerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase:GeNewsUseCase,
) : ViewModel(){

   // private val _uiStateHome = MutableStateFlow<HomeState>(HomeState.Loading(false))
    //val uiStateHome: StateFlow<HomeState> = _uiStateHome


    private val logger : Logger = LoggerImpl

    /**
     * Obtiene más productos según la consulta especificada.
     *
     * @param query La consulta de búsqueda de productos.
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
                    when (response) {

                }
                }
        }
    }

    private fun showLoading(loading: Boolean) {
        //_uiStateHome.value = HomeState.Loading(loading)
    }

    private fun showError(message: String) {
        //_uiStateHome.value = HomeState.Error(message)
    }

    /*

    private fun validateQuery(query: String) {
        if (query.isNotEmpty() && lastQuery.lowercase() != query.lowercase()) {
            reset(query)
        } else {
            page += LIMIT_PAGE
        }
    }

    private fun reset(query: String) {
        page = 0
        lastQuery = query
    }

    private fun showError(message: String) {
        _uiStateHome.value = HomeState.Error(message)
    }

    private fun showLoading(loading: Boolean) {
        _uiStateHome.value = HomeState.Loading(loading)
    }

    private fun moreProducts(products: List<ProductModel>) {
        _uiStateHome.value = HomeState.MoreProducts(products)
    }

    private fun newProducts(products: List<ProductModel>) {
        _uiStateHome.value = HomeState.NewProducts(products)
    }

*/




}

