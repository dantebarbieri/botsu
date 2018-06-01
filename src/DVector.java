import java.awt.Point;

public class DVector {
  /**
  * @author Dante Barbieri
  * @param x - X Position of the DVector
  * @param y - Y Position of the DVector
  * @param a - The Angle of the DVector in Radians
  * @param r - The Magnitude of the DVector
  */
  private float x, y, a, r;

  /**
  * @author Dante Barbieri
  * @param x - X Position of the DVector
  * @param y - Y Position of the DVector
  */
  public DVector(double x, double y) {
    this.x = (float) x;
    this.y = (float) y;
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  /**
  * @author Dante Barbieri
  * @param p - Point containing X and Y Values
  */
  public DVector(Point p) {
    x = (float) p.getX();
    y = (float) p.getY();
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  /**
  * @author Dante Barbieri
  * @param v - DVector containing X, Y, A, and R Values
  */
  public DVector(DVector v) {
    x = v.x;
    y = v.y;
    a = v.a;
    r = v.r;
  }

  public float x() {
    return x;
  }

  public void x(float f) {
    x = f;
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  public float y() {
    return y;
  }

  public void y(float f) {
    y = f;
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  public float a() {
    return a;
  }

  public void a(float f) {
    a = f;
    x = r * (float) Math.cos(a);
    y = r * (float) Math.sin(a);
  }

  public float r() {
    return r;
  }

  public void r(float f) {
    r = f;
    x = r * (float) Math.cos(a);
    y = r * (float) Math.sin(a);
  }

  public void truncate(float f) {
    if (r > f) {
      r = f;
      x = r * (float) Math.cos(a);
      y = r * (float) Math.sin(a);
    }
  }

  public void changeSpeed(float f) {
    r += f;
    x = r * (float) Math.cos(a);
    y = r * (float) Math.sin(a);
  }

  public void add(DVector other) {
    this.x += other.x;
    this.y += other.y;
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  public void sub(DVector other) {
    this.x -= other.x;
    this.y -= other.y;
    a = (float) Math.atan2(y, x);
    r = (float) Math.sqrt(x * x + y * y);
  }

  public void mult(float f) {
    r *= f;
    x = r * (float) Math.cos(a);
    y = r * (float) Math.sin(a);
  }

  public void div(float f) {
    r /= f;
    x = r * (float) Math.cos(a);
    y = r * (float) Math.sin(a);
  }

  //TODO: Fix this shit

  public float dist(DVector other) {
    return Math.abs(DVector.sub(this, other).r);
  }

  public DVector copy() {
    return new DVector(this);
  }

  public static DVector add(DVector a, DVector b) {
    return new DVector(a.x + b.x, a.y + b.y);
  }

  public static DVector sub(DVector a, DVector b) {
    return new DVector(a.x - b.x, a.y - b.y);
  }

  public static DVector mult(DVector v, float f) {
    DVector a = v.copy();
    a.mult(f);
    return a;
  }

  public static DVector div(DVector v, float f) {
    DVector a = v.copy();
    a.div(f);
    return a;
  }

  public static DVector random(float mag) {
    float angle = (float) (Math.random() * Math.PI * 2);
    return new DVector(mag * Math.cos(angle), mag * Math.sin(angle));
  }

  public static float dist(DVector a, DVector b) {
    return Math.abs(DVector.sub(a, b).r);
  }

  @Override
  public String toString() {
    return "(x, y): (" + x + ", " + y + ")\n(r, θ): (" + r + ", " + a + ")";
  }
}
