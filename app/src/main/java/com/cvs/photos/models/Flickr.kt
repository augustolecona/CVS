package com.cvs.photos.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Flickr {

   data class ListOfItmes (
        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("link")
        @Expose
        var link: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("modified")
        @Expose
        var modified: String? = null,

        @SerializedName("generator")
        @Expose
        var generator: String? = null,

        @SerializedName("items")
        @Expose
        var items: List<Item>? = null


   ){
       fun copyfrom(proyecta: ListOfItmes) {
           title=proyecta.title
           link= proyecta.link
           description = proyecta.description
           modified = proyecta.modified
           generator = proyecta.generator
           items = proyecta.items
       }
   }

    data class Item(
        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("link")
        @Expose
        var link: String? = null,

        @SerializedName("media")
        @Expose
        var media: Media? = null,

        @SerializedName("date_taken")
        @Expose
        var dateTaken: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("published")
        @Expose
        var published: String? = null,

        @SerializedName("author")
        @Expose
        var author: String? = null,

        @SerializedName("author_id")
        @Expose
        var authorId: String? = null,

        @SerializedName("tags")
        @Expose
        var tags: String? = null
    )

    class Media {
        @SerializedName("m")
        @Expose
        var m: String? = null


    }
}