package com.rivaldofez.moviers.data.source.remote

import com.rivaldofez.moviers.data.source.remote.StatusResponse.*

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}