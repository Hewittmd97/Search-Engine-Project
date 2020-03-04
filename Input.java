package allTogether;
import javax.swing.*;

public class Input {

	private static String initialPrompt()
	{
		String input = JOptionPane.showInputDialog("Please enter a search string. Enter \"S\" to stop: ");
		return input;
	}
	
	// return false when user enters end of session token
	
	public static String readInput(int group)
	{
		String noise = "a,able,about,across,after,all,almost,also" +
				",am,among,an,and,any,are,as,at,be,because,been,but" +
				",by,can,cannot,could,dear,did,do,does,either,else" +
				",ever,every,for,from,get,got,had,has,have,he,her,hers" +
				",him,his,how,however,i,if,in,into,is,it,its,just,least" +
				",let,like,likely,may,me,might,most,must,my,neither,no" +
				",nor,not,of,off,often,on,only,or,other,our,own,rather" +
				",said,say,says,she,should,since,so,some,than,that,the" +
				",their,them,then,there,these,they,this,tis,to,too,twas" +
				",us,wants,was,we,were,what,when,where,which,while,who" +
				",whom,why,will,with,would,yet,you,your";
		String line;
		while(1 == 1)
		{
			line = initialPrompt();
			if((line.length() == 2 && line.contains(" ")))
			{
				if(line.charAt(0) == ' ')
				{
					line = line.substring(line.indexOf(" ") + 1);
				}
				else if(line.charAt(1) == ' ')
				{
					line = Character.toString(line.charAt(0));
				}
			}
			if (line == null || (line != null && ("".equals(line))) || line.equalsIgnoreCase("S"))
			{
				return "s";
			}
			else
			{
				if((!(line.contains(" ")) && noise.contains(line.charAt(0) + ","))
						|| (!(line.contains(" ")) && noise.contains(line + ","))
						|| (line.length() == 1 && noise.contains(line + ","))
						|| (line.length() == 2 && noise.contains((line))))
				{
					continue;
				}
				else if(line.equalsIgnoreCase(" "))
				{
					continue;
				}
				return line;
			}
		}
	}
}
