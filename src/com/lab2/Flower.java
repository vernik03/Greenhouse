package com.lab2;

import java.awt.*;
import java.security.PublicKey;

public class Flower implements Comparable<Flower> {
    public enum SoilEnum {
        PODZOLIC("Podzolic soil"),
        GROUND("Ground soil"),
        SODDY_PODZOLIC("Soddy-podzolic soil");
        private String soil;
        SoilEnum(String soil) {
            this.soil = soil;}
        public String getSoil() {
            return soil;}
    }

    public enum MultiplyingEnum {
        BY_LEAVES("Multiplying by leaves"),
        BY_CUTTINGS("Multiplying by cuttings"),
        BY_SEEDS("Multiplying by seeds");
        private String multiplying;
        MultiplyingEnum(String multiplying) {
            this.multiplying = multiplying;}
        public String getMultiplying() {
            return multiplying;}
    }

    public enum SizeEnum {
        VERY_SMALL("Very small size"),
        SMALL("Small size"),
        MEDIUM("Medium size"),
        BIG("Big size"),
        VERY_BIG("Very big size");
        private String size;
        SizeEnum(String size) {
            this.size = size;}
        public String getSize() {
            return size;}
    }

    public class VisualParameters {
//        Color stem_color;
        String stem_color;
//        Color leaf_color;
        String leaf_color;
        SizeEnum size;
//        public VisualParameters(Color stem_color, Color leaf_color, SizeEnum size) {
        public VisualParameters(String stem_color, String leaf_color, SizeEnum size) {
            this.stem_color = stem_color;
            this.leaf_color = leaf_color;
            this.size = size;
        }

    }

    public class GrowingTips {
        Float temperature;
        Boolean is_photophilous;
        Float Watering;
        public GrowingTips(Float temperature, Boolean is_photophilous, Float watering) {
            this.temperature = temperature;
            this.is_photophilous = is_photophilous;
            Watering = watering;
        }

    }

    private String name;
    private Integer id;
    private SoilEnum soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private MultiplyingEnum multiplying;

    public Flower(){
        this.name = "No name";
        this.soil = SoilEnum.PODZOLIC;
        this.origin = "No origin";
        this.visualParameters = new VisualParameters("No color", "No color", SizeEnum.VERY_SMALL);
        this.growingTips = new GrowingTips(0f, false, 0f);
        this.multiplying = MultiplyingEnum.BY_LEAVES;
    }

    public Flower(String name, Integer id, String soil, String origin,
                  String stem_color, String leaf_color, String size,
                  Float temperature, Boolean is_photophilous, Float watering,
                  String multiplying) {
        this.name = name;
        this.id = id;
        this.soil = SoilEnum.valueOf(soil);
        this.origin = origin;
        this.visualParameters = new VisualParameters(stem_color, leaf_color, SizeEnum.valueOf(size));
        this.growingTips = new GrowingTips(temperature, is_photophilous, watering);
        this.multiplying = MultiplyingEnum.valueOf(multiplying);
    }

    @Override
    public int compareTo(Flower o) {
        return this.getId() - o.getId();
    }

    public int getId() {return id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {this.id = Integer.parseInt(id);}

    public void setSoil(String soil) {
        this.soil = SoilEnum.valueOf(soil);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setStem_color(String stem_color) {
        this.visualParameters.stem_color = stem_color;
    }

    public void setLeaf_color(String leaf_color) {
        this.visualParameters.leaf_color = leaf_color;
    }

    public void setAverageSize(String size) {
        this.visualParameters.size = SizeEnum.valueOf(size);
    }

    public void setTemperature(Float temperature) {
        this.growingTips.temperature = temperature;
    }

    public void setLighting(Boolean is_photophilous) {
        this.growingTips.is_photophilous = is_photophilous;
    }

    public void setWatering(Float watering) {
        this.growingTips.Watering = watering;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = MultiplyingEnum.valueOf(multiplying);
    }

    public void printClass(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Soil: " + soil.getSoil());
        System.out.println("Origin: " + origin);
        System.out.println("Stem color: " + visualParameters.stem_color);
        System.out.println("Leaf color: " + visualParameters.leaf_color);
        System.out.println("Size: " + visualParameters.size.getSize());
        System.out.println("Temperature: " + growingTips.temperature);
        System.out.println("Is photophilous: " + growingTips.is_photophilous);
        System.out.println("Watering: " + growingTips.Watering);
        System.out.println("Multiplying: " + multiplying.getMultiplying());
    }
}

