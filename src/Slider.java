public class Slider extends OsuObject {

  private DVector[] points;
  private int duration;

  public Slider(DVector pos, int time, DVector[] path, int dur) {
    super(pos, time, 'L');
    points = new DVector[path.length];
    for (int i = 0; i < path.length; i++)
      points[i] = path[i].copy();
    duration = dur;
  }

  public DVector[] getPoints() {
    return points;
  }

  public int getDuration() {
    return duration;
  }

  public double getTimePerPoint() {
    return (double) points.length / duration;
  }

  public void setPoints(DVector[] path) {
    points = new DVector[path.length];
    for (int i = 0; i < path.length; i++)
      points[i] = path[i].copy();
  }

  public void setDuration(int dur) {
    duration = dur;
  }

  @Override
  public String toString() {
    return super.toString() + "\nPoints: " + Arrays.toString(points) + "\nDuration (millis): " + duration
        + "\nType: Slider";
  }
}
