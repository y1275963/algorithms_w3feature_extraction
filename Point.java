/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;


public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if(that == null) {
            throw new NullPointerException();
        }
        
        if(this.y == that.y) {
            if(this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            }
            else {
                return +0.0;
            }
        }
        else {
            if(this.x == that.x) {
                return Double.POSITIVE_INFINITY;
            }
            else {
                double re = ((double)this.y - (double)that.y) / ((double)this.x - (double)that.x); 
                return re;
            }
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if(this.y == that.y) {
            return this.x - that.x;
        } else {
            return this.y - that.y;
        }
    }
    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        // local class
        class SlopeOrder implements Comparator<Point> {
            public int compare(Point p1, Point p2) {
                if(p1 == null || p2 == null) {
                    throw new NullPointerException();
                }
                double p1r = slopeTo(p1);
                double p2r = slopeTo(p2);

                if(p1r > p2r) {
                    return 1;
                }
                if(p1r == p2r) {
                    return 0;
                }
                return -1;
            }
        }
        return new SlopeOrder();
    }


//                if(p1r == Double.NEGATIVE_INFINITY || p2r == Double.NEGATIVE_INFINITY) {
//                    return (int) Double.NEGATIVE_INFINITY;
//                }
//                else if(p1r == 0.0) { 
//                    return (int) p2r;
//                }
//                else if(p1r == Double.POSITIVE_INFINITY) {
//                    if(p2r == Double.POSITIVE_INFINITY)
//                        return (int) Double.POSITIVE_INFINITY;
//                    else
//                        return 1;
//                }
//                else {
//                    if(p2r == Double.POSITIVE_INFINITY)
//                        return -1;
//                    else
//                        return (int)(p1r - p2r);
//                }                          
//            }
//        }



    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point ptest = new Point(206, 36);
        StdOut.println(ptest.slopeTo(new Point(206, 449)));
        
        Point[] points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        Comparator<Point> test = points[0].slopeOrder();
        Arrays.sort(points,test);
        
        StdDraw.show();
        StdOut.println(points[0].slopeTo(points[2]));
        }    
}
