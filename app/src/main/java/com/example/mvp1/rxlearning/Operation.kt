package com.example.mvp1.rxlearning

import com.github.ajalt.timberkt.i
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import timber.log.Timber
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operation {

    fun exec(){
    Consumer(Producer()).consume()

    }


    class Producer{

        fun randomResultOperation() : Boolean{
            Timber.d("SubscibeOn: ${Thread.currentThread().name}" )
            Thread.sleep(Random.nextLong(1000))
            return listOf(true,false,true)[Random.nextInt(2)]
        }

        fun just()= Observable.just("1","2","3","1")


        fun iterable()= Observable.fromIterable(listOf("1","2","3"))


        fun range()= Observable.range(0,3)

        fun interval() = Observable.interval(1,TimeUnit.SECONDS)

        fun fromCallable() = Observable.fromCallable{
            val result = randomResultOperation()
            return@fromCallable "Result of operation $result"
        }

        fun create() = Observable.create<String> {emitter ->
            for (i in 4..8){
                Thread.sleep(Random.nextInt(500).toLong())
                        emitter.onNext(i.toString()) }


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
           // execMap()
            //execFilter()
            //execDistinct()
            //execTake()
           // execSkip()
            //execMerge()
            execFlatMap()
            execSwitchMap()
        }

        fun execMap(){
            produser.just()
                .map {it + "x"}
                .subscribe(stringObserver)
        }

        fun execFilter(){
            produser.just()
                .filter {it.toInt()>2}
                .subscribe(stringObserver)
        }

        fun execDistinct(){
            produser.just()
                .distinct()
                .subscribe(stringObserver)
        }

        fun execTake(){
            produser.just()
                .take(2)
                .subscribe(stringObserver)
        }

        fun execSkip(){
            produser.just()
                .skip(2)
                .subscribe(stringObserver)
        }

        fun execMerge(){
            produser.create().subscribeOn(Schedulers.newThread())
                .mergeWith(produser.create())
                .subscribe(stringObserver)
        }

        fun execFlatMap(){
           val testScheduler = TestScheduler()
            produser.just()
                .flatMap {
                    val  delay = Random.nextInt(10).toLong()
                    return@flatMap Observable.just(it + " x").delay (delay,TimeUnit.SECONDS,testScheduler)
                }
                .toList()
                .subscribe({
                    Timber.d("onNext $it")
                },
                    {
                        Timber.e(it)

                    })
            testScheduler.advanceTimeBy(1,TimeUnit.MINUTES)
        }
        fun execSwitchMap(){
           val testScheduler = TestScheduler()
            produser.just()
                .switchMap {
                    val  delay = Random.nextInt(10).toLong()
                    return@switchMap Observable.just(it + " x").delay (delay,TimeUnit.SECONDS,testScheduler)
                }
                .toList()
                .subscribe({
                    Timber.d("onNext $it")
                },
                    {
                        Timber.e(it)

                    })
            testScheduler.advanceTimeBy(1,TimeUnit.MINUTES)
        }


    }
}