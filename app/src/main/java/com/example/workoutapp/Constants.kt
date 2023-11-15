package com.example.workoutapp

object Constants {
    fun defaultExList():ArrayList<Exersice>{
        var exList=ArrayList<Exersice>();
        val ex1=Exersice(1,"SQUATS",R.drawable.ex1);
        exList.add(ex1);
        val ex2=Exersice(2,"PUSH UP",R.drawable.ex2);
        exList.add(ex2);
        val ex3=Exersice(3,"PUNCHES ",R.drawable.ex3);
        exList.add(ex3);
        val ex4=Exersice(4,"LUNGES",R.drawable.ex4);
        exList.add(ex4);
        val ex5=Exersice(5,"HAND RAISE PLANK",R.drawable.ex5);
        exList.add(ex5);
        val ex6=Exersice(6,"MOUNTAIN CLIMBER",R.drawable.ex6);
        exList.add(ex6);
        val ex7=Exersice(7,"CRUNCHES",R.drawable.ex7);
        exList.add(ex7);
        val ex8=Exersice(8,"LEG RAISE",R.drawable.ex8);
        exList.add(ex8);
        return exList;
    }
}