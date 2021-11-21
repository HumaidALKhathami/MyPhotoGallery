package com.example.myphotogallery.flickr.api

import com.example.myphotogallery.flickr.modules.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET( "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=f0e2ac62a70294c37fa808f0f9a743c9" +
            "&extras=url_s" +
            "&per_page=20" +
            "&page=100" +
            "&format=json" +
            "&nojsoncallback=1")
    fun fetchPhotos(): Call<FlickrResponse>
}



//"services/rest/?method=flickr.interestingness.getList" +
//"&api_key=f0e2ac62a70294c37fa808f0f9a743c9" +
//"&extras=url_s" +
//"&format=json" +
//"&nojsoncallback=1"