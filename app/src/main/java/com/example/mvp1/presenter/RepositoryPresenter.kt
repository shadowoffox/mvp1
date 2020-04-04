package com.example.mvp1.presenter

import com.example.mvp1.model.entity.GithubRepository
import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.presenter.list.IRepositoryListPresenter
import com.example.mvp1.view.MainView
import com.example.mvp1.view.RepoView
import com.example.mvp1.view.RepositoryView
import com.example.mvp1.view.list.RepositoryItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoryPresenter(val repositoriesRepo: GithubRepository, val router:Router) : MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(repositoriesRepo.id)
        viewState.setTitle(repositoriesRepo.name)
        viewState.setForksCount(repositoriesRepo.forksCount.toString())

    }

    fun backClicked() : Boolean{
        router.exit()
        return true
    }
}