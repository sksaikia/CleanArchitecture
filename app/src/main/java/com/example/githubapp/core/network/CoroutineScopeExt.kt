package com.example.githubapp.core.network

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//Testing
fun CoroutineScope.launchCatchError(context: CoroutineContext = coroutineContext,
                                    block: suspend CoroutineScope.() -> Unit,
                                    onError: suspend (Throwable) -> Unit) =
    launch (context) {
        try{
            block()
        } catch (t: Throwable){
            if (t is CancellationException) throw t
            else {
                try {
                    onError(t)
                } catch (e: Throwable){

                }
            }
        }
    }