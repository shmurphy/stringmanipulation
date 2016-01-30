/*
 * TCSS 342
 * Assignment 2 - Evolved Names
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Contains a list of Genomes representing all the strings in my "world"
 * for an evolutionary system.
 * 
 * @author Shannon Murphy
 * @version April 24, 2015
 *
 */
public class Population {
	public String myTarget = "SHANNON NICOLE MURPHY";
	public Genome myMostFit;
	private List<Genome> myGenomes;
	private Integer mySize;
	
	public Population(final Integer theNumGenomes, final Double theMutationRate) {
		myGenomes = new LinkedList<>();
		mySize = theNumGenomes;
		
		for (int i = 0; i < theNumGenomes; i++) {
			myGenomes.add(new Genome(theMutationRate));
			myGenomes.get(i).setTarget(myTarget);
		}
		myMostFit = myGenomes.get(0);
	}
	
	public void day() {
		// sort the enomes and get most fit
		Collections.sort(myGenomes);
		myMostFit = myGenomes.get(0);
		// remove the least fit genomes
		for (int i = mySize - 1; i >= mySize / 2; i--) {
			myGenomes.remove(i);
		}
		while (myGenomes.size() < mySize) { // until enough genomes are added back
			Genome newGenome = null;
			Random rand = new Random();
			int fifty = rand.nextInt(2);
			if (fifty == 0) {
				int randGenome = rand.nextInt(myGenomes.size()); // pick random genome index
				newGenome = new Genome(myGenomes.get(randGenome)); // clone
				newGenome.mutate();
			} else if (fifty == 1) {
				int randGenome = rand.nextInt(myGenomes.size());
				newGenome = new Genome(myGenomes.get(randGenome));
				int crossoverIndex = rand.nextInt(myGenomes.size()); // index for second random genome
				newGenome.crossover(new Genome(myGenomes.get(crossoverIndex)));
				newGenome.mutate();
			}
			myGenomes.add(newGenome);
		}
	}
	
	public String toString() {
		return myGenomes.toString();
	}
}
