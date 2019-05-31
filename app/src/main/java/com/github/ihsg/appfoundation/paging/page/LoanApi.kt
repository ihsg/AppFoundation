package com.github.ihsg.appfoundation.paging.page

import com.github.ihsg.appfoundation.common.api.bean.PagedRspBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface LoanApi {
    @GET("loans")
    fun getLoanListApi(@QueryMap queryMap: Map<String, String>): Observable<PagedRspBean<LoanEntity>>
}