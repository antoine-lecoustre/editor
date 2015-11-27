package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public Group() {
        //m_groupList = new  Vector<Group>();
        m_objectList = new GraphicsObjects();
        m_ID = ID.instance();

    }

    public Group(String json) {
        //m_groupList = new  Vector<Group>();
        m_objectList = new GraphicsObjects();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public void add(Object object) {
        if (object instanceof GraphicsObject) {
            addObject((GraphicsObject) object);
        }
    }

    /*private void addGroup(Group group) {
        m_groupList.add(group);
    }*/

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            g.addObject(element.copy());
        }
        /*for (Object o : m_groupList) {
            Group element = (Group) (o);

            g.addGroup(element.copy());
        }*/
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public GraphicsObjects getObjectList(){return m_objectList;}

    @Override
    boolean isClosed(Point pt, double distance) {
        return false;
    }

    public void move(Point delta) {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }
        /*for (Object o : m_groupList) {
            Group element = (Group) (o);

            element.move(delta);
        }*/
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); ++i) {

            GraphicsObject element = m_objectList.elementAt(i);
            if(element instanceof Group){
                size += ((Group) element).size();
            }else{
                size += 1;
            }


            /*if(m_objectList.elementAt(i) instanceof Group){
                Group element = (Group)m_objectList.elementAt(i);
                size += element.size();
            }*/
        }
        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (!(element instanceof Group)) {
                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }

            }
        }

        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element instanceof Group) {
                str += element.toJson();
            }

        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (!(element instanceof Group)) {
                str += element.toString();
                System.out.println(element.toString() + " => " + i);
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element instanceof Group) {
                str += element.toString();
            }
        }
        return str + "]]";
    }

    //private Vector<Group> m_groupList;
    private GraphicsObjects m_objectList;
    private int m_ID;
}