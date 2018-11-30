package com.nikitvad.oryanmat.trellowidget.data

import com.nikitvad.oryanmat.trellowidget.old.model.Board
import com.nikitvad.oryanmat.trellowidget.old.model.BoardList
import com.nikitvad.oryanmat.trellowidget.old.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    companion object {
        const val TOKEN_PREF_KEY = "com.oryanmat.trellowidget.usertoken"
        const val APP_KEY = "8e4cc8c7144a83e9b55b99cb52874b93"
        const val API_VERSION = "1/"
        const val KEY = "&key=$APP_KEY"
        const val AUTH_URL = "https://trello.com/1/authorize" +
                "?name=TrelloMobile" +
                KEY +
                "&expiration=never" +
                "&callback_method=fragment" +
                "&return_url=trello-widget://callback"
        const val BASE_URL = "https://api.trello.com/$API_VERSION/"
    }


    //    @Headers("Content-Type: application/json/")
    @GET("members/me?fields=fullName,username")
    fun getUser(): Observable<Response<User>>


    @GET("members/me/boards?filter=open&fields=id,name,url&lists=open")
    fun getBoards(): Observable<Response<List<Board>>>

    @GET("lists/%s?cards=open&card_fields=name,badges,labels,url")
    fun getCards(): Observable<Response<List<BoardList>>>


}