
package problem1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Xml_Validation{  
  public static void main(String[] args) throws XMLStreamException, IOException {    
	    
	  	InputStreamReader r=new InputStreamReader(System.in);  
	   	BufferedReader b=new BufferedReader(r);  
	   	System.out.println("Enter your File Path");  
	   	String xmlFile=b.readLine();
	   	//String xmlFile = "D:/test1.xml";
	    
	   	XMLInputFactory factory = XMLInputFactory.newInstance();
	    XMLEventReader reader =factory.createXMLEventReader(new FileReader(xmlFile));
	    Stack<String> svalue=null;
	    
	     
	    while(reader.hasNext()){
	    		XMLEvent event =reader.nextEvent();
      
		   switch(event.getEventType()){
		   		case XMLStreamConstants.START_DOCUMENT:
		   			System.out.println("Start Document");
		   			svalue = new Stack<>();
		   			break;
		   		case XMLStreamConstants.START_ELEMENT:
		   			StartElement startElement = event.asStartElement();
		   			String startname = startElement.getName().getLocalPart();
		   			System.out.println(startname);
		   			svalue.push(startname);        
		   			break;
		   		
		   		case XMLStreamConstants.CHARACTERS:
		   			break;
		   			
		   		case XMLStreamConstants.END_ELEMENT:
		   			EndElement endElement = event.asEndElement();
		   			if(endElement.getName().getLocalPart().equals(svalue.peek())){
		   				System.out.println(endElement.getName().getLocalPart());
		   				svalue.pop();
		   			}
		   			break;
		   		case XMLStreamConstants.END_DOCUMENT:
		   			  System.out.print("End Document\n");
		          	  break;	
		   		}		    
	    	}
	    if(svalue.isEmpty())
	        System.out.print("valid");
	    else 
	    	System.out.print("invalid");
  }


}







