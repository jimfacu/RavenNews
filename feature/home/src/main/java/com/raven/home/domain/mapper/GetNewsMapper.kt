package com.raven.home.domain.mapper

import com.raven.home.data.Model.News
import com.raven.home.data.Model.NewsResponse
import javax.inject.Inject

class GetNewsMapper @Inject constructor() {

    fun transformDomainToUI(newsResponse: NewsResponse): List<News> {
        val newsList = mutableListOf<News>()
        newsResponse.results.forEach { result ->
            val news = News(
                title = result.title ?: "Titulo",

            )
            newsList.add(news)
        }
        return newsList
    }
}
