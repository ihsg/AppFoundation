package com.github.ihsg.appfoundation.common.exts

import com.github.ihsg.appfoundation.common.api.bean.PagedReqBean


fun PagedReqBean.toQueryMap(): Map<String, String> {
    return mapOf(
        "offset" to this.offset.toString(),
        "limit" to this.limit.toString(),
        "total" to this.total.toString()
    )
}