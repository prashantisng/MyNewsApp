package com.outthinking.newsmvvmex.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outthinking.newsmvvmex.models.Article
import com.outthinking.newsmvvmex.models.NewsResponse
import com.outthinking.newsmvvmex.repository.NewsRepository
import com.outthinking.newsmvvmex.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class NewsViewModel (app: Application, val newsRepository: NewsRepository):AndroidViewModel(app) {

val breakingNews:MutableLiveData<Resource<NewsResponse>> =MutableLiveData()

    var breakingNewsPage=1
    init {

        getBreakingNews("us")
    }
    val searchNews:MutableLiveData<Resource<NewsResponse>> =MutableLiveData()
    var searchNewsPage=1


    fun getBreakingNews(countryCode:String) =viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response=newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }
    fun searchNews(searchQuery:String) =viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success( resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success( resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews()=newsRepository.getSavedNews()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.deleteArticle(article)

    }
}