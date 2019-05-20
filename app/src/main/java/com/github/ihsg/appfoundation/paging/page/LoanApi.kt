package com.github.ihsg.appfoundation.paging.page

import com.github.ihsg.appfoundation.common.api.ListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LoanApi {
    @GET("loans")
    fun getLoanListApi(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<ListBean<LoanEntity>>
}