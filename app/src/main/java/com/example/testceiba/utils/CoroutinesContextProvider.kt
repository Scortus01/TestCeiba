package com.example.testceiba.utils

import kotlin.coroutines.CoroutineContext

interface CoroutinesContextProvider {
    val io: CoroutineContext
    val main: CoroutineContext
}