package com.cvs.photos.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cvs.photos.WebServices.NetworkHelper
import com.cvs.photos.models.Flickr
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModel (context: Application) : AndroidViewModel(context) {

    private var photosItemsList= MutableLiveData<Flickr.ListOfItmes>()
   private val Disposable: CompositeDisposable? = CompositeDisposable()

    fun getData(limit:String) {

        var obs = NetworkHelper.createGitHubAPI().getPhotos(limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                Disposable!!.add(it)
            }
            .doOnError {
                var t = 0
            }
            .doOnNext { data ->

                        photosItemsList.value= data


            }
            .subscribe()
    }


     fun getPhotosAPIResponse(): MutableLiveData<Flickr.ListOfItmes> {
        return photosItemsList
    }


    override fun onCleared() {
        Disposable!!.clear()
    }

}