package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityExersiceBinding
import java.util.Locale

class ExersiceActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExersiceBinding?=null;

    private var restTimer:CountDownTimer?=null;
    private var restProgress=0;
    private var ExTimer:CountDownTimer?=null;
    private var ExProgress=0;

    private var exList:ArrayList<Exersice>?=null;
    private var currEx=-1;

    private var tts:TextToSpeech?=null;

    private var exerciseStatus:ExerciseAdapter?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExersiceBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        setSupportActionBar(binding?.toolBarEx);
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        exList=Constants.defaultExList();
        binding?.toolBarEx?.setNavigationOnClickListener{
            onBackPressed();
        }
        tts= TextToSpeech(this,this);
        setupTimer();
        setupExerciseReView();
    }
    private fun setupExerciseReView(){
        binding?.rvExStatus?.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        exerciseStatus=ExerciseAdapter(exList!!);
        binding?.rvExStatus?.adapter=exerciseStatus;
    }
    override fun onInit(p0: Int) {
        if(p0==TextToSpeech.SUCCESS){
            val result=tts!!.setLanguage(Locale.US);
            if(result==TextToSpeech.LANG_MISSING_DATA||
                result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","NOT SUPPORTED");
            }
        }
        else{
            Log.e("TTS","INIT FAILED");
        }
    }
    fun speakOut(str:String){
        tts!!.speak(str,TextToSpeech.QUEUE_ADD,null,"");
        Log.e("TTS Spoken",str);
    }
    private fun setupTimer(){
        binding?.flProgressBar?.visibility= View.VISIBLE;
        binding?.tvTitle?.visibility=View.VISIBLE;
        binding?.tvEx?.visibility=View.INVISIBLE;
        binding?.exImg?.visibility=View.INVISIBLE;
        binding?.flProgressExBar?.visibility= View.INVISIBLE;
        currEx++;
        binding?.upExName?.visibility=View.VISIBLE;
        binding?.upEx?.visibility=View.VISIBLE;
        binding?.upExName?.text= exList!![currEx].getName();
        speakOut("Rest time")

        if(restProgress!=null){
            ExTimer?.cancel();
            restProgress=0;
        }
        restProgress=0;
        restProgressBar();
    }

    private fun restProgressBar(){
        binding?.progressBar?.progress=restProgress;
        restTimer=object :CountDownTimer(1000,1000){
            override fun onTick(p0: Long) {
                restProgress++;
                binding?.progressBar?.progress=10-restProgress;
                binding?.tvTimer?.text=(10-restProgress).toString();
                if((10-restProgress)<4 &&(10-restProgress)>0){
                    speakOut((10-restProgress).toString());
                }
            }
            override fun onFinish() {
                exList!![currEx].setIsSel(true);
                exerciseStatus?.notifyDataSetChanged();
                setupExTimer();
            }
        }.start();
    }
    private fun setupExTimer(){
        binding?.flProgressBar?.visibility= View.INVISIBLE;
        binding?.tvTitle?.visibility=View.INVISIBLE;
        binding?.tvEx?.visibility=View.VISIBLE;
        binding?.exImg?.visibility=View.VISIBLE;
        binding?.flProgressExBar?.visibility= View.VISIBLE;
        binding?.upExName?.visibility=View.INVISIBLE;
        binding?.upEx?.visibility=View.INVISIBLE;

        binding?.exImg?.setImageResource(exList!![currEx].getImg());
        binding?.tvEx?.text=exList!![currEx].getName();

        exList!![currEx].setIsSel(true);
        exerciseStatus?.notifyDataSetChanged();


        ExProgress=0;

        if(ExProgress!=null){
            ExTimer?.cancel();
            ExProgress=0;
        }

        speakOut("Now "+exList!![currEx].getName());
        ExProgressBar();
    }

    private fun ExProgressBar(){
        binding?.progressExBar?.progress=ExProgress;
        ExTimer=object :CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                ExProgress++;
                binding?.progressExBar?.progress=30-ExProgress;
                binding?.tvExTimer?.text=(30-ExProgress).toString();
                if((30-ExProgress)<4&&(30-ExProgress)>0){
                    speakOut((30-ExProgress).toString());
                }
            }
            override fun onFinish() {
                exList!![currEx].setIsSel(false);
                exList!![currEx].setIsComp(true);

                exerciseStatus?.notifyDataSetChanged();
                if(currEx< exList?.size!!-1){
                    setupTimer();
                }
                else {
                    Toast.makeText(this@ExersiceActivity,
                        "Completed",Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }
    override fun onDestroy() {
        super.onDestroy();
        if(restProgress!=null){
            restTimer?.cancel();
            restProgress=0;
        }
        if(ExProgress!=null){
            ExTimer?.cancel();
            ExProgress=0;
        }
        if(tts!=null){
            tts!!.stop();
            tts!!.shutdown();
        }
        binding=null;
    }
}