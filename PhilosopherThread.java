package dinerPhilosopher;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherThread extends Thread{
	
	String name;
	ReentrantLock leftLock = new ReentrantLock();
	ReentrantLock rightLock = new ReentrantLock();
	Random rand = new Random();
	
	public PhilosopherThread(String name,ReentrantLock leftLock,ReentrantLock rightLock) {
		this.name = name;
		this.leftLock = leftLock;
		this.rightLock = rightLock;
	}

	public void run() {
		do {
			switch(rand.nextInt() % 2) {
			case 0:
				eat();
				break;
			case 1:
				think();
				break;
			}
			
		} while(this.isAlive());
		
	}
	
	private void eat() {
		System.out.println(name + ": attempt to acquire fork to the left.");
		if(leftLock.tryLock()) {
			System.out.println(name + ": acquired left fork.");
			
			System.out.println(name + ": attempt to acquire fork to the right.");
			if(rightLock.tryLock()) {
				System.out.println(name + ": acquired left fork.");
				
				int sleepAmount = (rand.nextInt(Integer.MAX_VALUE) % 9001) + 1000;
				System.out.println(name + ": Eating for " + sleepAmount + " milliseconds.");
				try {
					Thread.sleep(sleepAmount);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name + ": dropping fork to the left");
				leftLock.unlock();
				System.out.println(name + ": dropping fork to the right");
				rightLock.unlock();
			} else {
				System.out.println(name + ": could not acquire fork to the right. Dropping left fork");
				leftLock.unlock(); // let go of fork thread already has
			}
		} else {
			System.out.println(name + ": could not acquire fork to the left");
		}
	}
	
	private void think() {
		int sleepAmount = (rand.nextInt(Integer.MAX_VALUE) % 4001) + 1000;
		System.out.println(name + ": Thinking for " + sleepAmount + " milliseconds.");
		try {
			Thread.sleep(sleepAmount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
