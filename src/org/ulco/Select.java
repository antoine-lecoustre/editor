package org.ulco;

/**
 * Created by Antoine on 24/11/2015.
 */
public class Select {
    public static GraphicsObjects selectDocument(Point pt, double distance,Document doc){
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : doc.getLayers()) {
            list.addAll(selectLayer(pt, distance, layer));
        }
        return list;
    }

    public static GraphicsObjects selectLayer(Point pt, double distance,Layer layer){
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer.getList()) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }
}
