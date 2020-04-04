package com.example.mvp1.presenter.list

import com.example.mvp1.view.list.RepositoryItemView

interface IRepositoryListPresenter {

    var itemClickListener:((RepositoryItemView)->Unit)?
    fun getCount():Int
    fun bindView(view:RepositoryItemView)


}