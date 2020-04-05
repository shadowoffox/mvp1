package com.example.mvp1.presenter

import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.navigation.Screens
import com.example.mvp1.rxlearning.Creation
import com.example.mvp1.rxlearning.Operation
import com.example.mvp1.rxlearning.Sources
import com.example.mvp1.view.MainView
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(val router:Router) : MvpPresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.RepoScreen())

        //Creation().exec()
        //Operation().exec()
        Sources().exec()
        }
    fun backClicked() {
        router.exit()
    }

}