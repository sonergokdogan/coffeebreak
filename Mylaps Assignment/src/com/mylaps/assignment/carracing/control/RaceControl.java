package com.mylaps.assignment.carracing.control;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import com.mylaps.assignment.carracing.model.Car;

public class RaceControl {

	private static final int MAX_NUMBER_OF_CARS = 5;
	private static final int MAX_NUMBER_OF_LAPS = 10;
	
	private static Vector<Car> cars = new Vector<Car>();
	
	public static void main(String[] args) throws IOException {

		

		/*
		final Scanner in = new Scanner(System.in, "UTF-8");

		int numberOfCars = 0;
		
		while (numberOfCars < 1 || numberOfCars > MAX_NUMBER_OF_CARS) {
			System.out.println("Welcome to Mylaps car race simulation..\n"
					+ "Please enter the number of cars (up to " + MAX_NUMBER_OF_CARS + "): ");
			numberOfCars = in.nextInt();
		}

		int lapLength = 0; // in meters
		System.out.println("Please enter the length of the track (meters): ");
		lapLength = in.nextInt();

		int numberOfLaps = 0;

		while (numberOfLaps < 1 || numberOfLaps > MAX_NUMBER_OF_LAPS) {
			System.out.println("Please enter the number of laps (up to " + MAX_NUMBER_OF_LAPS + "): ");
			numberOfLaps = in.nextInt();
		}
		
		
		System.out.println("Press any key to start the race. Press 'q' key to interrupt the race before finish");
		
		System.in.read();
		
		
		
		
		in.close();
		*/
		
		startNewRace(2, 2, 500);
	}
	
	private static void startNewRace(int numberOfCars, int numberOfLaps, int lapLength) {
		
		for (int i = 0; i < numberOfCars; i++) {
			Car kart = new Car(i, numberOfLaps, lapLength);
			cars.add(kart);
		}

		for (Car kart : cars) {
			kart.start();
		}
	}
	
}
