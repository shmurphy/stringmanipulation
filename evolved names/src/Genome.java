/*
 * TCSS 342
 * Assignment 2 - Evolved Names
 */

import java.util.Random;

/**
 * Represents a String in my "world" for an evolutionary system.
 * 
 * @author Shannon Murphy
 * @version 4/24/15
 *
 */
public class Genome implements Comparable<Genome> {
	String myString;
	double myRate;
	char[] myChars;
	String myTarget;
	
	public Genome(final double theMutationRate) {
		myString = "A";
		myChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-'};
		myRate = theMutationRate;
	}
	
	public Genome(final Genome theGene) {
		myString = theGene.myString;
		myChars = theGene.myChars;
		myRate = theGene.myRate;
		myTarget = theGene.myTarget;
	}
	
	public void setTarget(final String theTarget) {
		myTarget = theTarget;
	}
	
	public String getTarget() {
		return myTarget;
	}
	
	public void mutate() {
		Random rand = new Random();
	
		double addChance = Math.random();
		if (addChance < myRate) {
			addCharacter(rand);
		}

		double removeChance = Math.random();
		if (removeChance < myRate) {
			if (myString.length() >= 2) {
				removeCharacter(rand);
			}
		}
		
		double replaceChance = Math.random();
		if (replaceChance < myRate) {
			replaceCharacter(rand);
		}
	}
	
	public void crossover(Genome theOther) {
		String parentString = "";
		String newString = "";
		Random rand = new Random();
		boolean finished = false;
		int index = 0;
		while (!finished) {
			int genome = rand.nextInt(2);
			if (genome == 0) {
				parentString = myString;
			} else {
				parentString = theOther.toString();
			}
			if (index < parentString.length()) {
				newString += parentString.charAt(index);
			} else if (index >= parentString.length()) {
				finished = true;
			}
			index++;
		}		
		myString = newString;
	}
	
	public Integer fitness() {
		int n = myString.length();
		int m = myTarget.length();
		int l = Math.max(n,  m);
		int j = Math.min(n, m);
		int f = Math.abs(m-n);
		for (int i = 0; i < l; i++) {
			if (i < j) {
				if (myString.charAt(i) != myTarget.charAt(i)) {
					f++;
				}
			} else {
				f++;
			}
		}
		return f;
	}
	
	public String toString() {
		return myString;
	}
	
	@Override
    public int compareTo(Genome theOther) {
        if (this.fitness() < theOther.fitness()) {
            return -1;
        } else if (this.fitness() > theOther.fitness()) {
            return 1;
        } else {
        	return 0;
        }
    }
	
	private void addCharacter(final Random theRand) {
		int randAddIndex = theRand.nextInt(myString.length() + 1);
		int randCharIndex = theRand.nextInt(28);
		char randChar = myChars[randCharIndex];
		myString = myString.substring(0, randAddIndex) + randChar + myString.substring(randAddIndex);
	}
	
	private void removeCharacter(final Random theRand) {
		int randRemoveIndex = theRand.nextInt(myString.length());
		myString = myString.substring(0, randRemoveIndex) + myString.substring(randRemoveIndex+1);
	}
	
	private void replaceCharacter(final Random theRand) {
		int randRemoveIndex = theRand.nextInt(myString.length());
		int replaceCharIndex = theRand.nextInt(28);
		char replaceChar = myChars[replaceCharIndex];
		myString = myString.substring(0, randRemoveIndex) + replaceChar + myString.substring(randRemoveIndex+1);
	}
}
