package com.mylaps.assignment.carracing.model;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.mylaps.assignment.carracing.control.TimingSystem;

public class Car implements Runnable {

	private final double BASE_SPEED = 40.0; // in m/s

	private double speed; // in m/s
	private int kartNumber;
	private int currentLap;
	private int lapLength; // in meters
	private double currentPosition; // in meters
	private int numberOfLapsToGo;
	private long lapTime; // in milliseconds
	private Vector<Long> lapTimes;

	private Thread t;

	public Car(int kartNumber, int numberOfLaps, int lapLength) {
		this.kartNumber = kartNumber;
		this.lapLength = lapLength;
		this.numberOfLapsToGo = numberOfLaps;
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

	private void newLap() {
		lapTime = 0;
		currentPosition = 0;
		currentLap++;
		numberOfLapsToGo--;

		setSpeed(Math.random() * 5 + BASE_SPEED);

		System.out.println("Kart #" + this.kartNumber + " has finished the lap #" + currentLap);
	}

	public void run() {

		newLap();
		System.out.println("Running " + kartNumber);

		while (numberOfLapsToGo > 0) {

			long startTime = System.nanoTime();

			try {
				// Let the others breath.
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("Car number " + kartNumber + " is interrupted. Going out of track..");
			}

			long estimatedTime = System.nanoTime() - startTime;

			long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
			lapTime += elapsedTimeInMillis;
			double delta = speed * elapsedTimeInMillis / 1000;
			currentPosition += delta;

			System.out.println(
					"Kart #" + kartNumber + " current position:" + currentPosition + " " + estimatedTime + " " + delta);
			if (currentPosition >= lapLength) {
				newLap();
			}
		}
		System.out.println("Thread " + kartNumber + " is exiting.");
	}

	public void start() {
		System.out.println("Starting " + kartNumber);
		if (t == null) {
			t = new Thread(this, "Thread #" + kartNumber);
			t.start();
		}
	}

}
