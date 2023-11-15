package com.example.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ExNumberBinding

class ExerciseAdapter(val list:ArrayList<Exersice>): RecyclerView.Adapter<ExerciseAdapter.ViewHolder>(){
    class ViewHolder(val binding:ExNumberBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvExNumber;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ExNumberBinding.inflate(LayoutInflater.from(parent.context),parent,false));
    }

    override fun getItemCount(): Int {
        return list.size;
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exerciseModel=list[position];
        holder.tvItem.text=exerciseModel.getId().toString();
        when{
            exerciseModel.getIsSel()->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.ex_number_seleted);
                holder.tvItem.setTextColor(Color.parseColor("#c4c4c4"))
            }
            exerciseModel.getIsCom()->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.ex_number_com);
                holder.tvItem.setTextColor(Color.parseColor("#ffffff"))
            }
            else->{
                holder.tvItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,R.drawable.ex_number);
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }
}