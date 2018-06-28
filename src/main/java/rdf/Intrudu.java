package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

/**
 * Created by JackNiu on 2018/6/28.
 */
public class Intrudu {
    static String personURI    = "http://somewhere/JohnSmith";
    static String fullName     = "John Smith";
    public static void main(String[] args) {
        // some definitions


    // create an empty Model
        Model model = ModelFactory.createDefaultModel();

    // create the resource
        Resource johnSmith = model.createResource(personURI);

    // add the property
        johnSmith.addProperty(VCARD.FN, fullName);

        System.out.println(johnSmith);
    }
}
