public abstract class OsuObject {
  private DVector position;
  private int time;
  private char type;

  public OsuObject(DVector position, int time, char type) {
    this.position = position.copy();
    this.time = time;
    this.type = type;
  }

  final public DVector getPosition() {
    return position;
  }

  final public int getTime() {
    return time;
  }

  final public char getType() {
    return type;
  }

  final public void setPosition(DVector v) {
    position = v.copy();
  }

  final public void setTime(int i) {
    time = i;
  }

  final public void setType(char c) {
    type = c;
  }

  @Override
  public String toString() {
    return position + "\n" + time;
  }
}
