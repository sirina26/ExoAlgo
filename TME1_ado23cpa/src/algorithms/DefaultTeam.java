package algorithms;

import java.awt.Point;
import java.util.ArrayList;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
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
}
