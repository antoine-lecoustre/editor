package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.GraphicsObject;
import org.ulco.Point;
import org.ulco.Triangle;

/**
 * Created by Antoine on 28/11/2015.
 */
public class TriangleTest extends TestCase {
    public void testType() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), new Point(2, 2), new Point(-2, 2));

        assertTrue(t instanceof Triangle);
        assertTrue(t instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), new Point(2, 2), new Point(-2, 2));


        assertEquals(t.toJson(), "{ type: triangle, point1: { type: point, x: 0.0, y: 0.0 }, point2: { type: point, x: 2.0, y: 2.0 }, point3: { type: point, x: -2.0, y: 2.0 } }");
    }

    public void testCopy() throws Exception {
        Triangle t = new Triangle(new Point(0, 0), new Point(2, 2), new Point(-2, 2));

        assertEquals(t.toJson(), t.copy().toJson());
    }
}
