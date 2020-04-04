package com.example.mvp1.navigation

import com.example.mvp1.model.entity.GithubRepository
import com.example.mvp1.ui.fragments.RepositoriesFragment
import com.example.mvp1.ui.fragments.RepositoryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class RepoScreen : SupportAppScreen(){
        override fun getFragment() = RepositoriesFragment.newInstance()
    }

    class RepositoryScreen(val repository: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(repository)
    }

}