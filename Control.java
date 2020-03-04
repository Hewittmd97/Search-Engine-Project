package allTogether;

public class Control {

	public static final int MAX_LINES = 1024;
	public static final int MAX_WORDS = 128;
	//private static int group = 0;

	public static void assemble() 
	{
		//String[][] storage;
		//String[][][] shifted;
		//String[][][] sorted;
		//String line = Input.readInput(group);
		//while (!(line.equalsIgnoreCase("s")) && !(line.equals(null)))
		//{
			//storage = LineStorage.storeData(line, group);
			//shifted = CircularShift.shift(storage);
			//sorted = Alphabetize.sort(shifted, group);
			//sorted = Alphabetize.removeNoise(sorted, group);
			//Output.display(sorted, group);
			//group++;
			//line = Input.readInput(group);
		//}
		Output.displayAll();
		try
		{
			Output.clearFile();
		}
		catch(Exception e)
		{
			System.out.println("Error clearing file.");
		}
	}
}
