package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding?.toolBarEx?.setNavigationOnClickListener{
            onBackPressed();
        }
        binding?.finishBtn?.setOnClickListener{
            var intent: Intent = Intent(this@FinishActivity,MainActivity::class.java);
            startActivity(intent);
        }
    }
}