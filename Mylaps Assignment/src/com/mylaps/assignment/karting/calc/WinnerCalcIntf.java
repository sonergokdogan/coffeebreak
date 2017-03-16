package com.mylaps.assignment.karting.calc;

import java.util.ArrayList;
import java.util.List;

import com.mylaps.assignment.karting.model.RaceResult;

public interface WinnerCalcIntf {

	public RaceResult findWinner(List<ArrayList<Long>> raceData, int numberOfLaps);
}
