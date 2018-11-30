package com.fikriadriansa.footballschedule

import com.fikriadriansa.footballschedule.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined
}