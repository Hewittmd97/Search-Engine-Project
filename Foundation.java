package allTogether;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Foundation {
	
	public static Database process1() throws IOException
	{
		Database db = new Database();
		String[] input = null;
		List<String[]> list = new ArrayList<String[]>();
		
		try 
		{
			input = ParseFile.readIn();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < input.length; i++)
		{
			if((i + 1) % 3 == 0)
			{
				list.add(LineStorage.storeDataNoGroup(input[i]));
			}
		}
		String[][] inArrForm;
		inArrForm = new String[list.size()][];
		for(int i = 0; i < list.size(); i++)
		{
			inArrForm[i] = list.get(i);
		}
		int group = 0;
		String restOfString = "";
		for(int i = 0; i < inArrForm.length; i++)
		{
			String[][] temp;
			temp = CircularShift.shift(inArrForm[i]);
			for(int j = 0; j < temp.length; j++)
			{
				restOfString = "";
				for(int m = 1; m < temp[j].length; m++)
				{
					restOfString += temp[j][m] + " ";
				}
				if(db.hasNoiseWord(temp[j][0]) || temp[j][0].contentEquals(""))
				{
					continue;
				}
				//temp[j][0] = temp[j][0].toLowerCase();
				//restOfString = restOfString.toLowerCase();
				//String wabba = input[group + 2].toLowerCase();
				db.add(temp[j][0], restOfString, input[group], input[group + 1], input[group + 2]);
			}
			group += 3;
		}
		return db;
	}
	
	@SuppressWarnings("static-access")
	public static void process2(Database db, Search sch)
	{
		Scanner in = new Scanner(System.in);
		//System.out.println("Enter a term to search for: ");
		String searchTerm = "";
		//= in.nextLine();
		List<String[]> returned = new ArrayList<String[]>();
		try 
		{
			if(searchTerm.contains("\n"))
			{
				searchTerm = searchTerm.substring(0, searchTerm.length() - 1);
			}
			returned = sch.search(db, searchTerm, null, true);
			for(int i = 0; i < returned.size(); i++)
			{
				String[] temp = returned.get(i);
				System.out.println(i + " : " + temp[0] + " : " + temp[1] + " : " + temp[2] + " : " + temp[3] + "\n");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		in.close();
	}
}