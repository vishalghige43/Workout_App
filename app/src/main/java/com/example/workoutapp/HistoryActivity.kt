package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var binding:ActivityHistoryBinding?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        setSupportActionBar(binding?.toolBarHistory);
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding?.toolBarHistory?.setNavigationOnClickListener{
            onBackPressed();
        }
    }
}