package com.cvs.photos.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cvs.photos.R
import com.cvs.photos.models.Flickr
import com.google.gson.Gson
import kotlinx.android.synthetic.main.photodetail_view.*


class PhotoDetail : AppCompatActivity() {




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photodetail_view)
        var photoitem: Flickr.Item? = null
        try{
            val gson = Gson()
             photoitem = gson.fromJson<Flickr.Item>(
                intent.getStringExtra("photojson"), Flickr.Item::class.java
            )
        }
        catch (e:Exception){
            Log.e("#ERROR", e.message!!)
        }


            Glide.with(this).load(photoitem?.media?.m).placeholder(R.drawable.info).into(Image)
            photoitem?.title?.let      {     findViewById<TextView>(R.id.title).text = "${photoitem.title}"}
            photoitem?.description?.let{     description.text = "About: ${photoitem.description}"}
            photoitem?.author?.let{    authortv.text = photoitem.author}
            photoitem?.media?.m?.let{    widthtv.text = "240"}
            photoitem?.media?.m?.let{    lengthtv.text = "720"}



    }
}