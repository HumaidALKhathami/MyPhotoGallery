package com.example.myphotogallery.photogalleryfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myphotogallery.R
import com.example.myphotogallery.flickr.modules.GalleryItem
import com.example.myphotogallery.flickr.modules.PhotoResponse

private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment : Fragment() {


    private val viewModel by lazy { ViewModelProvider(this)[PhotoGalleryViewModel::class.java] }


    private lateinit var photoRv: RecyclerView
    private lateinit var progressBar: ProgressBar



    private val photoResponse = PhotoResponse()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.responseLiveData.observe(
            this, Observer { response ->

                updateUI(response)
                progressBar.visibility = View.GONE
                Log.d(TAG, "this is it $response")

            }
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_gallery_fragment, container, false)

        photoRv = view.findViewById(R.id.photo_rv)
        progressBar = view.findViewById(R.id.progressBar)

        photoRv.layoutManager = GridLayoutManager(context, 3)

        photoRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0){
                    photoResponse.page++

                }


                super.onScrolled(recyclerView, dx, dy)
            }
        })

        return view
    }

    private fun updateUI(photos: List<GalleryItem>) {
        photoRv.adapter = PhotoAdapter(photos)
    }

    private inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val photoIv: ImageView = view.findViewById(R.id.photo_item)


        fun bind(photo: GalleryItem) {

            photoIv.load(photo.url) {
                placeholder(R.drawable.ic_baseline_cloud_download_24)
                crossfade(300)
            }


        }

    }

    private inner class PhotoAdapter(val photos: List<GalleryItem>) :
        RecyclerView.Adapter<PhotoViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            val view = layoutInflater.inflate(R.layout.photo_gallery_item, parent, false)

            return PhotoViewHolder(view)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

            if (position == photos.size-1){
                photoResponse.page++

            }

            val photo = photos[position]

            holder.bind(photo)
        }

        override fun getItemCount(): Int = photos.size

    }



}