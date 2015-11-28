package org.ulco;

import org.ulco.*;

/**
 * Created by Antoine on 28/11/2015.
 */
public class Builder {
    public Builder(){

    }

    public Document createGridDocuement(Point origin, int line, int column, double length){
        Document d = new Document();

        Layer layer = d.createLayer();

        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }

        return d;
    }

    public Document createCircleDocuement(Point center, int number, double radius, double delta){
        Document d = new Document();

        Layer layer = d.createLayer();

        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }

        return d;
    }
}
