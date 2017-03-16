package com.mylaps.assignment.karting.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mylaps.assignment.karting.calc.FastestLapWinnerCalc;
import com.mylaps.assignment.karting.calc.WinnerCalcIntf;
import com.mylaps.assignment.karting.sims.Kart;

/*
 * This class is for feeding the winning calculation algorithm
 * with some simulated data.
 */
public class StartLine {

	//Some assumptions
	private static final int MAX_NUMBER_OF_KARTS = 5;
	private static final int MAX_NUMBER_OF_LAPS = 10;

	//Here goes the karts
	private static ArrayList<Kart> karts = new ArrayList<Kart>();

	public static void main(String[] args) throws IOException {

		//Console reader
		final Scanner in = new Scanner(System.in, "UTF-8");

		int numberOfKarts = 0;

		//Receive number of karts
		while (numberOfKarts < 1 || numberOfKarts > MAX_NUMBER_OF_KARTS) {
			System.out.println("Welcome to Mylaps kart race simulation..\n" + "Please enter the number of cars (up to "
					+ MAX_NUMBER_OF_KARTS + "): ");
			numberOfKarts = in.nextInt();
		}

		int numberOfLaps = 0;

		//Receive number of laps to go
		while (numberOfLaps < 1 || numberOfLaps > MAX_NUMBER_OF_LAPS) {
			System.out.println("Please enter the number of laps (up to " + MAX_NUMBER_OF_LAPS + "): ");
			numberOfLaps = in.nextInt();
		}

		int lapLength = 0; // in meters

		//Receive length of a lap. Longer laps cause longer runtime!!
		System.out.println("Please enter the length of the track (meters): ");
		lapLength = in.nextInt();

		//Wait for a kbhit
		System.out.println("Press any key to start the race..");
		System.in.read();
		in.close();
		
		//Let the game starts
		startRace(numberOfKarts, numberOfLaps, lapLength);
	}

	private static void startRace(int numberOfKarts, int numberOfLaps, int lapLength) {

		//We choose fastest lap winning calculation as out winner calculation method.
		WinnerCalcIntf raceType = new FastestLapWinnerCalc();
		
		//Let's initiate the race with the parameters
		Race.getInstance().startRace(numberOfKarts, numberOfLaps, lapLength, raceType);

		//Create the karts and let them go
		for (int i = 0; i < numberOfKarts; i++) {
			Kart kart = new Kart(i, numberOfLaps, lapLength);
			karts.add(kart);

			kart.start();
		}

		//Join the threads, so we can wait at the start line (here!) until the last thread finishes
		for (Kart kart : karts) {
			try {
				kart.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Race is finished");

		//Let's see who's the winner!
		System.out.println(Race.getInstance().finishRace());

	}

}
