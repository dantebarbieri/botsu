import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class botsu
{
	//Robot DT = new Robot();
	public static void main(String [] args) throws AWTException, InterruptedException
	{
		Robot DT = new Robot();

		smoothMove(0,0,DT);
		smoothMove(900,1005,DT);
		Point currentPos = MouseInfo.getPointerInfo().getLocation();
		double xPos = currentPos.getX();
		double yPos = currentPos.getY();
		System.out.print(xPos + " " + yPos);
		//DT.mouseMove(0,0);
		//DT.mousePress(InputEvent.BUTTON1_MASK);
		//DT.mouseMove(200,200);
		//DT.mouseRelease(InputEvent.BUTTON1_MASK);

	}

	public static void smoothMove(int x, int y, Robot DT)
	{
		// int distance = 
		DT.mouseMove(x,y);
	}
}
