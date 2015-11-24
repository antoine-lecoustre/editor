package org.ulco;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));


        String className = type.substring(0,1).toUpperCase() + type.substring(1);
        Class myClass = null;

        try {
            myClass = Class.forName("org.ulco." + className);
            Constructor cons= myClass.getConstructor(String.class);
            o = (GraphicsObject) cons.newInstance(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return o;
    }

    static public GraphicsObject parseGroup(String json) {
        return parse(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
