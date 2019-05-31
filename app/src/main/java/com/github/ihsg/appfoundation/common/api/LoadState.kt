package com.github.ihsg.appfoundation.common.api

enum class Status {
    RUNNING,
    FAILED,
    SUCCESS
}

@Suppress("DataClassPrivateConstructor")
data class LoadState private constructor(val status: Status, val message: String? = null) {
    companion object {
        val LOADED = LoadState(Status.SUCCESS)
        val LOADING = LoadState(Status.RUNNING)
        fun error(msg: String?) = LoadState(Status.FAILED, msg)
    }
}