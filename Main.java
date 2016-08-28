import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException{
    	
    	InputStreamReader r=new InputStreamReader(System.in);  
    	BufferedReader b=new BufferedReader(r);  
    	  
    	System.out.println("Enter your File Path");  
    	String csvFile=b.readLine();  
    	BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            //reading the first line for getting the tags
            int counter = 1;
            String [] headers = new String[0];
            while(counter!=0 && (line = br.readLine())!=null){
                headers = line.split(cvsSplitBy);
                counter--;
            }
            WriteToFile("<root>");
           
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                WriteToFile("	");
                WriteToFile("<root-row>");
                for(int i=0;i<country.length;i++){
                	
                    staxParser(headers[i%headers.length],country[i]);
                    
                	}
                WriteToFile("</root-row");
            }
            WriteToFile("</root>");
        	} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void staxParser(String header,String value){
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory factory      = XMLOutputFactory.newInstance();
        
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(stringWriter);
            writer.writeStartElement(header);
            writer.writeCharacters(value);
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            String xmlString = stringWriter.getBuffer().toString();
            stringWriter.close();
            WriteToFile(xmlString);
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public static void WriteToFile(String content){
    try{
        File f=new File("D:\\csv2xml.xml");
        if(!f.exists()){
            f.createNewFile();
        }
        else{
            FileWriter fw=new FileWriter(f.getAbsoluteFile(), true);
            BufferedWriter bw=new BufferedWriter(fw);
            
           	bw.write(content);
           
            bw.newLine();
            bw.close();
            System.out.println("Done");
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}
