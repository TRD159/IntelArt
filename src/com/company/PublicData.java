package com.company;

public class PublicData {
    public static int layerViewed = 0, columnViewed = 0;

    public static void upLayer() {
        layerViewed--;
        if(layerViewed < 0)
            layerViewed += 8;
        System.out.println(layerViewed);
    }
    public static void downLayer() {
        layerViewed++;
        if(layerViewed > 7)
            layerViewed -= 8;
        System.out.println(layerViewed);
    }

    public static void right() {
        columnViewed++;
        if(columnViewed > 7)
            columnViewed -= 8;
    }
    public static void left() {
        columnViewed--;
        if(columnViewed < 0)
            columnViewed += 8;
    }

    public static int getLayerViewed() {
        return layerViewed;
    }

    public static void setLayerViewed(int layerViewed) {
        PublicData.layerViewed = layerViewed;
    }
}
