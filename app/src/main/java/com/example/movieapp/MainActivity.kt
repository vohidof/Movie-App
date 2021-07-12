package com.example.movieapp

import Adapter.RvAdapter
import Cache.MySharedPreference
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_rv.*

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        img_add.setOnClickListener {
            startActivity(Intent(this, AddMovie::class.java))
        }
    }

    override fun onStart() {
        super.onStart()


        MySharedPreference.init(this)
        val list = MySharedPreference.objectString
        rvAdapter = RvAdapter(this, list)

        rvAdapter.notifyDataSetChanged()
        recyclerMovies.adapter = rvAdapter

    }
}