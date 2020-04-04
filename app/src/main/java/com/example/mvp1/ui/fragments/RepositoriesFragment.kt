package com.example.mvp1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp1.R
import com.example.mvp1.model.repo.GitHubRepoRepo
import com.example.mvp1.presenter.RepoPresenter
import com.example.mvp1.ui.App
import com.example.mvp1.ui.adapter.RepoRVAdapter
import com.example.mvp1.view.RepoView
import kotlinx.android.synthetic.main.fargment_repo.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class RepositoriesFragment: MvpAppCompatFragment(),RepoView {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    @InjectPresenter
    lateinit var presenter: RepoPresenter

    var adapter : RepoRVAdapter? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        View.inflate(context, R.layout.fargment_repo,null)

    @ProvidePresenter
    fun providePresenter() = RepoPresenter(GitHubRepoRepo(),App.instance.router)

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = RepoRVAdapter(presenter.repositoryListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}