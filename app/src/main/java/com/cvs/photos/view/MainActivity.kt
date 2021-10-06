package com.cvs.photos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvs.photos.R
import com.cvs.photos.Util
import com.cvs.photos.models.Flickr
import com.cvs.photos.view.adapters.PhottoslistAdapter
import com.google.gson.Gson
import io.reactivex.plugins.RxJavaPlugins


class MainActivity : AppCompatActivity() {

    lateinit var recycleView: RecyclerView
    var photos = Flickr.ListOfItmes()
    lateinit var adapter: PhottoslistAdapter
    lateinit var textView: TextView
    lateinit var progressbarlayout: View
    lateinit var searchView: SearchView
    lateinit var modelView: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        castViews()
        modelView = ViewModelProviders.of(this).get(ViewModel::class.java)

        val nameObserver = Observer<Flickr.ListOfItmes> { results ->
            progressbarlayout.visibility = View.INVISIBLE
            if (results.items.isNullOrEmpty()) {
                Toast.makeText(this, "No resuts f0und", Toast.LENGTH_LONG).show()
            } else {
                photos.copyfrom(results)
                adapter.notifyDataSetChanged()
            }
        }

        modelView.getPhotosAPIResponse().observe(this, nameObserver)


        adapter = PhottoslistAdapter(
            this,
            photos
        )
        recycleView.adapter = adapter


        adapter.onItemClick = { item ->
            val intent = Intent(this, PhotoDetail::class.java)
            val gson = Gson()
            val photoItem = gson.toJson(item)
            intent.putExtra("photojson", photoItem)
            startActivity(intent)

        }


        RxJavaPlugins.setErrorHandler { throwable ->
            throwable.printStackTrace()
            progressbarlayout.visibility = View.INVISIBLE
            Toast.makeText(this, Util.showError(throwable), Toast.LENGTH_LONG).show()
            Log.e(this@MainActivity.javaClass.name, throwable.message)

        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                makeRequest(query!!)
                hidesuggestions()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                displaysuggestions()
                return false
            }


        })


    }

    private fun displaysuggestions() {
        val names = arrayOf("DOGS", "CATS", "APPLES", "CHIKS")

        val lv =
            findViewById<View>(R.id.listview) as ListView
        lv.visibility = View.VISIBLE
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        lv.adapter = adapter
        lv.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, (parent[position] as TextView).text.toString(), Toast.LENGTH_LONG)
                .show()
            lv.visibility = View.GONE
        }


    }

    private fun hidesuggestions() {

        val lv =
            findViewById<View>(R.id.listview) as ListView
        lv.visibility = View.GONE

    }


    private fun makeRequest(query: String) {
        progressbarlayout.visibility = View.VISIBLE
        try {
            modelView.getData(query.toString())
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Please try with other words", Toast.LENGTH_LONG)
                .show()
            Log.e(this@MainActivity.javaClass.name, e.message)
            progressbarlayout.visibility = View.INVISIBLE
        }
    }

    private fun castViews() {
        textView = findViewById(R.id.textView)
        recycleView = findViewById<RecyclerView>(R.id.recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = GridLayoutManager(this, 2)

        progressbarlayout = findViewById(R.id.llProgressBar)
        searchView = findViewById(R.id.searchView)


    }


}

