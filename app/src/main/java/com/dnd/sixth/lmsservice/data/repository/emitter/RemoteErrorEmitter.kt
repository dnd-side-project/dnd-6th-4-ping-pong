package com.dnd.sixth.lmsservice.data.repository.emitter

interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}