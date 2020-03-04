package allTogether;

public class LineStorage {
	private static String[][] storage = new String[Control.MAX_LINES][];

	public static String[][] storeData (String newLine, int group) {
		String[][] storage = new String[Control.MAX_LINES][];
		String temp = newLine;
		int numWords = 0;
		int n = 0;
		while (temp.contains(" ") && !temp.equals("")) {
			numWords++;
			temp = temp.substring(temp.indexOf(" ") + 1);
		}
		numWords++;
		storage[group] = new String[numWords];
		if (newLine.contains(" "))
		{
			while (newLine.contains(" ")) {
				storage[group][n] = newLine.substring(0, newLine.indexOf(" "));
				newLine = newLine.substring(newLine.indexOf(" ") + 1);
				n++;
			}
			storage[group][numWords - 1] = newLine;
		}
		else
		{
			storage[group][0] = newLine;
		}
		return storage;
	}
	
	public static String[] storeDataNoGroup (String newLine) 
	{
		String[] storage;
		String temp = newLine;
		int numWords = 0;
		int n = 0;
		while (temp.contains(" ") && !temp.equals("")) 
		{
			numWords++;
			temp = temp.substring(temp.indexOf(" ") + 1);
		}
		numWords++;
		storage = new String[numWords];
		if (newLine.contains(" "))
		{
			while (newLine.contains(" ")) 
			{
				storage[n] = newLine.substring(0, newLine.indexOf(" "));
				newLine = newLine.substring(newLine.indexOf(" ") + 1);
				n++;
			}
			storage[numWords - 1] = newLine;
		}
		else
		{
			storage[0] = newLine;
		}
		return storage;
	}

	public static String[][] convertTo2D(String[] sorted)
	{
		int wordCount = 0;
		String temp = sorted[0];
		while(temp != "")
		{
			if(temp.contains(" "))
			{
				wordCount++;
				temp = temp.substring(temp.indexOf(" ") + 1);
			}
			else
			{
				wordCount++;
				temp = "";
			}
		}
		storage = new String[sorted.length][wordCount];
		for(int i = 0; i < sorted.length; i++)
		{
			for(int j = 0; j < wordCount; j++)
			{
				if(sorted[i].contains(" "))
				{
					storage[i][j] = sorted[i].substring(0, sorted[i].indexOf(" "));
					sorted[i] = sorted[i].substring(sorted[i].indexOf(" ") + 1);
				}
				else
				{
					storage[i][j] = sorted[i];
				}
			}
		}
		return storage;
	}
}
