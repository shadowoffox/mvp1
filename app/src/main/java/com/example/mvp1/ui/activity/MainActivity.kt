package com.example.mvp1.ui.activity


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp1.R

import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.presenter.MainPresenter
import com.example.mvp1.ui.App
import com.example.mvp1.ui.BackButtonListener
import com.example.mvp1.ui.adapter.RepoRVAdapter
import com.example.mvp1.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = SupportAppNavigator(this,R.id.container)


    @InjectPresenter
    lateinit var presenter: MainPresenter

    var adapter : RepoRVAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    @ProvidePresenter
    fun providePresenter() = MainPresenter(App.instance.router)

    override fun init() {

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backClicked()){
                return
            }
        }
        presenter.backClicked()
    }

}
