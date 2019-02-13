package com.dynamo.task.ui

import com.dynamo.task.data.api.DataRepository
import com.dynamo.task.util.BLSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repo: DataRepository) {

    private lateinit var view: MainView

    private val subscriptions = CompositeDisposable()

    fun onCreate(view: MainView) {
        this.view = view
    }

    fun fetchCourses() {
        subscriptions.add(repo.getCourses()
            .subscribeOn(BLSchedulers.io())
            .observeOn(BLSchedulers.main())
            .subscribe({ it ->
                view.showCourses(it.courses)
            }, { throwable ->
                view.showErrorLoadingData()
                Timber.e(throwable, "while loading data")
            }))
    }

    fun onDestroy() {
        subscriptions.clear()
    }
}