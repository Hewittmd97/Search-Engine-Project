package allTogether;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Output {

	public static void display(String[][][] alpha, int group)
	{
		String lineByLine = "";
		for(int i = 0; i < alpha[group].length; i++)
		{
			lineByLine = "";
			for(int j = 0; j < alpha[group][0].length; j++)
			{
				lineByLine += " ";
			}
			try
			{
				writeToFile(lineByLine);
			}
			catch(Exception e)
			{
				System.out.println("There was somehow an error writing to file");
			}
		}
	}

	private static void writeToFile(String line) throws IOException
	{
		File file = new File("all.txt");
		if(file.length() != 0)
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.append('\n');
			writer.write(line);
			writer.close();
		}
		else
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(line);
			writer.close();
		}
	}

	public static void displayAll()
	{
		try
		{
			String[] temp = readIn();
			Alphabetize.sortAll(temp);
			String output = "";
			for(int i = 0; i < temp.length; i++)
			{
				output += temp[i] + "\n";
			}
			output = output.substring(0, output.length() - 2);
			//JOptionPane.showMessageDialog(null, output);
		}
		catch(Exception e)
		{
			System.out.println("There was somehow an error reading from file");
		}
	}

	private static String[] readIn() throws IOException
	{
		long lineCount;
		try (Stream<String> linesStream = Files.lines(new File("all.txt").toPath()))
		{
			lineCount = linesStream.count();
		}
		BufferedReader reader = new BufferedReader(new FileReader(new File("all.txt")));
		int lC = (int) lineCount;
		String[] all = new String[lC];
		for(int i = 0; i < lC; i++)
		{
			all[i] = reader.readLine();
		}
		reader.close();
		return all;
	}

	public static void clearFile() throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(new File("all.txt"));
		writer.print("");
		writer.close();
	}
}
