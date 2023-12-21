package algorithms;
import java.awt.Point;
import java.util.ArrayList;
import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size() < 3) {
      return null;
    }

    Point p = points.get(0);
    Point q = points.get(1);
    double maxDistanceSquared = calculateDistanceSquared(p, q);

    for (int i = 0; i < points.size() - 1; i++) {
      for (int j = i + 1; j < points.size(); j++) {
        Point pointA = points.get(i);
        Point pointB = points.get(j);

        double distanceSquared = calculateDistanceSquared(pointA, pointB);

        if (distanceSquared > maxDistanceSquared) {
          maxDistanceSquared = distanceSquared;
          p = pointA;
          q = pointB;
        }
      }
    }

    return new Line(p, q);
  }

  private double calculateDistanceSquared(Point a, Point b) {
    int deltaX = (int) (a.getX() - b.getX());
    int deltaY = (int) (a.getY() - b.getY());
    return deltaX * deltaX + deltaY * deltaY;
  }

  public Circle calculCercleMin(ArrayList<Point> points) {
    if (points.isEmpty()) {
      return null;
    }

    Point center = points.get(0);
    double maxDistanceSquared = 0;

    for (int i = 0; i < points.size() - 1; i++) {
      for (int j = i + 1; j < points.size(); j++) {
        Point pointA = points.get(i);
        Point pointB = points.get(j);

        double distanceSquared = calculateDistanceSquared(pointA, pointB);

        if (distanceSquared > maxDistanceSquared) {
          maxDistanceSquared = distanceSquared;
          center = calculateMidpoint(pointA, pointB);
        }
      }
    }

    int radius = (int) Math.sqrt(maxDistanceSquared) / 2;  // Corrected division

    return new Circle(center, radius);
  }

  private Point calculateMidpoint(Point a, Point b) {
    int midX = (int) ((a.getX() + b.getX()) / 2);
    int midY = (int) ((a.getY() + b.getY()) / 2);  // Added parentheses
    return new Point(midX, midY);
  }

  public Circle BMINIDISK(Point p, ArrayList<Point> R) {
    if (p != null && Math.abs(3) != 3) {
      // Assuming b_md(null, R) is the desired behavior when p is not null and abs(3) is not equal to 3
      return b_md(null, R);
    } else {
      Point randomPoint = getRandomPoint(R);
      Circle D = BMINIDISK(randomPoint, subtractPoint(R, randomPoint));

      if (D != null && !pointInCircle(p, D)) {
        Circle m = BMINIDISK(randomPoint, unionPoint(R, randomPoint));
        return D;
      }

      return D;
    }
  }

  private Circle b_md(Point c, ArrayList<Point> R) {
    // Implement b_md logic here...
    return null; // Replace with actual result
  }

  private Point getRandomPoint(ArrayList<Point> points) {
    int randomIndex = (int) (Math.random() * points.size());
    return points.get(randomIndex);
  }

  private ArrayList<Point> subtractPoint(ArrayList<Point> points, Point p) {
    ArrayList<Point> result = new ArrayList<>(points);
    result.remove(p);
    return result;
  }

  private ArrayList<Point> unionPoint(ArrayList<Point> points, Point p) {
    ArrayList<Point> result = new ArrayList<>(points);
    result.add(p);
    return result;
  }

  private boolean pointInCircle(Point point, Circle circle) {
    // Implement logic to check if a point is inside a circle...
    return false; // Replace with actual result
  }
}
