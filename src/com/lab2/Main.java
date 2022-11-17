package com.lab2;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        DOM dom = new DOM("src/com/lab2/Flowers", "List of flowers:");
       // SAX sax = new SAX("src/com/lab2/Flowers", "List of flowers:");
        StAX stax = new StAX("src/com/lab2/Flowers", "List of flowers:");
        printIsValidate("src/com/lab2/Flowers");

    }

    public static void printIsValidate(String path){
        if (validateXMLSchema(path + ".xml", path + ".xsd")) {
            System.out.println("XML is valid");
        } else {
            System.out.println("XML is not valid");
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
}
