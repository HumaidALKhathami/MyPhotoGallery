package com.example.myphotogallery.photogalleryfragment

import androidx.lifecycle.ViewModel
import com.example.myphotogallery.flickr.repo.FlickrFetcherRepo

class PhotoGalleryViewModel : ViewModel() {

    private val flickerFetcherRepo = FlickrFetcherRepo()

    val responseLiveData = flickerFetcherRepo.fetchPhoto()
}