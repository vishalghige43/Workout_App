package com.example.workoutapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding?.root);

        binding?.flStart?.setOnClickListener {
            val intent:Intent=Intent(this,ExersiceActivity::class.java);
            startActivity(intent);
        }
    }

    override fun onDestroy() {
        super.onDestroy();
        binding=null;
    }
}