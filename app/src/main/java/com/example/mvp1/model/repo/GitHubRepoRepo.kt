package com.example.mvp1.model.repo

import com.example.mvp1.model.entity.GithubRepository

class GitHubRepoRepo {

    val repositories = listOf(
        GithubRepository("1","name1",10),
        GithubRepository("2","name2",20),
        GithubRepository("3","name3",30),
        GithubRepository("4","name4",40)
    )



    fun getRepo() = repositories

}