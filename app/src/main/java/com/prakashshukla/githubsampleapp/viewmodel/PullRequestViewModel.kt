package com.prakashshukla.githubsampleapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prakashshukla.githubsampleapp.di.DaggerApiComponent
import com.prakashshukla.githubsampleapp.model.PullRequest
import com.prakashshukla.githubsampleapp.model.PullRequestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//Viewmodel for our business logic
public class PullRequestViewModel :ViewModel() {

    @Inject
    lateinit var service:PullRequestService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val pullRequestList = MutableLiveData<List<PullRequest>?>()
    val  pullRequestLoadError = MutableLiveData<Boolean>()
    val loading  = MutableLiveData<Boolean>()

    fun refresh(){
        fetchPullRequest()
    }

    private fun fetchPullRequest(){
        loading.value = true
        disposable.add(
            service.getPullRequest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<PullRequest>>(){
                    override fun onSuccess(value: List<PullRequest>?) {
                        pullRequestList.value = value
                        pullRequestLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("ErrorData",e.toString())
                        pullRequestLoadError.value = true
                        loading.value = false
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

