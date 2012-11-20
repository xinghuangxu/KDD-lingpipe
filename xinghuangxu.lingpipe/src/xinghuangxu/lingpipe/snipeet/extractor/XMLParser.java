package xinghuangxu.lingpipe.snipeet.extractor;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import xinghuangxu.lingpipe.snipeet.extractor.util.NodeWalker;

public class XMLParser {

	public static Review getReviews(File[] reviewFiles) throws IOException,
			ParserConfigurationException, org.xml.sax.SAXException {
		// Create a factory object for creating DOM parsers and configure it
		// DocumentBuilderFactory factory =
		// DocumentBuilderFactory.newInstance();
		// factory.setIgnoringComments(true); // We want to ignore comments
		// factory.setCoalescing(true); // Convert CDATA to Text nodes
		// factory.setNamespaceAware(false); // No namespaces: this is default
		// factory.setValidating(false); // Don't validate DTD: also default
		// // Now use the factory to create a DOM parser, a.k.a. DocumentBuilder
		// DocumentBuilder parser = factory.newDocumentBuilder();
		// Parse the file and build a Document tree to represent its content
		Document document = getDOM(reviewFiles[0]);
		// Ask the document for a list of all <sect1> elements it contains

		// NodeWalker walker=new NodeWalker(document);
		// while(walker.hasNext()){
		// Node currentNode= walker.nextNode();
		// String nodeName=currentNode.getNodeName();
		// short nodeType = currentNode.getNodeType();
		// System.out.println(nodeName+nodeType);
		// }

		//
		Review review = new Review();
		NodeList sections = document.getElementsByTagName("Review");
		// Loop through those <sect1> elements one at a time
		int numSections = sections.getLength();
		for (int i = 0; i < numSections; i++) {
			Node reviewNode = sections.item(i);
			Node reviewText = reviewNode.getFirstChild();
			while (reviewText != null
					&& reviewText.getNodeType() != Node.TEXT_NODE) {
				reviewText = reviewText.getNextSibling();
			}
			if (reviewText != null) {
				review.add(reviewText.getNodeValue().toLowerCase()
						.replaceAll("\\\n", " ").replace(" +", " "));
			}
			// Element section = (Element) sections.item(i); // A <sect1>
			// // The first Element child of each <sect1> should be a <title>
			// // element, but there may be some whitespace Text nodes first, so
			// // loop through the children until you find the first element
			// // child.
			// Node title = section.getFirstChild();
			// while (title != null && title.getNodeType() != Node.ELEMENT_NODE)
			// title = title.getNextSibling();
			// // Print the text contained in the Text node child of this
			// element
			// if (title != null)
			// System.out.println(title.getFirstChild().getNodeValue());
		}
		return review;
	}

	public static Document getDOM(File file)
			throws ParserConfigurationException, SAXException, IOException {
		// Create a factory object for creating DOM parsers and configure it
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true); // We want to ignore comments
		factory.setCoalescing(true); // Convert CDATA to Text nodes
		factory.setNamespaceAware(false); // No namespaces: this is default
		factory.setValidating(false); // Don't validate DTD: also default
		// Now use the factory to create a DOM parser, a.k.a. DocumentBuilder
		DocumentBuilder parser = factory.newDocumentBuilder();
		// Parse the file and build a Document tree to represent its content
		Document document = parser.parse(file);
		return document;
	}

	public static AspDictionary getAspectsDictionary(File[] aspFiles)
			throws ParserConfigurationException, SAXException, IOException {

		AspDictionary aspDictionary = new AspDictionary();
		Document document = getDOM(aspFiles[0]);
		// Node root=document.getChildNodes().item(0);
		// NodeList aspects=root.getChildNodes();
		// for(int i=0;i<aspects.getLength();i++){
		// Node aspect=aspects.item(i);
		// System.out.println("name: "+aspect.getNodeName());
		// System.out.println("value: "+aspect.getNodeValue());
		// }

		NodeWalker walker = new NodeWalker(document);
		String nodeName = "";
		while (walker.hasNext()) {
			Node currentNode = walker.nextNode();
			short nodeType = currentNode.getNodeType();
			if (nodeType != Node.TEXT_NODE)
				nodeName = currentNode.getNodeName();
			if (nodeType == Node.TEXT_NODE&&nodeName!=null) {
				String nodeValue = currentNode.getNodeValue();

				System.out.println("Name: " + nodeName + "|| Value: "
						+ nodeValue + "|| Type: " + nodeType);
				aspDictionary.add(nodeName, nodeValue.toLowerCase());
				nodeName = null;
			}
		}
		return aspDictionary;
	}

}
