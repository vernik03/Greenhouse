package com.lab2;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class StAX {

    private static ArrayList<Flower> Flowers = new ArrayList<>();
    final static private String separator = "____________________";

    public StAX(String path, String name) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(path + ".xml"));
            int eventType = reader.getEventType();
            parse(reader, eventType);
            Collections.sort(Flowers);
            printFromArray(Flowers);


        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void printFromArray(ArrayList<Flower> FlowerArray) {
        for (Flower flower : FlowerArray) {
            flower.printClass();
            System.out.println(separator);
        }
    }

    public void parse(XMLStreamReader reader, int eventType) throws XMLStreamException {

        Flower flower = new Flower();
        while (reader.hasNext()) {
            eventType = reader.next();
            if (eventType == XMLEvent.START_ELEMENT) {
                switch (reader.getName().getLocalPart()) {
                    case "Flower":
                        flower = new Flower();
                        break;

                    case "stem_color":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setStem_color(reader.getText());
                        break;

                    case "leaf_color":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setLeaf_color(reader.getText());
                        break;

                    case "size":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setAverageSize(reader.getText());
                        break;

                    case "temperature":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setTemperature(Float.parseFloat(reader.getText()));
                        break;

                    case "lighting":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setLighting(Boolean.parseBoolean(reader.getText()));
                        break;

                    case "name":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setName(reader.getText());
                        break;

                    case "soil":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setSoil(reader.getText());
                        break;

                    case "origin":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setOrigin(reader.getText());
                        Flowers.add(flower);
                        break;

                    case "multiplying":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setMultiplying(reader.getText());
                        break;

                    case "flowerID":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setId(reader.getText());
                        break;

                    case "watering":
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS)
                            flower.setWatering(Float.parseFloat(reader.getText()));
                        break;
                }

            }
        }
    }
    /*

    void printDevicesComponents(){
        for(int i =0;i<this.device.size();i++){
            device.get(i).printComponent();
        }
    }*/

}
