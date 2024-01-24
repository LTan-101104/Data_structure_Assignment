/*
 * Copyright 2023 Marc Liberatore.
 */

package index;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased versions of each word 
 * in the document. 
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms are 
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s) to a specific term 
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)> 
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {

	Map<String, Set<DocumentId>> storage;
	Map<DocumentId, Map<String, Integer>> docToTermsFrequency;

	public SearchEngine(){
		this.storage = new HashMap<>();
		this.docToTermsFrequency = new HashMap<>();
	}
	
	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert the same 
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document contents for 
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException iff the reader throws an exception 
	 */
	public void addDocument(DocumentId documentId, Reader reader) throws IOException {
		// int temp;
		// Set<DocumentId> termSet;
		// String tempTerm = "";
		// while ((temp = reader.read()) != -1){
		// 	//! not sure if this is correct, Marc recommended Scanner but have not idea how to use it
		// 	if (((char) temp) == ' '){
		// 		termSet = this.storage.getOrDefault(tempTerm, new HashSet<>());
		// 		termSet.add(documentId);
		// 		this.storage.put(tempTerm, termSet);
		// 		tempTerm = "";
		// 		continue;
		// 	}

		// 	tempTerm += temp;
		

		Scanner readString = new Scanner(reader);
		readString.useDelimiter("\\W+");

		while (readString.hasNext()){
			String term = readString.next().toLowerCase();
			//add the term and document ID to set for index lookup
			Set<DocumentId> termSet = this.storage.getOrDefault(term, new HashSet<>());
			termSet.add(documentId);
			this.storage.put(term, termSet);

			//add term frequency
			if(!(this.docToTermsFrequency.containsKey(documentId))) 
				this.docToTermsFrequency.put(documentId, new HashMap<>());

			int curCount = this.docToTermsFrequency.get(documentId).getOrDefault(term, 0);
			curCount ++;
			this.docToTermsFrequency.get(documentId).put(term, curCount);
		}

		readString.close();



		

	}
	
	/**
	 * Returns the set of DocumentIds contained within the search engine that contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) {
		term = term.toLowerCase();
		return this.storage.getOrDefault(term, new HashSet<>());
	}
	
	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
		int res;

		if (this.docToTermsFrequency.get(documentId) == null) throw new IllegalArgumentException();

		res = this.docToTermsFrequency.get(documentId).getOrDefault(term, 0);

		return res;
	}
	
	/**
	 * Returns the inverse document frequency of a term across all documents in the index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where 
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term 
	 */
	public double inverseDocumentFrequency(String term) {
		int N = this.docToTermsFrequency.size();
		int M = this.indexLookup(term).size();
		double res = Math.log((double) (1 + N ) / (double) (1 + M));
		return res;
	}
	
	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
		double res;
		res = (double) this.termFrequency(documentId, term) * this.inverseDocumentFrequency(term);
		return res;
	}
	
	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term) {
		List<DocumentId> res = new ArrayList<>(this.storage.getOrDefault(term, new HashSet<>()));

		Collections.sort(res, new TfIdfComparator(this, term));


		return res;
	}
}
