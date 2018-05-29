import java.awt.Point;

public class vector{
  /**
  * @author Dante Barbieri
  * @param x - X Position of the Vector
  * @param y - Y Position of the Vector
  * @param a - The Angle of the Vector in Radians
  * @param r - The Magnitude of the Vector
  */
  private float x, y, a, r;

  /**
  * @author Dante Barbieri
  * @param x - X Position of the Vector
  * @param y - Y Position of the Vector
  */
  public vector(float x, float y){
    this.x = x;
    this.y = y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /**
  * @author Dante Barbieri
  * @param p - Point containing X and Y Values
  */
  public vector(Point p){
    this.x = (float)p.getX();
    this.y = (float)p.getY();
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /**
  * @author Dante Barbieri
  * @param v - Vector containing X, Y, A, and R Values
  */
  public vector(vector v){
    x = v.x;
    y = v.y;
    a = v.a;
    r = v.r;
  }

  public float x(){
    return x;
  }

  public void x(float f){
    x = f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public float y(){
    return y;
  }

  public void y(float f){
    y = f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public float a(){
    return a;
  }

  public void a(float f){
    a = f;
    x = r * (float)Math.sin(a);
    y = r * (float)Math.cos(a);
  }

  public float r(){
    return r;
  }

  public void r(float f){
    r = f;
    x = r * (float)Math.sin(a);
    y = r * (float)Math.cos(a);
  }

  public void add(vector other){
    this.x += other.x;
    this.y += other.y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public void sub(vector other){
    this.x -= other.x;
    this.y -= other.y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public void mult(float f){
    x *= f;
    y *= f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public void div(float f){
    x /= f;
    y /= f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  public float dist(vector other){
    return Math.abs(vector.sub(this, other).r);
  }

  public float mag(){
    return r;
  }

  public float magSq(){
    return x * x + y * y;
  }

  public vector copy(){
    return (new vector(this));
  }

  public static vector add(vector a, vector b){
    return new vector(a.x+b.x, a.y+b.y);
  }

  public static vector sub(vector a, vector b){
    return new vector(a.x-b.x, a.y-b.y);
  }

  public static vector mult(vector v, float f){
    vector a = v.copy();
    a.mult(f);
    return a;
  }

  public static vector div(vector v, float f){
    vector a = v.copy();
    a.div(f);
    return a;
  }

  public static float dist(vector a, vector b){
    return Math.abs(vector.sub(a, b).r);
  }

  @Override
  public String toString(){
    // return "(x, y): (" + x + ", " + y + ")\n(r, \u03b8): (" + r + ", " + a + ")";
    return "(x, y): (" + x + ", " + y + ")\n(r, θ): (" + r + ", " + a + ")";
  }
}
