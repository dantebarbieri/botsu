import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileDataReader {
  public static double[] readDifficulties(String file) throws IOException {
    Scanner reader = new Scanner(new File(file));
    double circleSize = null;
    double overallDifficulty = null;
    double approachRate = null;
    double sliderMultiplier = null;
    double sliderTickRate = null;
    while (reader.hasNextLine() && !reader.nextLine().toLowerCase().equals("[difficulty]")) {
      if (reader.nextLine().toLowerCase().equals("[difficulty]")) {
        reader.nextLine();
        circleSize = endNum(reader.nextLine());
        overallDifficulty = endNum(reader.nextLine());
        approachRate = endNum(reader.nextLine());
        sliderMultiplier = endNum(reader.nextLine());
        sliderTickRate = endNum(reader.nextLine());
      }
    }
    reader.close();
    return new double[] { circleSize, overallDifficulty, approachRate, sliderMultiplier, sliderTickRate };
  }

  public static OsuObject[] getObjects(String file) {
    String[] raw = readObjects(file);
    double[][] numbers = interpretRaw(raw);
    return parseOsuObjects(numbers);
  }

  private static String[] readObjects(String file) {
    String ret = "";
    Scanner sc = new Scanner(new File(file));
    while (sc.hasNext()) {
      ret += sc.nextLine()/*TODO: + "\n"*/;
    }
    sc.close();
    return ret;
  }

  private static double[][] interpretRaw(String[] raw) {
    double[][] ret = new double[raw.length][];
    for (int i = 0; i < raw.length; i++) {
      String[] nums = raw[i].split("\\D|[^\\056]");
      ret[i] = new double[nums.length];
      for (int j = 0; j < nums.length; j++) {
        ret[i][j] = Double.parseDouble(nums[j]);
      }
    }
    return ret;
  }

  private static OsuObject[] parseOsuObjects(double[][] objs) {
    OsuObject[] ret = new OsuObject[objs.length];
    for (int i = 0; i < objs.length; i++) {
      double x = objs[i][0];
      double y = objs[i][1];
      double time = objs[i][2];
      int type = ((int) objs[i][3]) % 16;
      switch (type) {
      case 1:
      case 5:
        ret[i] = new HitCircle(new DVector(x, y), time);
        break;
      case 2:
      case 6:
        DVector[] path = new DVector[(objs[i].length - 7) / 2];
        int index = 0;
        for (int j = 5; j < objs[i].length - 2; j += 2) {
          path[index++] = new DVector(objs[i][j], objs[i][j + 1]);
        }
        ret[i] = new Slider(new DVector(x, y), time, path);
        break;
      case 8:
      case 12:
        ret[i] = new Spinner(new DVector(x, y), time);
        break;
      default:
        throw new Exception("Unexpected Osu Object Type Found: " + type);
        break;
      }
    }
    return null;
  }

  public static double endNum(String str) {
    return Double.parseDouble(str.substring(str.lastIndexOf(":") + 1));
  }
}
