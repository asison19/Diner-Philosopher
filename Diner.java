package dinerPhilosopher;

import java.util.concurrent.locks.ReentrantLock;

public class Diner {
	
	public static void main(String[] args) {
		
		//our "forks" used to eat
		ReentrantLock ab = new ReentrantLock();
		ReentrantLock bc = new ReentrantLock();
		ReentrantLock cd = new ReentrantLock();
		ReentrantLock de = new ReentrantLock();
		ReentrantLock ea = new ReentrantLock();
		
		//Philosophers
		PhilosopherThread A = new PhilosopherThread("Philosopher A", ea, ab);
		PhilosopherThread B = new PhilosopherThread("Philosopher B", ab, bc);
		PhilosopherThread C = new PhilosopherThread("Philosopher C", bc, cd);
		PhilosopherThread D = new PhilosopherThread("Philosopher D", cd, de);
		PhilosopherThread E = new PhilosopherThread("Philosopher E", de, ea);
		
		A.start();
		B.start();
		C.start();
		D.start();
		E.start();
		
	}

}
