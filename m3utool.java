import java.io.*;
import java.util.*;

public class m3utool {


	private static int i = 0;
	private static BufferedReader inputFile = null;
	private static BufferedWriter outputFile = null;
	private static String line = "";
	private static String newLine = "";
	private static String editedLine = "";
	private static String filterString = "";
	private static Character c;

	public static void main (String[] args)
	{
		if (args.length == 0) System.out.println("Add meg a file-t!");
		else if (args.length == 1) fileReader(args[0]);
		else if (args.length == 2)
		{
			filterString = args[1];
			fileReader(args[0]);
		}
		else System.out.println("Sok paraméter!");
    	}


	private static void fileReader(String name)
	{
		try
		{
			inputFile = new BufferedReader(new FileReader(new File(name)));
		} catch (FileNotFoundException e) { System.out.println("Nincs meg a file!"); }

		try
		{
			outputFile = new BufferedWriter(new FileWriter(new File(name+".txt")));
		} catch (IOException ie) { System.out.println("File létrehozása nem sikerült!"); }

		while (line != null)
		{
			try
			{
				line = inputFile.readLine();
				if ( (line != null) && (!line.startsWith("#")) )
				{
					editedLine = lineCutter(line);
					outputFile.write(editedLine,0,editedLine.length());
					outputFile.newLine();
				}
			} catch (IOException ioe) { System.out.println("Hiba a file írása közben!"); }
		}

		try
		{
			inputFile.close();
			outputFile.close();
		} catch (IOException ioe) { System.out.println("Hiba a file-ok bezárásánál!"); }
	}


	private static String lineCutter (String l)
	{
		newLine = "";
		for (int j=0; j < l.length(); j++)
		{
			if ( (j+3) < l.length() )
			{
				if ( ! filterString.equals(l.substring(j,j+4)) )
				{
					c = new Character( l.charAt(j) );
					newLine += c.toString();
				}
				else j += 3;
			}
			else
			{
				c = new Character( l.charAt(j) );
				newLine += c.toString();
			}
		}
		return newLine;
	}

}