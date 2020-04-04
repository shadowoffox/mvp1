package com.example.mvp1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp1.R
import com.example.mvp1.model.entity.GithubRepository
import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.presenter.RepoPresenter
import com.example.mvp1.presenter.RepositoryPresenter
import com.example.mvp1.ui.App
import com.example.mvp1.ui.BackButtonListener
import com.example.mvp1.ui.adapter.RepoRVAdapter
import com.example.mvp1.view.RepoView
import com.example.mvp1.view.RepositoryView
import kotlinx.android.synthetic.main.fargment_repo.*
import kotlinx.android.synthetic.main.fargment_repository.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoryFragment: MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        const val REPOSITORY_KEY="repo"
        fun newInstance(repository:GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_KEY,repository)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: RepositoryPresenter

    var adapter : RepoRVAdapter? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        View.inflate(context, R.layout.fargment_repository,null)

    @ProvidePresenter
    fun providePresenter() = RepositoryPresenter(arguments!![REPOSITORY_KEY] as GithubRepository,App.instance.router)

    override fun init() {

    }

    override fun setId(text: String) {
     tv_id.text=text
    }

    override fun setTitle(text: String) {
       tv_name.text=text
    }

    override fun setForksCount(text: String) {
        tv_forksCount.text=text
    }

    override fun backClicked()= presenter.backClicked()

}