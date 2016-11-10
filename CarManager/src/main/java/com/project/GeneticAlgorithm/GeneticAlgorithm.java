package com.project.GeneticAlgorithm;

import java.util.Random;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE = 8;
	public static final int TARGET_LENGTH = 10;
	private static final int NR_ELITE_CHROMOSOMES = 1;
	private static final int TOURNAMENT_SIZE = POPULATION_SIZE / 2;
	private static final double MUTATION_RATE = 0.25;
	
	public Population evolve(Population population){
		return mutatePopulation(crossoverPopulation(population));
	}
	
	private Population crossoverPopulation(Population population){
		Population crossoverPopulation = new Population(population.getChromosomes().length);
		
		for(int i = 0 ; i < NR_ELITE_CHROMOSOMES ; i++){
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		
		for(int i = NR_ELITE_CHROMOSOMES ; i < POPULATION_SIZE ; i++){
			Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
			Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
		}
		
		return crossoverPopulation;
	}
	
	private Population mutatePopulation(Population population){
		Population mutatePopulation = new Population(population.getChromosomes().length);
		
		for(int i = 0 ; i < NR_ELITE_CHROMOSOMES ; i++){
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		
		for(int i = NR_ELITE_CHROMOSOMES ; i < POPULATION_SIZE ; i++){
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		
		return mutatePopulation;
	}
	
	private Chromosome crossoverChromosome(Chromosome c1, Chromosome c2){
		Chromosome crossoverChromosome = new Chromosome(TARGET_LENGTH);
		
		for(int i = 1 ; i < 3 ; i++){
			int temp = c2.getGenes()[i];
			c2.getGenes()[i] = c1.getGenes()[i];
			c1.getGenes()[i] = temp;
		}
		
		return crossoverChromosome;
	}
	
	private Chromosome mutateChromosome(Chromosome c){
		Chromosome mutateChromosome = new Chromosome(TARGET_LENGTH);

		Random rand = new Random();
		
		for(int i = 0 ; i < TARGET_LENGTH ; i++){
			if(Math.random() < MUTATION_RATE){
				mutateChromosome.getGenes()[i] = rand.nextInt(POPULATION_SIZE);
			} else {
				mutateChromosome.getGenes()[i] = c.getGenes()[i];
			}
		}
		
		return mutateChromosome;
	}
	
	private Population selectTournamentPopulation(Population population){
		Population tournamentPopulation = new Population(TOURNAMENT_SIZE);
		Random rand = new Random();
		
		for(int i = 0 ; i < TOURNAMENT_SIZE ; i++){
			tournamentPopulation.getChromosomes()[i] = population.getChromosomes()[rand.nextInt(POPULATION_SIZE)];
		}
		
		tournamentPopulation.sortChromosomesByFitness();
		
		return tournamentPopulation;
	}
}
