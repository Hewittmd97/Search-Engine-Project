package allTogether;


// returns the Circular Shift of the specified lineNum 
// each line has multiple groupings specified by group
// returns null if groupNum is too large for lineNum
// returns null if lineNum is too large
// and line storage does not have another line

public class CircularShift {

	public static String[][] shift(String[] storage)
	{
        String[][] word = null;
	    if(storage.length > 1) 
	    {
	    	word = new String[storage.length][storage.length];
	    	String temp = "";
            	String temp2 = "";
            	for (int i = 0; i < storage.length; i++) 
            	{
                	temp = storage[0];
               		for (int j = 0; j < storage.length; j++) 
                	{
                    		temp2 = storage[(j + 1) % storage.length];
                    		storage[(j + 1) % storage.length] = temp;
                    		temp = temp2;
                	}
                	for (int j = 0; j < storage.length; j++) 
                	{
                    		word[i][j] = storage[j];
                	}
            	}
        }
	else
        {
            word = new String[1][1];
            word[0][0] = storage[0];
        }
		return word;
	}
}
