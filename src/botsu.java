import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class botsu
{
	public static Robot DT;
	public static void main(String [] args) throws AWTException, InterruptedException
	{
		DT = new Robot();

		vector goal = new vector(900, 1005);

		System.out.println("Goal:\n" + goal);

		smoothMove(goal, (int)(vector.dist(new vector(getMousePos()), goal) * 10));
		vector currentPos = new vector(getMousePos());
		System.out.println("End:\n" + currentPos);

	}

	public static void smoothMove(int x, int y, int smoothness)
	{
		vector start = new vector(getMousePos());
		vector current = start.copy();
		vector cease = new vector(x, y);
		vector diff = vector.sub(cease, start);
		// diff.mult(SPEED);
		diff.div(smoothness);
		for(int i = 0; i < smoothness; i++){
			current.add(diff);
			DT.mouseMove((int)current.x(),(int)current.y());
		}
	}

	public static void smoothMove(vector cease, int smoothness)
	{
		vector start = new vector(getMousePos());
		vector current = start.copy();
		vector diff = vector.sub(cease, start);
		// diff.mult(SPEED);
		diff.div(smoothness);
		for(int i = 0; i < smoothness; i++){
			current.add(diff);
			DT.mouseMove((int)current.x(),(int)current.y());
		}
	}

	public static Point getMousePos(){ return MouseInfo.getPointerInfo().getLocation();	}
}
