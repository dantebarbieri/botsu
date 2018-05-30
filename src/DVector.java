import java.awt.Point;

public class DVector{
  /** List of Class Variables that Vectors Have
  * @author Dante Barbieri
  * @param x - X Position of the DVector
  * @param y - Y Position of the DVector
  * @param a - The Angle of the DVector in Radians
  * @param r - The Magnitude of the DVector
  */
  private float x, y, a, r;

  /** Double Parameter Vector Constructor
  * @author Dante Barbieri
  * @param x - Double containing X Position of the DVector
  * @param y - Double containing Y Position of the DVector
  */
  public DVector(double x, double y){
    this.x = (float)x;
    this.y = (float)y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Point Parameter Vector Constructor
  * @author Dante Barbieri
  * @param p - Point containing X and Y Values
  */
  public DVector(Point p){
    x = (float)p.getX();
    y = (float)p.getY();
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Vector Parameter Vector Constructor (used for Deep Copy)
  * @author Dante Barbieri
  * @param v - DVector containing X, Y, A, and R Values
  */
  public DVector(DVector v){
    x = v.x;
    y = v.y;
    a = v.a;
    r = v.r;
  }

  /** Getter Method for X Parameter
  * @author Dante Barbieri
  */
  public float x(){
    return x;
  }

  /** Setter Method for X Parameter
  * @author Dante Barbieri
  * @param f - Float containing New X Parameter
  */
  public void x(float f){
    x = f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Getter Method for Y Parameter
  * @author Dante Barbieri
  */
  public float y(){
    return y;
  }

  /** Setter Method for Y Parameter
  * @author Dante Barbieri
  * @param f - Float containing New Y Parameter
  */
  public void y(float f){
    y = f;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Getter Method for Angle Parameter
  * @author Dante Barbieri
  */
  public float a(){
    return a;
  }

  /** Setter Method for Angle Parameter
  * @author Dante Barbieri
  * @param f - Float containing New Angle Parameter
  */
  public void a(float f){
    a = f;
    x = r * (float)Math.cos(a);
    y = r * (float)Math.sin(a);
  }

  /** Getter Method for Radius Parameter
  * @author Dante Barbieri
  */
  public float r(){
    return r;
  }

  /** Setter Method for Radius Parameter
  * @author Dante Barbieri
  * @param f - Float containing New Radius Parameter
  */
  public void r(float f){
    r = f;
    x = r * (float)Math.cos(a);
    y = r * (float)Math.sin(a);
  }

  /** Conditional Setter Method for Radius Parameter
  * @author Dante Barbieri
  * @param f - Float containing New Maximum Radius Parameter
  */
  public void truncate(float f){
    if(r > f){
      r = f;
      x = r * (float)Math.cos(a);
      y = r * (float)Math.sin(a);
    }
  }

  /** Incrememter for Radius Parameter
  * @author Dante Barbieri
  * @param f - Float containing Value to Increment the Radius
  */
  public void incrementMagnitude(float f){
    r += f;
    x = r * (float)Math.cos(a);
    y = r * (float)Math.sin(a);
  }

  /** Vector Addition Method
  * @author Dante Barbieri
  * @param other - DVector containing DVector to add to this
  */
  public void add(DVector other){
    this.x += other.x;
    this.y += other.y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Vector Subtraction Method
  * @author Dante Barbieri
  * @param other - DVector containing DVector to subtract from this
  */
  public void sub(DVector other){
    this.x -= other.x;
    this.y -= other.y;
    a = (float)Math.atan2(y, x);
    r = (float)Math.sqrt(x * x + y * y);
  }

  /** Vector Multiplying Scale Method
  * @author Dante Barbieri
  * @param f - Float containing Multiplying Scale Factor
  */
  public void mult(float f){
    r *= f;
    x = r * (float)Math.cos(a);
    y = r * (float)Math.sin(a);
  }

  /** Vector Dividing Scale Method
  * @author Dante Barbieri
  * @param f - Float containing Dividing Scale Factor
  */
  public void div(float f){
    r /= f;
    x = r * (float)Math.cos(a);
    y = r * (float)Math.sin(a);
  }

  public float dist(DVector other){
    return Math.abs(DVector.sub(this, other).r);
  }

  public DVector copy(){
    return new DVector(this);
  }

  public static DVector add(DVector a, DVector b){
    return new DVector(a.x+b.x, a.y+b.y);
  }

  public static DVector sub(DVector a, DVector b){
    return new DVector(a.x-b.x, a.y-b.y);
  }

  public static DVector mult(DVector v, float f){
    DVector a = v.copy();
    a.mult(f);
    return a;
  }

  public static DVector div(DVector v, float f){
    DVector a = v.copy();
    a.div(f);
    return a;
  }

  public static DVector random(float mag){
    float angle = (float)(Math.random() * Math.PI*2);
    return new DVector(mag * Math.cos(angle), mag * Math.sin(angle));
  }

  public static float dist(DVector a, DVector b){
    return Math.abs(DVector.sub(a, b).r);
  }

  @Override
  public String toString(){
    // return "(x, y): (" + x + ", " + y + ")\n(r, \u03b8): (" + r + ", " + a + ")";
    return "(x, y): (" + x + ", " + y + ")\n(r, θ): (" + r + ", " + a + ")";
  }
}
