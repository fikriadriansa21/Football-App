package com.fikriadriansa.footballschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fikriadriansa.footballschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import java.text.FieldPosition

class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var id: String
    private lateinit var playerName: String
    private lateinit var description: String
    private lateinit var imageFanart: String
    private lateinit var weight: String
    private lateinit var height: String
    private lateinit var position: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        playerName = intent.getStringExtra("playerName")
        imageFanart = intent.getStringExtra("fanart")
        description = intent.getStringExtra("strDescription")
        weight = intent.getStringExtra("weight")
        height = intent.getStringExtra("height")
        position = intent.getStringExtra("position")


        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tv_weight.text = weight
        tv_height.text = height
        tv_player_detail_position.text = position
        tv_player_overview.text = description
        Picasso.get().load(imageFanart).into(img_player_detail)
    }
}
