package com.lab2;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
            Document document = documentBuilder.parse(path);
            Node root = document.getDocumentElement();
            System.out.println(name);
            System.out.print(separator);
            recursionPrint(root, 0);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
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