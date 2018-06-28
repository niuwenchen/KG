package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

/**
 * Created by JackNiu on 2018/6/28.
 */
public class Tutorial06 {
    static final String inputFileName  = "vc-db-1.rdf";
    static final String JohnSmith = "http://somewhere/JohnSmith/";

    public static void main (String args[]) {
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        Resource vcard = model.getResource(JohnSmith);
        System.out.println(vcard.getProperty(VCARD.FN));  // 返回的是一个RDF
        System.out.println(vcard.getProperty(VCARD.FN).getObject());  // 返回的是最后的宾语
//        System.out.println(vcard);


        String fullName = vcard.getProperty(VCARD.FN)   // 因为一般意义上的object 都是 一个 字符串。
                .getString();
        System.out.println(fullName);

        vcard.addProperty(VCARD.NICKNAME,"Smithy")
                .addProperty(VCARD.NICKNAME,"Adman");

         StmtIterator iter =vcard.listProperties(VCARD.NICKNAME);
        while(iter.hasNext())
        {
            System.out.println("   "+ iter.nextStatement().getObject().toString());
        }


//        model.write(System.out);
//        System.out.println();
//        // write it to standard out
//        model.write(System.out,"N-TRIPLES");
    }
}
