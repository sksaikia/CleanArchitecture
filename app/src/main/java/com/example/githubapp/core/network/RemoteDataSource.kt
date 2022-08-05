package com.example.githubapp.core.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteDataSource() {

    open suspend fun <T> safeApiCall(apiCall : suspend () -> T, error : suspend (String) -> Unit) {
            try {
                 apiCall.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
                when(e) {
                    is HttpException -> error("Http Exception with code : ${e.code()}")
                    is SocketTimeoutException -> error("Socket Exception with code ${e.message}")
                    is IOException -> error("Io Exception with code ${e.message}")
                    is UnknownHostException -> error("Unknown Exception with code }")
                    else -> error("Http Exception with code }")
                }

            }
        }
    }
//    open suspend fun <T> safeApiCall(apiCall : suspend () -> T) : Result<T> {
//        return withContext(Dispatchers.IO){
//            try {
//                Result.Success(apiCall.invoke())
//            } catch (e : Exception) {
//                when(e) {
//                    is HttpException -> {
//                        val result = when(e.code()) {
//                            in 400..451 -> error(e.message())
//                            in 500..509 -> error(e.message())
//                            else -> error(e.message())
//                        }
//                        result
//                    }
//                    is UnknownHostException -> error( "No internet connection")
//                    is SocketTimeoutException -> error("Slow Internet")
//                    is IOException -> error( e.message)
//                    else -> error("Unknown Error")
//                }
//            }
//        }
//    open suspend fun <T> safeApiCall(apiCall : suspend () -> T) : Result<out T> {
//        return withContext(Dispatchers.IO){
//            try {
//                Result.Success(apiCall.invoke())
//            } catch (throwable : Throwable) {
//                when (throwable) {
//                    is HttpException -> {
//                        val result = when(throwable.code()){
//                            in 400..451 -> parseHttpError(throwable)
//                            in 500..599 -> error(HttpResult.SERVER_ERROR, throwable.code(), "Server error")
//                            else -> error(HttpResult.NOT_DEFINED, throwable.code(), "Undefined error")
//                        }
//                        result
//                    }
//                    is UnknownHostException -> error(HttpResult.NO_CONNECTION,null, "No internet connection")
//                    is SocketTimeoutException -> error(HttpResult.TIMEOUT,null, "Slow Internet")
//                    is IOException -> error(HttpResult.BAD_RESPONSE, null, throwable.message)
//                    else -> error(HttpResult.NOT_DEFINED,null, throwable.message)
//                }
//            }
//        }
//    }
//
//    private fun parseHttpError(throwable: HttpException) : Result<Nothing> {
//        return try {
//            val errorBody = throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
//            val gson = Gson()
//            val adapter = gson.getAdapter(Object::class.java)
//            val errorMessage = adapter.fromJson(errorBody)
//            error(HttpResult.CLIENT_ERROR, throwable.code(),errorMessage.toString())
//        } catch (exception : Exception){
//            error(HttpResult.CLIENT_ERROR, throwable.code(), exception.localizedMessage)
//        }
////    }
