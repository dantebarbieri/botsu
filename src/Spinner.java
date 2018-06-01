public class Spinner extends OsuObject {

  private int endTime;

  public Spinner(DVector pos, int time, int end) {
    super(pos, time, 'P');
    endTime = time;
  }

  public int getEndTime() {
    return endTime;
  }

  public int getDuration() {
    return endTime - startTime;
  }

  public void setEndTime(int end) {
    endTime = end;
  }

  public void setDuration(int dur) {
    endTime = super.getTime() + dur;
  }

  @Override
  public String toString() {
    return super.toString() + "\nEnd Time: " + endTime + "\nType: Spinner";
  }
}
