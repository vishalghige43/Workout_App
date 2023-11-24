package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    private var binding:ActivityBmiBinding?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        setSupportActionBar(binding?.toolBarBmi);
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding?.toolBarBmi?.setNavigationOnClickListener{
            onBackPressed();
        }
        binding?.calculateBtn?.setOnClickListener {
            binding?.dispVAl?.visibility=View.INVISIBLE;
            if(isValid()) calculate();
            else{
                Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun isValid():Boolean{
        if(binding?.heightIn?.text.toString()=="")return false;
        if(binding?.weightIn?.text.toString()=="")return false;
        return true;
    }
    private fun calculate(){
        var height:Float=binding?.heightIn?.text.toString().toFloat()/100;
        var weight:Float=binding?.weightIn?.text.toString().toFloat();

        var result:Float=(weight)/(height*height);
        var answer:String=String.format("%.2f",(weight)/(height*height));

        binding?.dispVAl?.visibility=View.VISIBLE;
        binding?.bmiVal?.text=answer;

        if(result<18.5){
            binding?.bmiStats?.text="Your are Underweight";
            return;
        }
        if(result>24.5){
            binding?.bmiStats?.text="Your are Overweight";
            return;
        }
        binding?.bmiStats?.text="Your are Fit";
    }
}