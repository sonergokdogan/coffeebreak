package com.mylaps.assignment.karting.model;

public class RaceResult {

	private Winner winner; //Check the class definition below

	//Fastest race completion time (in milliseconds)
	private long fastestRaceTime;
	
	//Fastest kart which completes the race
	private int fastestKartOfRace;

	public RaceResult() {
		this.winner = new Winner();
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}

	public long getMinimumRaceTime() {
		return fastestRaceTime;
	}

	public void setMinimumRaceTime(long minimumRaceTime) {
		this.fastestRaceTime = minimumRaceTime;
	}

	public int getFastestKartInTheRace() {
		return fastestKartOfRace;
	}

	public void setFastestKartInTheRace(int fastestKartInTheRace) {
		this.fastestKartOfRace = fastestKartInTheRace;
	}

	@Override
	public String toString() {
		return "RaceResult [Winner=" + winner + ", fastestRaceTime=" + fastestRaceTime + ", fastestKartInTheRace="
				+ fastestKartOfRace + "]";
	}

	public class Winner {

		//The winning kart number (may change regarding the calculation type)
		private int kartNumber = 1;
		
		//Fastest lap of the winning kart
		private int fastestLap;
		
		//Race completion time of the winning kart (not applicable for fastest-lap-winner calculation)
		private long raceTime;
		
		//Fastest lap time of the winner kart
		private long fastestLapTime = Long.MAX_VALUE;

		public int getKartNumber() {
			return kartNumber;
		}

		public void setKartNumber(int kartNumber) {
			this.kartNumber = kartNumber;
		}

		public int getFastestLap() {
			return fastestLap;
		}

		public void setFastestLap(int fastestLap) {
			this.fastestLap = fastestLap;
		}

		public long getRaceTime() {
			return raceTime;
		}

		public void setRaceTime(long raceTime) {
			this.raceTime = raceTime;
		}

		public long getFastestLapTime() {
			return fastestLapTime;
		}

		public void setFastestLapTime(long fastestLapTime) {
			this.fastestLapTime = fastestLapTime;
		}

		@Override
		public String toString() {
			return "Winner [kartNumber=" + kartNumber + ", fastestLap=" + fastestLap + ", fastestLapTime="
					+ fastestLapTime + ", raceTime=" + raceTime + "]";
		}

	}

}
