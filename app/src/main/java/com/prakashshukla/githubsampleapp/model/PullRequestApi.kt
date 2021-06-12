package com.prakashshukla.githubsampleapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface PullRequestApi {
    @GET("repos/facebook/react/pulls?state=closed")
    fun getPullRequest(): Single<List<PullRequest>>
}