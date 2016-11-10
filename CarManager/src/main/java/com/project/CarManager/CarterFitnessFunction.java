package com.project.CarManager;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class CarterFitnessFunction extends FitnessFunction {
	private static final long serialVersionUID = 5866439090560969516L;
	Car c;
//    double w[] = {0.0, 16.0, 8.0, 4.0, 2.0, 1.0};
 
    public CarterFitnessFunction(Car c) {
        this.c = c;
    }
    
//    public boolean anotherCarHasIt(){
//    	
//    }
    //Just need to implementfitness function and determine representation of problem
    
    private double getCost(IChromosome timetable) {
        double cost = 0.0;
        int numSlots = c.getE();
        
        //if this goes upto 350 changes everything
        if(areDuplicates(timetable)){
        	cost += 300;
        }
 
        for(int timeSlot = 0; timeSlot < numSlots; timeSlot++) {
        	if(timeSlot % 24 == 1 || timeSlot % 24 == 2 ||
            		timeSlot % 24 == 21 || timeSlot % 24 == 22 || timeSlot % 24 == 23 || timeSlot % 24 == 20 ||
            		timeSlot % 24 == 1){
	            if(((Integer) timetable.getGene(timeSlot).getAllele()).intValue() == 0){
	            	cost += 10;
	            }
        	} else if(((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 0){
            	cost += 10;
        	}
        	
        	if((timeSlot % 24 == 1 || timeSlot % 24 == 2) && ((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 1){
        		cost += 1;
        	}
        	
        	if((timeSlot % 24 == 21 || timeSlot % 24 == 22 || timeSlot % 24 == 23 || timeSlot % 24 == 20) &&
        			((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 2){
        		cost += 1;
        	}
        	
        	if((timeSlot % 24 == 1) && ((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 3){
        		cost += 1;
        	}
        }
        return cost;
    }
 
 
//    private double getCost(IChromosome timetable) {
//        double cost = 0.0;
//        int numSlots = c.getE();
// 
//        for(int exam1=0; exam1<numSlots; exam1++) {
//            int period1 = ( (Integer) timetable.getGene(exam1).getAllele()).intValue();
//            for(int exam2=exam1+1; exam2<numSlots; exam2++) {
//                int period2 = ( (Integer) timetable.getGene(exam2).getAllele()).intValue();
//                int d = Math.abs(period1 - period2);
//                if(d<6){
//                    cost += w[d]*c.getConflicts(exam1, exam2);
//                }
//            }
//        }
//        return cost/c.getS();
//    }
    
    private boolean isLegal(IChromosome timetable) {
        int numSlots = c.getE();
        
        if(areDuplicates(timetable)){
        	return false;
        }
 
        for(int timeSlot = 0; timeSlot < numSlots; timeSlot++) {
        	if(timeSlot % 24 == 9 || timeSlot % 24 == 10 ||
            		timeSlot % 24 == 21 || timeSlot % 24 == 22 || timeSlot % 24 == 23 || timeSlot % 24 == 20 ||
            		timeSlot % 24 == 1){
	            if(((Integer) timetable.getGene(timeSlot).getAllele()).intValue() == 0){
	            	return false;
	            }
        	}
        	
//        	if((timeSlot % 24 == 9 || timeSlot % 24 == 10) && ((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 1){
//        		return false;
//        	}
//        	
//        	if((timeSlot % 24 == 21 || timeSlot % 24 == 22 || timeSlot % 24 == 23 || timeSlot % 24 == 20) &&
//        			((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 2){
//        		return false;
//        	}
//        	
//        	if((timeSlot % 24 == 1) && ((Integer) timetable.getGene(timeSlot).getAllele()).intValue() != 3){
//        		return false;
//        	}
        	
        }
        return true;
    }
    
    private boolean areDuplicates(IChromosome timetable){    	 
         for(int timeSlot = 0; timeSlot < 24; timeSlot++) {
        	 int original = ((Integer) timetable.getGene(timeSlot).getAllele()).intValue();
//        	 for(int i = 1 ; i < 2 ; i++){
    		 int comparator = ((Integer) timetable.getGene(timeSlot + 24).getAllele()).intValue();
    		 if(original == comparator){
    			 return true;
    		 }
//        	 }
         }
         return false;
    }
 
 
//    private boolean isLegal(IChromosome timetable) {
//        int numExams = c.getE();
// 
//        for(int exam1=0; exam1<numExams; exam1++) {
//            int period1 = ( (Integer) timetable.getGene(exam1).getAllele()).intValue();
//            for(int exam2=exam1+1; exam2<numExams; exam2++) {
//                int period2 = ( (Integer) timetable.getGene(exam2).getAllele()).intValue();
//                if(period1 == period2) {
//                    if(c.areExamsInConflict(exam1, exam2)){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
 
    @Override
    protected double evaluate(IChromosome timetable) {
        double cost = getCost(timetable);
        double maxFit = 1000.0;
        double fitness;
 
        fitness = (cost<maxFit) ? maxFit - cost : maxFit;
        if(isLegal(timetable)){
        	fitness += 2*maxFit;
        }
        return fitness;
    }
    
}