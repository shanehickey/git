package com.project.CarManager;

public class Car {
	
	int[] todaySlots;
	
	public Car(){
		todaySlots = new int[24];
	}
	
	public int getConflicts(int exam1, int exam2){
		return 3;
	}
	
	//number of genes i.e. no of hours in the day or week
	public int getE(){
		return 48;
	}
	
	//maximum possible value for each gene i.e. number of trips
	public int getP(){
		return 4;
	}
	
	//
	public int getS(){
		return 48;
	}
	
	public boolean areExamsInConflict(int a, int b){
		return a == b;
	}
}