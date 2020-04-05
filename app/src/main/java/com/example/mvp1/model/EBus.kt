package com.example.mvp1.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class EBus() {

    private val bus :PublishSubject<Any> = PublishSubject.create()

    fun execBus(s: Any)= bus.onNext(s)

    fun toObserv():Observable<Any> = bus


}