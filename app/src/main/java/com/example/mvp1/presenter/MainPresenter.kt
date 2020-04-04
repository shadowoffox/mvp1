package com.example.mvp1.presenter

import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.navigation.Screens
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
        }
    fun backClicked() {
        router.exit()
    }

}