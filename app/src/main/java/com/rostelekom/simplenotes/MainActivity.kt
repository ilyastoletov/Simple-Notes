package com.rostelekom.simplenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.rostelekom.simplenotes.data.room.model.NotesModel
import com.rostelekom.simplenotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Notes"
        navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)

    }

}