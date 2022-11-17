package com.lab2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SAX {

    private static ArrayList<Flower> Flowers = new ArrayList<>();
    final static private String separator = "====================";

    public SAX(String path, String name) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(new File(path+".xml"), handler);

            Collections.sort(Flowers);
            printFromArray(Flowers);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void printFromArray(ArrayList<Flower> FlowerArray) {
        for (Flower flower : FlowerArray) {
            flower.printClass();
            System.out.println(separator);
        }
    }


    private static class XMLHandler extends DefaultHandler {
        private Flower flower = new Flower();
        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                switch (lastElementName) {
                    case "name":
                        flower.setName(information);
                        break;
                    case "soil":
                        flower.setSoil(information);
                        break;
                    case "origin":
                        flower.setOrigin(information);
                        break;
                    case "multiplying":
                        flower.setMultiplying(information);
                        break;
                    case "flowerID":
                        flower.setId(information);
                        break;
                    case "stem_color":
                        flower.setStem_color(information);
                        break;
                    case "leaf_color":
                        flower.setLeaf_color(information);
                        break;
                    case "temperature":
                        flower.setTemperature(Float.parseFloat(information));
                        break;
                    case "lighting":
                        flower.setLighting(Boolean.parseBoolean(information));
                        break;
                    case "watering":
                        flower.setWatering(Float.parseFloat(information));
                        break;
                }
            }
            if (lastElementName.equals("origin") && information.isEmpty()) {
                if (flower.getId() != 0) {
                    Flowers.add(flower);
                    flower = new Flower();
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

        }
    }
}