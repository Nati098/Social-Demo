package ru.social.demo.services.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import ru.social.demo.data.model.rpg.ResultsModel
import ru.social.demo.data.model.rpg.RpgClass
import ru.social.demo.data.model.rpg.RpgMonster
import ru.social.demo.data.model.rpg.RpgRace

interface RpgApiService {

    @GET("classes/{id}")
    suspend fun getClassById(@Path("id") id: String): RpgClass

    @GET("races/{id}")
    suspend fun getRaceById(@Path("id") id: String): RpgRace

    @GET("monsters")
    suspend fun getMonsters(): ResultsModel

    @GET("monsters/{id}")
    suspend fun getMonsterById(@Path("id") id: String): RpgMonster

}