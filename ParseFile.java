package allTogether;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParseFile {

	public static String[] readIn() throws IOException
	{
		long lineCount = 0;
		try (Stream<String> linesStream = Files.lines(Paths.get("descriptors.txt"), Charset.forName("Cp1252")))
		{
			lineCount = linesStream.count();
		} catch(Exception e)
		{
			//do nothing
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new FileReader(new File("descriptors.txt")));
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < lineCount; i++)
		{
			String temp = reader.readLine();
			if((i + 1) % 3 == 0)
			{
				if(temp.contains("'") || temp.contains(",") || temp.contains("-") || temp.contains("&") || temp.contains("*") 
						|| temp.contains(":") || temp.contains(";") || temp.contains("\\") || temp.contains("\"") || temp.contains(".")
						|| temp.contains("?"))
				{
					temp = temp.replaceAll("[^a-zA-Z0-9_]", " ");
				}
				if(temp.contains("   "))
				{
					temp = temp.replaceAll("[ ][ ][ ]", " ");
				}
				if(temp.contains("  "))
				{
					temp = temp.replaceAll("[ ][ ]", " ");
				}
				if(temp.contains(" s "))
				{
					temp = temp.replaceAll("[ ][s][ ]", "s ");
				}
				if(temp.contains(" t "))
				{
					temp = temp.replaceAll("[ ][t][ ]", "t ");
				}
				temp = temp.replaceAll("(?<=\\d) +(?=\\d)", "");
			}
			else
			{
				if(temp.contains("'"))
				{
					temp = temp.replaceAll("'", "");
				}
			}
			list.add(temp);
		}
		String[] all = list.toArray(new String[0]);
		reader.close();
		return all;
	}
}