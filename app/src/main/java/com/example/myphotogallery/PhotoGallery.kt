package com.example.myphotogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myphotogallery.photogalleryfragment.PhotoGalleryFragment

class PhotoGallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if (currentFragment == null){
            val fragment = PhotoGalleryFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView,fragment)
                .commit()
        }


    }
}