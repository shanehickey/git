package com.project.GeneticAlgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Chromosome {
//	private boolean isFitnessChanged;
	private int fitness;
	private int[] genes;
	
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
//	private static int[] trips;{
//		trips[0] = 3;
//		trips[1] = 3;
//		trips[2] = 9;
//	};
//	
//	
//	private static int[] cars;{
//		cars[0] = 0;
//		cars[1] = 1;
//	}
	
	public Chromosome(int length){
		genes = new int[length];
		

		map.put(0, 1);
		map.put(1, 2);
		map.put(2, 3);
		map.put(3, 4);
		map.put(4, 5);
		map.put(5, 6);
		map.put(6, 7);
		map.put(7, 8);
		map.put(8, 8);
		map.put(9, 8);
		
	}
	
	private int getNumberOfCars(){
		return 3;
	}
	
	public int[] getGenes(){
//		isFitnessChanged = true;
		return genes;
	}
	
	public int getFitness(){
//		if(isFitnessChanged){
			fitness = calculateFitness();
//			isFitnessChanged = false;
//		}
		return fitness;
	}
	
	public void setFitness(int newFitness){
		fitness = newFitness;
	}
	
	public int calculateFitness(){
		int cost = 0;
		for(int i = 0 ; i < genes.length ; i++){
			for(int j = i + 1 ; i < genes.length ; i++){
				if(genes[i] == genes[j] && map.get(i).equals(map.get(j))){
					cost += 1;
				}
			}
		}
		
		int chromosomeFitness = 100 - cost;
			//for every gene compute fitness
			//heavily penalise clashes etc.
			//can be duplicates without accounting for time
		
		if(isLegal()){
			chromosomeFitness += 100;
		}
		
		return chromosomeFitness;
	}
	
	public boolean isLegal(){
		for(int i = 0 ; i < genes.length ; i++){
			for(int j = i + 1 ; j < genes.length ; j++){
				if(genes[i] == genes[j] && map.get(i).equals(map.get(j))){
					return false;
				}
			}
		}
		return true;
	}
	
	public Chromosome initialiseChromosome(){
		Random rand = new Random();
		
		for(int i = 0 ; i < genes.length ; i++){
			genes[i] = rand.nextInt(getNumberOfCars());
		}
		
		return this;
	}
	
	public String toString(){
		return Arrays.toString(this.genes);
	}
	
	
}
