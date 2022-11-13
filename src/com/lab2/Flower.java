package com.lab2;

import java.awt.*;

public class Flower {
    public enum SoilEnum {
        PODZOLIC,
        GROUND,
        SODDY_PODZOLIC,
    }

    public enum MultiplyingEnum {
        BY_LEAVES,
        BY_CUTTINGS,
        BY_SEEDS,
    }

    public enum SizeEnum {
        VERY_SMALL,
        SMALL,
        MIDDLE,
        BIG,
        VERY_BIG,
    }

    public class VisualParameters {
        Color stem_color;
        Color leaf_color;
        SizeEnum size;

    }

    public class GrowingTips {
        Float temperature;
        Boolean is_photophilous;
        Float Watering;

    }

    private String name;
    private SoilEnum soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private MultiplyingEnum multiplying;


}

