package com.example.mvp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvp1.model.CountersMod
import com.example.mvp1.presenter.MainPresenter
import com.example.mvp1.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter = MainPresenter(this, CountersMod())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_counter1.setOnClickListener{
            presenter.setClick1()
        }
        button_counter2.setOnClickListener{
            presenter.setClick2()
        }
        button_counter3.setOnClickListener{
            presenter.setClick3()
        }
    }

    override fun setButtonText(index: Int, text: String) {
        when(index){
            0 -> button_counter1.text = text
            1 -> button_counter2.text = text
            2 -> button_counter3.text = text
        }
    }
}
