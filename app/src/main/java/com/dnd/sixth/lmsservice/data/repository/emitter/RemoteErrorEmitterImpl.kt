package com.dnd.sixth.lmsservice.data.repository.emitter

import timber.log.Timber

class RemoteErrorEmitterImpl: RemoteErrorEmitter {
    override fun onError(msg: String) {
        Timber.d(msg)
    }

    override fun onError(errorType: ErrorType) {
        Timber.d(errorType.name)
    }
}