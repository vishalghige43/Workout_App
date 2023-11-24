package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

    private  var exTimeInSec:Int=30;
    private  var restTimeInSec:Int=10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExersiceBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        setSupportActionBar(binding?.toolBarEx);
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding?.toolBarEx?.setNavigationOnClickListener{
            onBackPressed();
        }
        exList=Constants.defaultExList();

        tts= TextToSpeech(this,this);
        setupTimer();
        setupExerciseReView();
    }

    override fun onBackPressed() {
        customOnBackPressed()
//        super.onBackPressed()
    }
    private fun customOnBackPressed(){
        var blurBac=BlurDialogBoxFragment();
        blurBac.dialogListener=object : BlurDialogBoxFragment.DialogListener{
            override fun onPositiveButtonClick() {
                this@ExersiceActivity.finish();
            }
            override fun onNegativeButtonClick() {
            }
        }
        blurBac.show(supportFragmentManager,blurBac.javaClass.simpleName);
//        var customDialog=Dialog(this@ExersiceActivity);
//        var dialogBinding= CustomDialogboxOnBackPressBinding.inflate(layoutInflater);


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
        restTimer=object :CountDownTimer((restTimeInSec*1000).toLong(),1000){
            override fun onTick(p0: Long) {
                restProgress++;
                binding?.progressBar?.progress=restTimeInSec-restProgress;
                binding?.tvTimer?.text=(restTimeInSec-restProgress).toString();
                if((restTimeInSec-restProgress)<4 &&(restTimeInSec-restProgress)>0){
                    speakOut((restTimeInSec-restProgress).toString());
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
        ExTimer=object :CountDownTimer((exTimeInSec*1000).toLong(),1000){
            override fun onTick(p0: Long) {
                ExProgress++;
                binding?.progressExBar?.progress=exTimeInSec-ExProgress;
                binding?.tvExTimer?.text=(exTimeInSec-ExProgress).toString();
                if((exTimeInSec-ExProgress)<4&&(exTimeInSec-ExProgress)>0){
                    speakOut((exTimeInSec-ExProgress).toString());
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
                    var intent:Intent=Intent(this@ExersiceActivity,FinishActivity::class.java);
                    startActivity(intent);
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