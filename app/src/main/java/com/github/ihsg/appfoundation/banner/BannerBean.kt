package com.github.ihsg.appfoundation.banner

import com.github.ihsg.appfoundation.common.api.CodeNameBean

data class BannerBean(
    val imageUrl: String?,
    val description: String?,
    var link: String?,
    var linkV2: String?,
    var linkV3: String?,
    var title: String?,
    var type: CodeNameBean?,
    var bannerId: String?,
    var isNeedLogin: String?
)