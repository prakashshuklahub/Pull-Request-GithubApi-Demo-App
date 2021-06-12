package com.prakashshukla.githubsampleapp.di

import com.prakashshukla.githubsampleapp.model.PullRequestService
import com.prakashshukla.githubsampleapp.viewmodel.PullRequestViewModel
import dagger.Component



@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: PullRequestService)

    fun inject(viewModel: PullRequestViewModel)
}