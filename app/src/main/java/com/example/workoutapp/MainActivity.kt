package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding?.bmiBtn?.setOnClickListener {
            val intent:Intent=Intent(this,BmiActivity::class.java);
            startActivity(intent);
        }
        binding?.HistoryBtn?.setOnClickListener {
            val intent:Intent=Intent(this,HistoryActivity::class.java);
            startActivity(intent);
        }
    }

    override fun onBackPressed() {
        var blurBac=BlurDialogBoxFragment();

        blurBac.dialogListener=object : BlurDialogBoxFragment.DialogListener{
            override fun onPositiveButtonClick() {
                this@MainActivity.finish();
            }
            override fun onNegativeButtonClick() {
            }
        }
        blurBac.show(supportFragmentManager,blurBac.javaClass.simpleName);
    }

    override fun onDestroy() {
        super.onDestroy();
        binding=null;
    }
}