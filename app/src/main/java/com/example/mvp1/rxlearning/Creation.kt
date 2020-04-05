package com.example.mvp1.rxlearning

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Creation {

    fun exec(){
    Consumer(Producer()).consume()

    }


    class Producer{

        fun randomResultOperation() : Boolean{
            Timber.d("SubscibeOn: ${Thread.currentThread().name}" )
            Thread.sleep(Random.nextLong(1000))
            return listOf(true,false,true)[Random.nextInt(2)]
        }

        fun just()= Observable.just("1","2","3")


        fun iterable()= Observable.fromIterable(listOf("1","2","3"))


        fun range()= Observable.range(0,3)

        fun interval() = Observable.interval(1,TimeUnit.SECONDS)

        fun fromCallable() = Observable.fromCallable{
            val result = randomResultOperation()
            return@fromCallable "Result of operation $result"
        }

        fun create() = Observable.create<String> {emitter ->
            for (i in 0..10){
                randomResultOperation().let {
                    if (it){
                        emitter.onNext("Success")
                    }
                    else{
                        emitter.onError(RuntimeException("Error"))
                        return@create
                    }
                }
            }
            emitter.onComplete()

        }

    }

    class Consumer(val produser:Producer){
            val stringObserver = object :Observer<String>{

                var disposable : Disposable? = null

                override fun onComplete() {
                    Timber.d("onComplete")
                }

                override fun onSubscribe(d: Disposable?) {
                    Timber.d("onSubscribe")
                    disposable = d
                }

                override fun onNext(t: String?) {
                    Timber.d("onNext: $t")
                }

                override fun onError(e: Throwable?) {
                    Timber.e(e)
                }

            }


        fun consume (){
            //execJust()
            //execIterable()
            //execLambdas()
            //execRange()
            //execInterval()
            //execHotInterval()
            //execFromCallable()
            //execCreate()
            execSubscribeOnObservOn()
        }

        fun execJust(){
            produser.just()
                .subscribe(stringObserver)
        }

        fun execIterable(){
            produser.iterable()
                .subscribe(stringObserver)

        }

        fun execLambdas(){
            produser.iterable()
                .subscribe({s->
                Timber.d("onNext $s")
                },{e->
                Timber.e(e)
                },{
                Timber.d("onComplete")
                })
        }

        fun execRange(){
            produser.range()
                .subscribe({s->
                    Timber.d("onNext $s")
                },{e->
                    Timber.e(e)
                },{
                    Timber.d("onComplete")
                })
        }

        fun execInterval(){
            var disposable : Disposable? = null
            val observer = object : Observer<Long>{
                override fun onComplete() {
                    Timber.d("onComplete")
                }
                override fun onSubscribe(d: Disposable?) {
                    disposable=d
                }
                override fun onNext(l: Long) {
                    Timber.d("onNext: $l")
                    if (l > 10){
                        disposable?.dispose()
                    }
                }
                override fun onError(e: Throwable?) {
                    Timber.e(e)
                }
            }
            produser.interval()
                .subscribe(observer)
        }

        fun execHotInterval(){
            var disposable : Disposable? = null
            val observer = object : Observer<Long>{
                override fun onComplete() {
                    Timber.d("onComplete")
                }
                override fun onSubscribe(d: Disposable?) {
                    disposable=d
                }
                override fun onNext(l: Long) {
                    Timber.d("onNext: $l")
                    if (l > 10){
                        disposable?.dispose()
                    }
                }
                override fun onError(e: Throwable?) {
                    Timber.e(e)
                }
            }
            val hotInterval = produser.interval().publish()
            hotInterval.connect()

            Thread.sleep(6000)

            hotInterval.subscribe(observer)
        }

        fun execFromCallable(){
            produser.fromCallable()
                .subscribe({s->
                    Timber.d("onNext $s")
                },{e->
                    Timber.e(e)
                },{
                    Timber.d("onComplete")
                })
        }

        fun execCreate(){
            produser.create()
                .subscribe({s->
                    Timber.d("onNext $s")
                },{e->
                    Timber.e(e)
                },{
                    Timber.d("onComplete")
                })
        }

        fun execSubscribeOnObservOn(){
            produser.fromCallable()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    Timber.d("ResultOn: ${Thread.currentThread().name}" )
                    Timber.d("onNext $it")
                }
        }

    }
}