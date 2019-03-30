package com.github.ihsg.appfoundation.banner

import com.github.ihsg.appfoundation.common.api.ListBean
import io.reactivex.Observable
import retrofit2.http.GET

interface BannerApi {

    @GET("banners")
    fun getBannerList(): Observable<ListBean<BannerBean>>
}