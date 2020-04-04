package com.example.mvp1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp1.R
import com.example.mvp1.presenter.list.IRepositoryListPresenter
import com.example.mvp1.view.list.RepositoryItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoRVAdapter(val presenter: IRepositoryListPresenter):RecyclerView.Adapter<RepoRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo,parent,false ))

    override fun getItemCount()= presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos=position
        holder.containerView.setOnClickListener{presenter.itemClickListener?.invoke(holder)}
       presenter.bindView(holder)
    }



class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),LayoutContainer,RepositoryItemView {
    override var pos = -1

    override fun setTitle(text: String) = with(containerView) {
        tv_title.text = text
    }
}



}