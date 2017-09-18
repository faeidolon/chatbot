/** @author Faelan S.
 * 
 * This is supposed to be a chatbot but it's not very intelligent and it can't
 * learn anything past what I code in it I'm sorry my child
 * 
 * IMPORTANT: for now, stick to 1 task/piece of info per line (greetings and
 * compliments are an exception), otherwise it breaks (sorry kiddo)
 */

import java.util.Scanner;

public class ControlPanel 
{
	static PersonalInfo myCB;
	//TODO: sorting things? maybe a multi-step process?
	//TODO: turn into more a personal assistant that helps w/stuff you need

	public static void main(String[] args) 
	{
		myCB = new PersonalInfo();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Colors.addColors();
		
		while(true)
		{
			String in = sc.nextLine().toLowerCase();

			String out = getOutput(in);
			System.out.println(out);
		}
	}

	private static String getOutput(String in) 
	{
		String output = ""; 
		if(includesSwears(in))
		{
			output = "DON'T BE RUDE";
			return output;
		}
		if(hasGoodJob(in))
			output += "Thank you!! ";
		if(hasGreeting(in)) 
			output += "Hello!! ";
		if(in.contains("my name") && !hasQuestion(in))
		{
			getName(in);
			output += "Thank you for telling me your name!! ";
		}
		if(hasQuestion(in))
			output += answerQuestion(in);
		return output;
	}

	private static boolean includesSwears(String in) 
	{
		if(in.contains("mfer"))
			return true;
		return false;
	}

	private static boolean hasGoodJob(String in) 
	{
		if(in.contains("good job") || in.contains("gj"))
			return true;
		return false;
	}

	private static void getName(String in) 
	{
		String[] parts = in.split(" ");
		
		for(int i = 0; i < parts.length - 3; i++)
		{
			if(parts[i].equals("my") && parts[i + 1].equals("name")
					&& parts[i + 2].equals("is"))
				//includes a regex to remove characters that aren't letters/digits
				PersonalInfo.userName = parts[i + 3].replaceAll("[^a-zA-Z0-9]", "");
		}
	}

	private static String answerQuestion(String in) 
	{
		if(in.contains("fav"))
		{
			//System.out.println("i will answer ur question");
			return favQuestion(in);
		}
		if(in.contains("name"))
		{
			if(in.contains("your")||in.contains("ur"))
			{
				if(PersonalInfo.myName == null)
					return "I don't have a name yet!! ";
				return "My name is " + PersonalInfo.myName + "!! ";
			}
			if(in.contains("my"))
			{
				if(PersonalInfo.userName == null)
					return "I don't know your name!!";
				return "Your name is " + PersonalInfo.userName + "!! ";
			}
		}
		if(hasMath(in))
		{
			return (solveMath(in));
		}
		return "i wnt answer ur question srry";
	}

	private static String solveMath(String in) 
	{
		String[] parts = in.split(" ");
		
		//TODO: change so it's recursive? it can solve complex math problems??
		
		int op1 = 0, op2 = 0;
		String op = null;
		for(int size = 1; size < parts.length - 1; size++)
		{
			if(parts[size].equals("+")||parts[size].equals("-")||
					parts[size].equals("*")||parts[size].equals("/"))
			{
				op1 = Integer.parseInt(parts[size - 1]);
				op2 = Integer.parseInt(parts[size + 1].replace("?", ""));
				op = parts[size];
			}
		}
		
		if(op.equals("+"))
			return "" + op1 + " + " + op2 + " is " + (op1 + op2) + "!! ";
		if(op.equals("-"))
			return "" + op1 + " - " + op2 + " is " + (op1 - op2) + "!! ";
		if(op.equals("*"))
			return "" + op1 + " * " + op2 + " is " + (op1 * op2) + "!! ";
		if(op.equals("/"))
			return "" + op1 + " / " + op2 + " is " + (op1 / op2) + 
					" remainder " + (op1 % op2) + "!!";
		return null;
	}

	private static boolean hasMath(String in) 
	{
		if(in.contains(" + ")||in.contains(" - ")||in.contains(" / ")||
				in.contains(" * "))
			return true;
		return false;
	}

	private static String favQuestion(String in) 
	{
		String output = "My favorite ";
		int offset = 0;
		String question;
		if(in.contains("favorite"))
		{
			offset = in.indexOf("favorite");
			question = in.substring(offset+9, in.length() - 1);
		}
		else
		{
			offset = in.indexOf("fav");
			question = in.substring(offset+4, in.length() - 1);
		}
		output += question + " is ";
		
		//System.out.println("Question: " + question);
		
		if(question.contains("color"))
		{
			if(PersonalInfo.favColor == null)
			{
				String newFavColor = Colors.getRandColor();
				PersonalInfo.favColor = newFavColor;
				output += newFavColor;
			}
			else
				output += PersonalInfo.favColor;
		}
		
		return output + "!! ";
	}

	private static boolean hasQuestion(String in) 
	{
		if(in.contains("?"))
			return true;
		return false;
	}

	private static boolean hasGreeting(String in) 
	{
		if(in.contains("hi")||in.contains("hello")||in.contains("hey"))
			return true;
		return false;
	}
}
