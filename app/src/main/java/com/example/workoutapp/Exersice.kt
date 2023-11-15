package com.example.workoutapp


class Exersice (
    private var id:Int,
    private var name:String,
    private var image:Int,
    private var isCompleted:Boolean=false,
    private var isSelected:Boolean=false){
    fun getId():Int{
        return id;
    }
    fun getName():String{
        return name;
    }
    fun getImg():Int{
        return image;
    }
    fun getIsCom():Boolean{
        return isCompleted;
    }
    fun getIsSel():Boolean{
        return isSelected;
    }
    fun setId(id: Int){
        this.id=id;
    }
    fun setName(name:String){
        this.name=name;
    }
    fun setImg(img: Int){
        this.image=img;
    }
    fun setIsComp(isCompleted: Boolean){
        this.isCompleted=isCompleted;
    }
    fun setIsSel(isSelected: Boolean){
        this.isSelected=isSelected;
    }

}