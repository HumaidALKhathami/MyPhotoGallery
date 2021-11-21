package com.example.myphotogallery.flickr.modules

import com.google.gson.annotations.SerializedName

class PhotoResponse {

    @SerializedName("photo")
    lateinit var galleryItem: List<GalleryItem>

    var page: Int = 1
}