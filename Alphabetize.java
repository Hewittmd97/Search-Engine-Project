package allTogether;

import java.util.Arrays; //used to sort arrays using Arrays.sort
import java.util.ArrayList;
import java.util.List;

public class Alphabetize {

	// returns the Alphabetized Circular Shift of the specified lineNum 
	// each line has multiple groupings specified by group

	// returns null if groupNum is too large for lineNum.

	// returns null if lineNum is too large
	// and Circular Shift does not have another line

	//will sort lines of circularly shifted items alphabetically. Will accept 3D array of arr[group][line][word]
	public static String[][][] sort(String[][][] circleLines, int group)
	{
		//used to store sorted lines, arr[group][line][word]
		String[][][] sorted = new String[Control.MAX_LINES][][];
		int numberLines;
		// creates numberLines, and calls on circleLines to get the total number of lines created in the group.
		//the number of lines will also be equal to the number of words in each line
		numberLines = circleLines[group][0].length;
		//allocates a square 2D array to sorted[group]
		sorted[group] = new String[numberLines][numberLines];
		//creates a 1D array to hold first word from every circleLines[group][]
		String[] firstWord = new String[numberLines];
		//for every element in the second dimension.
		for(int i = 0; i < numberLines; i++)
		{
			String temp = "";
			//used to add every word in a line to firstWord
			for(int j = 0; j < numberLines; j++)
			{
				temp += circleLines[group][i][j] + " ";
			}
			temp = temp.substring(0, temp.length() - 1);
			firstWord[i] = temp;
		}
		//sorts firstWord alphabetically
		Arrays.sort(firstWord);
		//call and assignment of returned 2D array
		sorted[group] = LineStorage.convertTo2D(firstWord);
		return sorted;
	}

	public static String[] sortAll(String[] allLines)
	{
		String temp = "";
		for(int i = 0; i < allLines.length; i++)
		{
			temp = allLines[i];
			allLines[i] = temp;
		}
		Arrays.sort(allLines);
		return allLines;
	}

	public static String[][][] removeNoise(String[][][] noisey, int group)
	{
		List<String> list = new ArrayList<String>();
		String[] lines = new String[noisey[group].length];
		String word;
		for(int i = 0; i < noisey[group][0].length; i++)
		{
			String temp = "";
			for (int j = 0; j < noisey[group][0].length; j++) {
				temp += noisey[group][i][j] + " ";
			}
			temp = temp.substring(0, temp.length() - 1);
			lines[i] = temp;
		}
		for(int i = 0; i < lines.length; i++)
		{
			if(lines[i].contains(" "))
			{
				word = lines[i].substring(0, lines[i].indexOf(" "));
				if(!(word.equalsIgnoreCase("a") || word.equals("the") || word.equals("of")))
				{
					list.add(lines[i]);
				}
			}
			else
			{
				word = lines[i];
				if(!(word.equalsIgnoreCase("a") || word.equals("the") || word.equals("of")))
				{
					list.add(lines[i]);
				}
			}
		}
		String[] reduction = list.toArray(new String[list.size()]);
		noisey[group] = LineStorage.convertTo2D(reduction);
		return noisey;
	}
}
