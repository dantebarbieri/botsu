public class HitCircle extends OsuObject {
  public HitCircle(DVector pos, int time) {
    super(pos, time, 'H');
  }

  @Override
  public String toString() {
    return super.toString() + "\nType: Hit Circle";
  }
}
