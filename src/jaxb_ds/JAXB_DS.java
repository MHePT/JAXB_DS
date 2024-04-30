package JAXB_DS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import static JAXB_DS.Pre_In_Post.*;

public class JAXB_DS {

    public static void main(String[] args) throws JAXBException {

        try {
            //Open the file if it exists
            FileReader reader = new FileReader("src\\JAXB_DS\\New Text Document.txt");
            //Identifing Which class is the XML Class
            JAXBContext jaxbContext = JAXBContext.newInstance(Expression.class);
            //Creating the reader that will extract the XML Content from the file
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //Extracting the content of XML and converting it to Object
            Expression e = (Expression) jaxbUnmarshaller.unmarshal(reader);

            String exp = "" + e;  //String exp = "+*3+54*98";
            System.out.println("Expr. : " + exp);
            
            System.out.println("Infix : " + convertPrefixToInfix(exp));
            System.out.println("Value : " + evaluatePostfix(convertPrefixToPostfix(exp)));
        } catch (FileNotFoundException ex) {
            
            //if there is the file not found print the error details
            System.out.println("File Not Found: "+ex.getMessage());
            // print more detils about what happened
            ex.printStackTrace();
            
        }catch(JAXBException ex){
            
            //if there is the XML File Corrupted print the error details
            System.out.println("XML File is not good: "+ex.getMessage());
            // print more detils about what happened
            ex.printStackTrace();
            
        }catch(Exception ex){
            //if there is other error print the error details
            System.out.println("Something bad happened: "+ex.getMessage());
            // print more detils about what happened
            ex.printStackTrace();
            
        }
    }

}
