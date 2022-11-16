package com.lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM {

    final static private String separator = "--------------------";

    public DOM(String path, String name) {
        try {
            // The document builder is created
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // The DOM tree of the document is created from the file
            Document document = documentBuilder.parse(path+".xml");
//            printFromXML(document.getDocumentElement(), name);
            ArrayList<Flower> FlowersArray = parseToClass_FlowersArray(document.getDocumentElement());
            Collections.sort(FlowersArray);
            printFromArray(FlowersArray);
            if (validateXMLSchema(path + ".xml", path + ".xsd")) {
                System.out.println("XML is valid");
            } else {
                System.out.println("XML is not valid");
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static Boolean validateXMLSchema(String xmlPath, String xsdPath) {
        try {
            File xml = new File(xmlPath);
            File xsd = new File(xsdPath);

            if (!xml.exists()) {
                System.out.println("XML file not found" + xmlPath);
            }

            if (!xsd.exists()) {
                System.out.println("XSD file not found" + xsdPath);
            }
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    public static void printFromArray(ArrayList<Flower> FlowerArray) {
        for (Flower flower : FlowerArray) {
            flower.printClass();
            System.out.println(separator);
        }
    }

    private static ArrayList<Flower> parseToClass_FlowersArray(Node root) {
        ArrayList<Flower> flowers = new ArrayList<>();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {
                Flower flower = parseToClass_Flower(node);
                flowers.add(flower);
            }
        }
        return flowers;
    }

    private static Flower parseToClass_Flower(Node root) {
        NodeList nodeList = root.getChildNodes();
        Flower flower = new Flower();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {
                switch (node.getNodeName()) {
                    case "flowerID":
                        flower.setId(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "name":
                        flower.setName(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "soil":
                        flower.setSoil(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "origin":
                        flower.setOrigin(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "visualParameters":
                        parseToClass_Visual_parameters(node, flower);
                        break;
                    case "growingTips":
                        parseToClass_Growing_tips(node, flower);
                        break;
                    case "multiplying":
                        flower.setMultiplying(node.getChildNodes().item(0).getTextContent());
                        break;
                }
            }
        }

        return flower;
    }

    private static void parseToClass_Visual_parameters(Node root, Flower flower) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {
                switch (node.getNodeName()) {
                    case "stem_color":
                        flower.setStem_color(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "leaf_color":
                        flower.setLeaf_color(node.getChildNodes().item(0).getTextContent());
                        break;
                    case "size":
                        flower.setAverageSize(node.getChildNodes().item(0).getTextContent());
                        break;
                }
            }
        }
    }

    private static void parseToClass_Growing_tips(Node root, Flower flower) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {
                switch (node.getNodeName()) {
                    case "temperature":
                        flower.setTemperature(Float.valueOf(node.getChildNodes().item(0).getTextContent()));
                        break;
                    case "is_photophilous":
                        flower.setLighting(Boolean.valueOf(node.getChildNodes().item(0).getTextContent()));
                        break;
                    case "watering":
                        flower.setWatering(Float.valueOf(node.getChildNodes().item(0).getTextContent()));
                        break;
                }
            }
        }
    }

    private static void printFromXML(Node root, String name){
        System.out.println(name);
        System.out.print(separator);
        recursionPrint(root, 0);
    }

    private static void recursionPrint(Node root, int level) {
        NodeList rootChildNodes = root.getChildNodes();
        if(rootChildNodes.getLength()>1){
            System.out.println();
            for (int i = 0; i < rootChildNodes.getLength(); i++)
                if (rootChildNodes.item(i).getNodeType() != Node.TEXT_NODE) {
                    for (int j = 0; j < level; j++)
                        System.out.print("\t");
                    System.out.print(rootChildNodes.item(i).getNodeName() + ":");
                    recursionPrint(rootChildNodes.item(i), level + 1);
                }
        } else
            System.out.println(root.getTextContent());
        if (level == 1)
            System.out.println(separator);
    }
}