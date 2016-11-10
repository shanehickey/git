package com.project.GeneticAlgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {
	Chromosome[] chromosomes;
	
	public Population(int length){
		chromosomes = new Chromosome[length];
	}
	
	public Chromosome[] getChromosomes(){
		return chromosomes;
	}
	
	public Chromosome[] sortChromosomesByFitness(){
		List<Chromosome> list = Arrays.asList(chromosomes);
		Collections.sort(list, new Comparator<Chromosome>() {
			   public int compare(Chromosome c1, Chromosome c2) {
				   if(c1.getFitness() < c2.getFitness()){
					   return 1;
				   } else if (c1.getFitness() > c2.getFitness()){
					   return -1;
				   } else {
					   return 0;
				   }
			   }
			});
		
		Chromosome[] array = new Chromosome[list.size()];
		return list.toArray(array);
	}
	
	public Population initialisePopulation(){
		for(int i = 0 ; i < chromosomes.length ; i++){
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_LENGTH).initialiseChromosome();
			chromosomes[i].setFitness(chromosomes[i].calculateFitness());
		}
		sortChromosomesByFitness();
		return this;
	}
}
