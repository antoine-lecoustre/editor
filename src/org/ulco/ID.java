package org.ulco;

public class ID {
    static public int ID = 0;
    private static ID pInstance;

    private ID(){

    }

    public static int instance(){
        if(pInstance != null){
            pInstance = new ID();
        }
        ID = ++ID;
        return ID;
    }
}