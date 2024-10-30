package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Error: Please provide CSV filename");
			return;
		}

		String fileName = args[0];
		File file = new File(fileName);
		if (!file.exists()){
			System.out.println("File does not exist");
		}

		List<BikeEntry> entries = new ArrayList<>();

		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line = br.readLine();

			while((line = br.readLine()) !=null){
				String[] data = line.split(",");

			BikeEntry entry = BikeEntry.toBikeEntry(data); entries.add(entry);	
			}
		}

		List<BikeEntry> sortedEntries = new ArrayList<>();
		for (int i = 0; i<sortedEntries.size(); i++){
			for (int j = i + 1; j < sortedEntries.size(); j++){
				int totalCyclistsI = sortedEntries.get(i).getCasual() + sortedEntries.get(i).getRegistered();
				int totalCyclistsJ = sortedEntries.get(j).getCasual() + sortedEntries.get(j).getRegistered();
			if (totalCyclistsJ > totalCyclistsI){
				BikeEntry temp = sortedEntries.get(i);
				sortedEntries.set(i, sortedEntries.get(j));
				sortedEntries.set(j,temp);
			}}
		}

		List<BikeEntry> top5Entries = new ArrayList<>();
		for (int i = 0; i < 5 && i < sortedEntries.size(); i++){
			top5Entries.add(sortedEntries.get(i));
		}
		/* entries.sort(Comparator.comparingInt(e -> -(e.getCasual() + e.getRegistered())));
		List<BikeEntry> top5Entries = entries.subList(0, Math.min(5, entries.size()));  */

		String[]positions = {"highest", "second highest", "third highest", "forth highest", "fifth highest"};
		

		for (int i = 0; i < top5Entries.size(); i++){
			BikeEntry entry = top5Entries.get(i);
			int totalCyclists = entry.getCasual() + entry.getRegistered();
			String position = positions[i];
			String month = Utilities.toMonth(entry.getMonth());
			String season = Utilities.toSeason(entry.getSeason());
			String day = Utilities.toWeekday(entry.getWeekday());
			String weather = Helper.toWeather(entry.getWeather());
			String holiday = entry.isHoliday() ? "a holiday" : "not a holiday";
			
			System.out.printf("The %s recorded number of cyclist was in %s\n, on a %s in the month of %s\n. There were a total of %d cyclists. The weather was \n%s. %s was %s.", position, season, day, month, totalCyclists, weather, day, holiday);
		}
		
		
		

		

	}
}
