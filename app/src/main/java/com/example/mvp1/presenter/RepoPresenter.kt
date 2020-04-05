package com.example.mvp1.presenter

import com.example.mvp1.model.entity.GithubRepository
import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.navigation.Screens
import com.example.mvp1.presenter.list.IRepositoryListPresenter
import com.example.mvp1.view.RepoView
import com.example.mvp1.view.list.RepositoryItemView
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class RepoPresenter(val repositoriesRepo: GitHubRepoRepo, val router:Router) : MvpPresenter<RepoView>() {

    class RepositoryListPresenter : IRepositoryListPresenter{

        val repositoies = mutableListOf<GithubRepository>()


        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositoies.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositoies[view.pos]

            view.setTitle(repository.name)
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepo()
        repositoryListPresenter.itemClickListener = {itemView ->
            val repository = repositoryListPresenter.repositoies[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }


    fun loadRepo(){
        repositoryListPresenter.repositoies.clear()
        repositoriesRepo.getRepo()
            .subscribe({
                timber.log.Timber.d("do it $it")
                repositoryListPresenter.repositoies.add(it)
            },{e ->
                timber.log.Timber.e(e)
            })

    }
    fun backClicked() : Boolean {
        router.exit()
        return true
    }
}