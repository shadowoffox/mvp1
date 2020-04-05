package com.example.mvp1.rxlearning

import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Sources {

    fun exec(){
    Consumer(Producer()).consume()

    }


    class Producer{

        fun randomResultOperation() : Boolean{
            Timber.d("SubscibeOn: ${Thread.currentThread().name}" )
            Thread.sleep(Random.nextLong(1000))
            return listOf(true,false,true)[Random.nextInt(2)]
        }

        fun single() = Single.fromCallable{
            return@fromCallable "AAAAAAAAAAA!!!!"
        }
        fun mb() =Maybe.create<String>{emitter ->
            try {
                randomResultOperation().let {
                    if (it)
                    {
                        emitter.onSuccess("Success operation")
                    }
                    else{
                        emitter.onComplete()
                    }
                }
            } catch (t:Throwable){
                emitter.onError(t)
            }
        }

        fun comleteble() = Completable.create { emitter ->
            randomResultOperation().let {
                if (it)
                {
                    emitter.onComplete()
                }
                else{
                    emitter.onError(RuntimeException("ERORRRRRRRRRR!"))
                }
            }
        }

        fun publishSubject() = PublishSubject.create<String>()
    }

    class Consumer(val produser:Producer){

        fun consume (){
            // execSingle()
            // execMb()
            // execComplete()
            execPublishSubject()
        }

        fun execSingle(){
            produser.single()
                .subscribe({
                    Timber.d("onNext $it")
                },{
                    Timber.e(it)
                })
        }

        fun execMb(){
        produser.mb()
            .subscribe({
                Timber.d("onNext $it")
            },{
                Timber.e(it)
            })
        }
        fun execComplete(){
            produser.comleteble()
                .subscribe({
                },{
                    Timber.e(it)
                })
        }

        fun execPublishSubject(){
            val subject = produser.publishSubject()

            subject.subscribe({
                Timber.d("onNext $it")
            },{
                Timber.e(it)
            },{
                Timber.d("onComplete")
            })

            subject.onNext("from exec")
        }

    }
}