package com.cvs.photos.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cvs.photos.R
import com.cvs.photos.models.Flickr
import kotlinx.android.synthetic.main.item_view.view.*


class PhottoslistAdapter(
    val context: Context,
    val photosItemsList: Flickr.ListOfItmes
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Flickr.Item?) -> Unit)? = null

    override fun getItemCount(): Int {

        return if  (photosItemsList.items== null) 0
        else photosItemsList.items!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        )

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        photosItemsList.items?.let {
            (holder as ViewHolder).bind(photosItemsList.items?.get(position), context)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stores: Flickr.Item?, context: Context) {
            itemView.title.text = stores!!.title!!
            stores.media.let {
                Glide.with(context).load(stores.media?.m).placeholder(R.drawable.ic_launcher_background).into(itemView.Image)
            }


        }

        init {
            itemView.setOnClickListener {
                  onItemClick?.invoke(photosItemsList.items?.get(position))
            }
        }
    }

}

