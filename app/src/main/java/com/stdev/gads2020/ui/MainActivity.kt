package com.stdev.gads2020.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.stdev.gads2020.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting up the action bar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        //setting up the custom action bar
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        val inflater : LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val actionBarView = inflater.inflate(R.layout.custom_toolbar,null)
        actionBar?.customView = actionBarView

        val customSubmitButton = findViewById<Button>(R.id.button_submit_menu)
        customSubmitButton.setOnClickListener {
            submit()
        }

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }

    private fun submit() {
        Toast.makeText(this,"About to Submit!",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SubmitActivity::class.java)
        startActivity(intent)
    }
}