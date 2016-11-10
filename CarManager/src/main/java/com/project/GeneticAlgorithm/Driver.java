package com.project.GeneticAlgorithm;

import java.util.Arrays;

public class Driver {
	
    public static void main(String[] args){
    	Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initialisePopulation();
    	GeneticAlgorithm ga = new GeneticAlgorithm();
    	printPopulation(population);
    	
    	int generationNumber = 0;
    	
    	while(generationNumber < 10000 && population.getChromosomes()[0].getFitness() < 100){
    		ga.evolve(population);
    		generationNumber++;
    	}

    	printPopulation(population);
    }
    
    public static void printPopulation(Population population){
    	System.out.println("Heading");
    	System.out.println("------------------------------");
    	for (int i = 0 ; i < population.getChromosomes().length ; i++){
        	System.out.println(Arrays.toString(population.getChromosomes()));
    	}
    	System.out.println("Fittest is " + population.getChromosomes()[0].getFitness());
    }
}
