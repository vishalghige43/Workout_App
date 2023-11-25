package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {
    private var usUnits:Boolean=false;
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
        binding?.rgUnits?.setOnCheckedChangeListener{_,checkId:Int->
            binding?.dispVAl?.visibility=View.INVISIBLE;
            clearDisplay();
            if(checkId==R.id.rbMetricUnits){
                usUnits=false;
                binding?.inputLL?.visibility=View.VISIBLE;
                binding?.inputUsLL?.visibility=View.INVISIBLE;
            }
            else{
                usUnits=true;
                binding?.inputUsLL?.visibility=View.VISIBLE;
                binding?.inputLL?.visibility=View.INVISIBLE;
            }
        }

        binding?.calculateBtn?.setOnClickListener {
            binding?.dispVAl?.visibility=View.INVISIBLE;
            if(isValid()) {
                if(usUnits) calculateUsUnits()
                else calculateMetricUnits()
            }
            else{
                Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun clearDisplay(){
        binding?.InchesUsIn?.text?.clear();
        binding?.feetUsIn?.text?.clear();
        binding?.weightUsIn?.text?.clear();
        binding?.heightIn?.text?.clear();
        binding?.weightIn?.text?.clear();
    }
    private fun isValid():Boolean{
        if(usUnits){
            if(binding?.InchesUsIn?.text.toString()=="")return false;
            if(binding?.feetUsIn?.text.toString()=="")return false;
            if(binding?.weightUsIn?.text.toString()=="")return false;
            return true;
        }
        if(binding?.heightIn?.text.toString()=="")return false;
        if(binding?.weightIn?.text.toString()=="")return false;
        return true;
    }
    private fun calculateUsUnits(){
        var height=(binding?.feetUsIn?.text.toString().toFloat()*30)+(binding?.InchesUsIn?.text.toString().toFloat()*2.54);
        var weight=binding?.weightUsIn?.text.toString().toFloat()*0.453592;
        height=height/100;
        var result: Double =(weight)/(height*height);
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
    private fun calculateMetricUnits(){
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