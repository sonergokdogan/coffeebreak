package com.mylaps.assignment.carracing.control;

import java.util.HashMap;
import java.util.Map;

public class TimingSystem {

	private static TimingSystem INSTANCE = new TimingSystem();
	private Map<Integer, Map<Integer, Long>> totalLapTimes = new HashMap<Integer, Map<Integer, Long>>();

	private TimingSystem() {

	}

	public static TimingSystem getInstance() {
		return INSTANCE;
	}

	public boolean pollTheMat() {

		return false;
	}

	public synchronized void registerLapTime(int kartNumber, int lapNumber, long lapTime) {

		System.out.println("Registering time for kart #" + kartNumber + " :" + " for lap #:" + lapNumber + " time:" + lapTime);
		if (totalLapTimes.get(lapNumber) == null)
			totalLapTimes.put(lapNumber, new HashMap());
		totalLapTimes.get(lapNumber).put(kartNumber, lapTime);
		System.out.println("Size of totalLapTimes[" + lapNumber + "]: " + totalLapTimes.get(lapNumber).size() );
	}

	public int getNumberOfCompletedLaps() {
		
		return this.totalLapTimes.size();
	}
	
	public int getNumberOfCars() {
		
		int numberOfCars = 0;
		for (int i = 0; i < totalLapTimes.size(); i++) {

			//Get the number of laps run by the kart in the map index
			int numberOfCarsForCurrentLap = totalLapTimes.get(i).size();
			
			if (numberOfCarsForCurrentLap > numberOfCars)
				numberOfCars = numberOfCarsForCurrentLap;
		}
		
		return numberOfCars;
		
	}

	public Map<Integer, Long> getRegisteredResultsForLap(int lapNumber) {
		
		return this.totalLapTimes.get(lapNumber);
	}
}
