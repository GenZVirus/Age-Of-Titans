package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLFileJava {
	
	 public static String defoult_xmlFilePath = "world/ageoftitans/playerdata/";
	    public static String Player_Name = "PlayerName";
	    
	    public XMLFileJava(UUID UniquePlayerName, String playerName) {
	    	String xmlFilePath = defoult_xmlFilePath + UniquePlayerName.toString() + ".xml";
	    	Player_Name = playerName;
	    	 try {
	    		 
	         	File file = new File(xmlFilePath);
	 		      File parent = file.getParentFile();
	 		      if (!parent.exists() && !parent.mkdirs()) {
	 		          throw new IllegalStateException("Couldn't create dir: " + parent);
	 		      }
	         	
	             DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	  
	             DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	  
	             Document document = documentBuilder.newDocument();
	  
	             // player name element
	             Element playername = document.createElement("PlayerName");
	             playername.appendChild(document.createTextNode(Player_Name));
	             document.appendChild(playername);
	             
	             // player name element
	             Element playerlevel = document.createElement("PlayerLevel");
	             playerlevel.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(playerlevel);
	             
	             // player experience element
	             Element playerexp = document.createElement("PlayerExp");
	             playerexp.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(playerexp);
	             
	             // slot1 element
	             Element slot1 = document.createElement("Slot1_Spell_ID");
	             slot1.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(slot1);

	             // slot2 element
	             Element slot2 = document.createElement("Slot2_Spell_ID");
	             slot2.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(slot2);

	             // slot3 element
	             Element slot3 = document.createElement("Slot3_Spell_ID");
	             slot3.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(slot3);

	             // slot4 element
	             Element slot4 = document.createElement("Slot4_Spell_ID");
	             slot4.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(slot4);
	             
	             // spell1 element
	             Element Spell1 = document.createElement("Spell1_Level");
	             Spell1.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(Spell1);
	             
	        	 // spell2 element
	             Element Spell2 = document.createElement("Spell2_Level");
	             Spell2.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(Spell2);
	             
	        	 // spell3 element
	             Element Spell3 = document.createElement("Spell3_Level");
	             Spell3.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(Spell3);
	             
	        	 // spell4 element
	             Element Spell4 = document.createElement("Spell4_Level");
	             Spell4.appendChild(document.createTextNode(Integer.toString(0)));
	             playername.appendChild(Spell4);
	  
	             // create the xml file
	             //transform the DOM Object to an XML File
	             TransformerFactory transformerFactory = TransformerFactory.newInstance();
	             Transformer transformer = transformerFactory.newTransformer();
	             DOMSource domSource = new DOMSource(document);
	             StreamResult streamResult = new StreamResult(new File(xmlFilePath));
	  
	             // If you use
	             // StreamResult result = new StreamResult(System.out);
	             // the output will be pushed to the standard output ...
	             // You can use that for debugging 
	  
	             transformer.transform(domSource, streamResult);
	  
	             System.out.println("Done creating XML File");
	  
	         } catch (ParserConfigurationException pce) {
	             pce.printStackTrace();
	         } catch (TransformerException tfe) {
	             tfe.printStackTrace();
	         }
	    }
	    
	    public static void editElement(UUID UniquePlayerName, String elementTag, String elementTextContent) {
	    	String xmlFilePath = defoult_xmlFilePath + UniquePlayerName.toString() + ".xml";
	    	try {
	    	 DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	         Document document = documentBuilder.parse(xmlFilePath);
	         
	         // Get root element
	         Node PlayerName = document.getFirstChild();
	         
	         Node element = document.getElementsByTagName(elementTag).item(0);
	         element.setTextContent(elementTextContent);
	      // write the content into xml file
	 		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	 		Transformer transformer = transformerFactory.newTransformer();
	 		DOMSource source = new DOMSource(document);
	 		StreamResult result = new StreamResult(new File(xmlFilePath));
	 		transformer.transform(source, result);
	 		
	    	} catch (ParserConfigurationException pce) {
	    		pce.printStackTrace();
	    	} catch (TransformerException tfe) {
	    			tfe.printStackTrace();
	 	   } catch (IOException ioe) {
	 		ioe.printStackTrace();
	 	   } catch (SAXException sae) {
	 		sae.printStackTrace();
	 	   }
	    }
	    
	    public static String readElement(UUID UniquePlayerName, String elementTag) {
	    	String xmlFilePath = defoult_xmlFilePath + UniquePlayerName.toString() + ".xml";
	    	try {
	    	 DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	         Document document = documentBuilder.parse(xmlFilePath);
	         
	         Node element = document.getElementsByTagName(elementTag).item(0);
	         return element.getTextContent();
	    	} catch (ParserConfigurationException pce) {
	    		pce.printStackTrace();
	 	   } catch (IOException ioe) {
	 		ioe.printStackTrace();
	 	   } catch (SAXException sae) {
	 		sae.printStackTrace();
	 	   }
	    	return "0";
	    }
	    
	    public static void checkFileAndMake(UUID UniquePlayerName, String playerName) {
	    	String xmlFilePath = defoult_xmlFilePath + UniquePlayerName.toString() + ".xml";
	    	File file = new File(xmlFilePath);
	    	boolean found = file.exists();
	    	
	    	if(!found) {
	    		new XMLFileJava(UniquePlayerName, playerName);
	    	}
			
	    }
	
}
