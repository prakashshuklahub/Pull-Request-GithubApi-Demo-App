package com.prakashshukla.githubsampleapp.model

import io.reactivex.Single
import javax.inject.Inject
import com.prakashshukla.githubsampleapp.di.DaggerApiComponent

//Service used for api call
class PullRequestService {

    @Inject
    lateinit var api:PullRequestApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPullRequest(): Single<List<PullRequest>> {
        return api.getPullRequest()
    }
}