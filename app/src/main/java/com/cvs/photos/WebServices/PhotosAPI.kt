package com.cvs.photos.WebServices

import com.cvs.photos.models.Flickr
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotosAPI {


    @GET("services/feeds/photos_public.gne?nojsoncallback=1&tagmode=any&format=json")
    fun getPhotos(
        @Query("tags") lat: String? = "dog"
    ): Observable<Flickr.ListOfItmes>
}



