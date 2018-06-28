package rdf;

import com.sun.xml.internal.fastinfoset.sax.Properties;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

/**
 * Created by JackNiu on 2018/6/28.
 */
public class Tutorial03 {
    public static void main(String[] args) {
        // some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String givenName    = "John";
        String familyName   = "Smith";
        String fullName     = givenName + " " + familyName;

        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith = model.createResource(personURI).addProperty(VCARD.FN,fullName)
                .addProperty(VCARD.N,model.createResource().addProperty(VCARD.Given,givenName).addProperty(VCARD.Family,familyName));



        StmtIterator iter=model.listStatements();
        while(iter.hasNext()){
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode  object =stmt.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }

        model.write(System.out);
    }
}
