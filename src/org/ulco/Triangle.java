package org.ulco;

/**
 * Created by Antoine on 28/11/2015.
 */
public class Triangle extends GraphicsObject{

    public Triangle(Point p1, Point p2, Point p3){
        this.m_point1 = p1;
        this.m_point2 = p2;
        this.m_point3 = p3;
    }

    private Triangle(String json){
        String str = json.replaceAll("\\s+","");
        int point1Index = str.indexOf("point1");
        int point2Index = str.indexOf("point2");
        int point3Index = str.indexOf("point3");
        int endIndex = str.lastIndexOf("}");

        m_point1 = new Point(str.substring(point1Index + 7, point2Index - 1));
        m_point2 = new Point(str.substring(point2Index + 7, point3Index - 1));
        m_point3 = new Point(str.substring(point3Index + 7, endIndex));
    }

    @Override
    public GraphicsObject copy() {
        return new Triangle(m_point1.copy(), m_point2.copy(),m_point3.copy());
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        return false;
    }

    @Override
    void move(Point delta) {

    }

    @Override
    public String toJson() {
        return "{ type: triangle, point1: " + m_point1.toJson() + ", point2: " + m_point2.toJson() + ", point3: " + m_point3.toJson() + " }";
    }

    @Override
    public String toString() {
        return "triangle[" + m_point1.toString() + "," + m_point2.toString() + "," + m_point3.toString() + "]";
    }

    private final Point m_point1;
    private final Point m_point2;
    private final Point m_point3;
}
