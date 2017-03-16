package com.mylaps.assignment.karting.sims;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.mylaps.assignment.karting.control.Race;

/*
 * This class simulates the car and partly the timing system (since it pushes
 * lap data to the Race singleton instance).
 * 
 * Here we derived the class from Thread, so we can move the cars at the same
 * time. This is not mandatory since the calculation will be handled on a
 * unique collection. But it saves time for us to run them all.
 * 
 */
public class Kart extends Thread {

	private final double BASE_SPEED = 40.0; // in m/s

	// We have a base speed and at the beginning of every lap, we randomly
	// put some more speed to each of them. This varies the results.
	private double speed; // in m/s

	private int kartNumber;

	// Which lap I'm on?
	private int currentLap;

	// These two variables are to understand whether the lap is finished or not
	private int lapLength; // in meters
	private double currentPosition; // in meters

	// Shall I finish or go?
	private int numberOfLapsToGo;

	// Ctor
	public Kart(int kartNumber, int numberOfLaps, int lapLength) {
		this.kartNumber = kartNumber;
		this.lapLength = lapLength;
		this.numberOfLapsToGo = numberOfLaps;
	}

	//A pass over the mat!
	private void newLap() {

		//Save the time of the event into the Race singleton instance
		Race.getInstance().newLapRecord(this.kartNumber, Calendar.getInstance().getTimeInMillis());

		if (currentLap > 0) {
			System.out.println("Kart #" + this.kartNumber + " has finished the lap #" + this.currentLap);
			
			//One less to go!
			this.numberOfLapsToGo--;
		}

		//Reset current position
		this.currentPosition = 0;
		
		//One less to go!
		this.currentLap++;

		//Put a little salt to the gasoline. This makes me faster or slower than the others
		this.setSpeed(Math.random() * 5 + BASE_SPEED);

	}

	//Ignite the thread
	public void run() {

		System.out.println("Running " + kartNumber);

		//Let's pass over the mat for the first time
		newLap();
	
		//Loop until I finish all the laps
		while (numberOfLapsToGo > 0) {

			/*
			 * We should measure the elapsed time since it may not be accurate enough
			 * to use the milliseconds we slept as our final period
			 */
			long startTime = System.nanoTime();

			try {
				// Let the others breath.
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("Kart number " + kartNumber + " is interrupted. Going out of track..");
			}

			//Let's see how much time we gave the cpu to the others
			long estimatedTime = System.nanoTime() - startTime;

			//Nano-to-milliseconds
			long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
			
			//This gives us the delta x
			double delta = speed * elapsedTimeInMillis / 1000;
			
			//Let's extrapollate the car
			currentPosition += delta;

			//If I'm further than the lap length, it means I finished the lap.
			//Let's move on to the next lap.
			//Note: Here there may be some inaccuracy due to the resolution of the 
			//loop. I neglected this possible extra distance.
			if (currentPosition >= lapLength) {
				newLap();
			}
		}
	}

	public int getKartNumber() {
		return kartNumber;
	}

	public void setKartNumber(int kartNumber) {
		this.kartNumber = kartNumber;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
