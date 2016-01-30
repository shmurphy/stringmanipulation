/*
 * TCSS 342
 * Assignment 2 - Evolved Names
 */

/**
 * Controller for the evolution.
 * 
 * @author Shannon Murphy
 * @version 4/24/15
 */
public class Main {

	/**
	 * Driver for the Population class.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		Population p = new Population(100, 0.05);
		int i = 0;
		long startTime = System.currentTimeMillis();
		
		while (p.myMostFit.fitness() != 0) {
			p.day();
			i++;
			System.out.println("(\"" + p.myMostFit + "\", " + p.myMostFit.fitness() + ")");
		}
		System.out.println("Number of times day was called: " + i);
		System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
		
//		testGenome();
//		testPopulation();
	}
	
	public static void testGenome() {
		Genome g = new Genome(0.5); // tested at rate 0.5 to see more changes to test methods
		Genome g2 = new Genome(0.5);
		g.mutate();
		System.out.println("G: " + g);
		g.crossover(g2);
		System.out.println("g after crossover with g2: " + g);
		g2.mutate();
		g.mutate();
		g.crossover(g2);
		System.out.println("After mutate & crossover: " + g);
		
	}
	
	public static void testPopulation() {
		Population p = new Population(100, 0.05);
		System.out.println(p);
		p.day();
		System.out.println(p);
		p.day();
		System.out.println(p);
	}

}
