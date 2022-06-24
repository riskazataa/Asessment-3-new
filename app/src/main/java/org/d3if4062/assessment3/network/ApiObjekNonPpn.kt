package org.d3if4062.assessment3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4062.assessment3.model.ObjekNonPpn
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/riskazataa/Asessment3-new/main/"
private const val BASE_IMG_URL = "https://raw.githubusercontent.com/riskazataa/Asessment3-new/main/img/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface ApiObjekNonPpn {
    @GET("objek-pajak.json")
    suspend fun getObjekNonPpn(): List<ObjekNonPpn>
}
object ObjekNonPpnApi {
    val service: ApiObjekNonPpn by lazy {
        retrofit.create(ApiObjekNonPpn::class.java)
    }
    fun getObjekNonPpnUrl(gambar: String): String {
        return "$BASE_IMG_URL$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }