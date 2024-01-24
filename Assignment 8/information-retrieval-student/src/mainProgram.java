import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import documents.DocumentId;
import index.SearchEngine;

public class mainProgram {
    public static void main(String[] args) throws IOException{
        String DOCUMENT1 = "this is a a sample";
	    DocumentId DOCUMENT1_ID = new DocumentId("DOCUMENT1");
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		System.out.println(Math.log(2));
        System.out.println(searchEngine.inverseDocumentFrequency("foo"));






    }    
}
