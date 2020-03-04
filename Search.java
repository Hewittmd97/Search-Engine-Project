package allTogether;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {
	
	public static List<String[]> search(Database db, String searchFor, List<String[]> list, boolean firstCall) throws SQLException
	{
		List<String[]> ll = new ArrayList<String[]>();
		String subString;
		String[] intoLL = new String[4];
		ResultSet rs = null;
		try
		{
			//list will NOT be null if search has been called in a recursive manor. 
			//this can be checked by calling .get and retrieving the first object in the list. 
			list.get(0);
			//If "&&" is still in the searchFor String, then search() must be called recursively again
			//which occurs in the catch portion. 
			if(searchFor.contains("&&"))
			{
				//to exit this try portion.
				throw new ArithmeticException("Random Error");
			}
			//while searchFor still contains more than 1 word in the string. 
			while(searchFor.contains(" "))
			{
				//gets first word from searchFor
				String word = searchFor.substring(0, searchFor.indexOf(' '));
				//remove the first word from searchFor
				searchFor = searchFor.substring(searchFor.indexOf(' ') + 1);
				//because search() has been called recursively, a "&&" was present at one point. 
				//and because a list was passed to the function this time, the first set of search results has 
				//been acquired, so now the subset acquired from database from first search must be searched to find 
				//the subset of the subset. 
				//loop through every object in the list. 
				for(int i = 0; i < list.size(); i++)
				{
					//create a string array object to hold the object being fetched from list.
					String[] temp = list.get(i);
					//if the word after "&&" is also found in the descriptor from the first search subset
					if(temp[3].contains(word))
					{
						//add the data to the list that will be returned. 
						ll.add(temp);
					}
				}
			}
			//this checks to see if the word after "&&" is contained in subset from first search query. 
			for(int i = 0; i < list.size(); i++)
			{
				String[] temp = list.get(i);
				if(temp[3].contains(searchFor))
				{
					ll.add(temp);
				}
			}
			//this branch will never be triggered on first call, so do not change next line.
			return ll;
		}
		//will catch a null pointer if list is null.
		catch(Exception e)
		{
			//this will check to see if searchFor contains "&&"
			if(searchFor.contains("&&")) 
			{
				String string1 = searchFor.substring(0, searchFor.indexOf("&&") - 1);
				
				String string2 = searchFor.substring(searchFor.indexOf("&&") + 3);
				System.out.println(string1 + " : " + string2);
				List<String[]> list1 = Search.search(db, string1, null, false);
				List<String[]> list2 = Search.search(db, string2, list1, false);
				if(firstCall == true)
				{
					String[] lastly = new String[1];
					lastly[0] = "";
					for(int i = 0; i < list2.size(); i++)
					{
						String[] temp = list2.get(i);
						lastly[0] += (temp[0] + " : " + temp[1] + " : " + temp[2] + " : " + temp[3] + "\n");
					}
					List<String[]> finalist = new ArrayList<String[]>();
					finalist.add(lastly);
					return finalist;
				}
				else 
				{
					return list2;
				}
			}
			else 
			{
				while(searchFor.contains(" "))
				{
					subString = searchFor.substring(0, searchFor.indexOf(" "));
					searchFor = searchFor.substring(searchFor.indexOf(" ") + 1);
					db.search(subString);
					rs = db.getRS();
					rs.first();
					intoLL[0] = rs.getString("index");
					intoLL[1] = rs.getString("url");
					intoLL[2] = rs.getString("title");
					intoLL[3] = rs.getString("description");
					ll.add(intoLL);
					intoLL = new String[4];
					while(rs.next())
					{
						boolean alreadyThere = false;
						intoLL[0] = rs.getString("index");
						intoLL[1] = rs.getString("url");
						intoLL[2] = rs.getString("title");
						intoLL[3] = rs.getString("description");
						
						for(int i = 0; i < intoLL.length; i++)
						{
							String[] obj = ll.get(i);
							if(intoLL[1].contains(obj[1]))
							{
								alreadyThere = true;
								break;
							}
						}
						if(alreadyThere == true)
						{
							intoLL = new String[4];
							continue;
						}
						ll.add(intoLL);
						intoLL = new String[4];
					}
				}
				subString = searchFor;
				db.search(subString);
				rs = db.getRS();
				rs.first();
				intoLL[0] = rs.getString("index");
				intoLL[1] = rs.getString("url");
				intoLL[2] = rs.getString("title");
				intoLL[3] = rs.getString("description");
				ll.add(intoLL);
				intoLL = new String[4];
				while(rs.next())
				{
					boolean alreadyThere = false;
					intoLL[0] = rs.getString("index");
					intoLL[1] = rs.getString("url");
					intoLL[2] = rs.getString("title");
					intoLL[3] = rs.getString("description");
					for(int i = 0; i < ll.size(); i++)
					{
						String[] obj = ll.get(i);
						if(intoLL[1].contains(obj[1]))
						{
							alreadyThere = true;
							break;
						}
					}
					if(alreadyThere == true)
					{
						intoLL = new String[4];
						continue;
					}
					ll.add(intoLL);
					intoLL = new String[4];
				}
			}
		}
		if(firstCall == true)
		{
			String[] lastly = new String[1];
			lastly[0] = "";
			for(int i = 0; i < ll.size(); i++)
			{
				String[] temp = ll.get(i);
				lastly[0] += (temp[0] + " : " + temp[1] + " : " + temp[2] + " : " + temp[3] + "\n\n");
			}
			List<String[]> finalist = new ArrayList<String[]>();
			finalist.add(lastly);
			return finalist;
		}
		else
		{
			return ll;
		}
	}
}
