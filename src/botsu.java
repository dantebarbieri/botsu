import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Botsu {
	public static Robot DT;
	public static DVector position;
	public static DVector velocity;
	public static float pxCircleSize;
	public static float sliderSpeed;

	public static void main(String[] args) throws AWTException, InterruptedException {
		DT = new Robot();

		double[] difficulty = FileDataReader.readDifficulties(args[0]); //args[0] represents the filename passed into the program through the console

		pxCircleSize = 54.4f - 4.48f * (float) difficulty[0];
		sliderSpeed = 100f * (float) difficulty[3];

		OsuObject[] objects = FileDataReader.getObjects(args[0]);

		position = new DVector(getMousePos());
		velocity = new DVector(0, 0);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		for (int i = 0; i < 10; i++) {
			DVector goal = new DVector(Math.random() * screenSize.getWidth(), Math.random() * screenSize.getHeight());

			System.out.println("Goal:\n" + goal);

			steerMove(goal, 13, 0.1f, 12, 0.001f);
			DVector currentPos = new DVector(getMousePos());
			System.out.println("End:\n" + currentPos);
		}
	}

	public static void smoothMove(int x, int y, int smoothness) {
		DVector start = new DVector(getMousePos());
		DVector current = start.copy();
		DVector cease = new DVector(x, y);
		DVector diff = DVector.sub(cease, start);
		diff.div(smoothness);
		for (int i = 0; i < smoothness; i++) {
			current.add(diff);
			DT.mouseMove((int) current.x(), (int) current.y());
		}
	}

	public static void smoothMove(DVector cease, int smoothness) {
		DVector start = new DVector(getMousePos());
		DVector current = start.copy();
		DVector diff = DVector.sub(cease, start);
		diff.div(smoothness);
		for (int i = 0; i < smoothness; i++) {
			current.add(diff);
			DT.mouseMove((int) current.x(), (int) current.y());
		}
	}

	public static void steerMove(DVector target, float radius, float max_speed, float slowing_distance, float max_force) {
		DVector target_offset, desired_velocity, steering, acceleration;
		float distance, ramped_speed, clipped_speed;
		while (DVector.dist(position, target) > radius) {
			target_offset = DVector.sub(target, position);
			distance = target_offset.r();
			ramped_speed = max_speed * (distance / slowing_distance);
			clipped_speed = Math.min(ramped_speed, max_speed);
			desired_velocity = DVector.mult(target_offset, clipped_speed / distance);
			steering = DVector.sub(desired_velocity, velocity);
			acceleration = steering.copy();
			acceleration.truncate(max_force);
			velocity.add(acceleration);
			if (random() < 0.003 * max_speed)
				velocity.changeSpeed(random(-0.15f * max_speed, 0.1f * max_speed));
			velocity.truncate(max_speed);
			position.add(velocity);
			if (random() < 0.3 * max_speed)
				position.add(DVector.random(1.5f * max_speed));
			DT.mouseMove((int) position.x(), (int) position.y());
		}
	}

	public static Point getMousePos() {
		return MouseInfo.getPointerInfo().getLocation();
	}

	public final static float random() {
		return (float) Math.random();
	}

	public final static float random(float high) {
		return high * (float) Math.random();
	}

	public final static float random(float low, float high) {
		float temp = low < high ? high : low;
		low = low < high ? low : high;
		high = temp;
		return (high - low) * (float) Math.random() + low;
	}
}
