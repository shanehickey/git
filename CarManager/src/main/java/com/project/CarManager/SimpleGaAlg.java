package com.project.CarManager;
 
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
 
public class SimpleGaAlg {
    Car c;
    Genotype population;
    
    public SimpleGaAlg(Car p){
        this.c = p;
    }
 
    public void create() throws InvalidConfigurationException {
        // Create a GA configuration and use our fitness function
        Configuration conf = new DefaultConfiguration();
        FitnessFunction fitnessFunction = new CarterFitnessFunction(c);
        conf.setFitnessFunction(fitnessFunction);
 
        // Create a sample chromosome so that JGAP can create new ones			XXXXXXXXXXX
        Gene[] sampleGenes = new Gene[c.getE()];
        for (int i = 0; i < c.getE(); i++){
            sampleGenes[i] = new IntegerGene(conf, 0, c.getP() - 1);
        }
        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
 
        // Use the sample chromosome in the configcuration
        conf.setSampleChromosome(sampleChromosome);
 
        // Set the population size default 100
        conf.setPopulationSize(100);
 
        // Initialize the population with random members
        population = Genotype.randomInitialGenotype( conf );
    }
    
//    public void clearAllToZero(){
//    	population = new Genotype();
//    }

    public void evolve(int numberGens) {
        int step = 10;
        for(int i=0; i <numberGens; i+=step) {
            for(int s=0; s<step; s++){
                population.evolve();
            }
            IChromosome bestsol = population.getFittestChromosome();
            System.out.println("Iteration " + (i+step) + ", Best chromosome: " + bestsol.getFitnessValue());
            System.out.println(bestsol.toString());
        }
    }
    
    public void printResults(){
    	IChromosome bestsol = population.getFittestChromosome();
    	
    	for(int i = 0 ; i < 24 ; i++){
    		System.out.print(((Integer) bestsol.getGene(i).getAllele()).intValue() + ", ");
    	}
    	
    	System.out.println();
    	
    	for(int i = 24 ; i < 48 ; i++){
    		System.out.print(((Integer) bestsol.getGene(i).getAllele()).intValue() + ", ");
    	}
    }
    
    public static void main(String[] args) throws InvalidConfigurationException{
    	SimpleGaAlg ga = new SimpleGaAlg(new Car());
    	ga.create();
    	ga.evolve(1000);
    	ga.printResults();
    }
}