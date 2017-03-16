package com.mylaps.assignment.karting.control;

import java.util.ArrayList;
import java.util.List;

import com.mylaps.assignment.karting.calc.WinnerCalcIntf;
import com.mylaps.assignment.karting.model.RaceResult;

/*
 * This Singleton class is for controlling the race. It starts/stops 
 * the race and registers new lap times for the karts.
 * 
 * 
 */
public class Race {
	private int numberOfLaps;

	private final static Race INSTANCE = new Race();

	private Race() {
		// Exists only to defeat instantiation.
	}

	public static Race getInstance() {
		return INSTANCE;
	}

	//Let's keep race data in the memory (not possibly in the L2 cache) to be safe with multiple threads
	volatile List<ArrayList<Long>> raceData = new ArrayList<>();
	
	//Calculator interface
	WinnerCalcIntf calculator;

	/*
	 * This method clears the registers to get them ready for a new race
	 */
	public void startRace(int numberOfKarts, int numberOfLaps, int lapLength, WinnerCalcIntf winnerCalcType) {

		calculator = winnerCalcType;

		//Clear the lists
		raceData.clear();
		
		// We increment this by 1 since the first pass from the mat would be for
		// start of the race for the kart. The rest of the assignment is using this
		// assumption
		this.numberOfLaps = numberOfLaps + 1;

		//Instantiate the lists in the list
		for (int i = 0; i < numberOfKarts; i++)
			raceData.add(new ArrayList<>());
	}

	/*
	 * This is the method which will be triggered by the timing system.
	 */
	public synchronized void newLapRecord(int kartNumber, long timeOfDay) {

		//Save the lap data
		raceData.get(kartNumber).add(timeOfDay);
	}

	public RaceResult finishRace() {

		//Ok, let's see who gets the cup!
		return calculator.findWinner(this.raceData, numberOfLaps);

	}
}
