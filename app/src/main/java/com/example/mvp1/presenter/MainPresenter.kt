package com.example.mvp1.presenter

import com.example.mvp1.model.CountersMod
import com.example.mvp1.view.MainView

class MainPresenter(val view:MainView, val model: CountersMod) {

    fun setClick1(){
        val nextValue=model.next(0)
        view.setButtonText(0,nextValue.toString())
    }
    fun setClick2(){
        val nextValue=model.next(1)
        view.setButtonText(1,nextValue.toString())
    }
    fun setClick3(){
        val nextValue=model.next(2)
        view.setButtonText(2,nextValue.toString())
    }

}