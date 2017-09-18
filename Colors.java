/**
 * A list of colors to pick out a favorite from
 */

import java.util.LinkedList;
import java.util.Random;

public class Colors 
{
	static LinkedList<String> colors = new LinkedList<String>();
	
	public static String getRandColor() 
	{
		Random r = new Random();
		int rand = r.nextInt(colors.size());
		return colors.get(rand);
	}

	static void addColors() 
	{
		colors.add("#0000ff");
		colors.add("azure");
		colors.add("cerulean");
		colors.add("carmine");
		colors.add("hooloovoo");
	}
}
