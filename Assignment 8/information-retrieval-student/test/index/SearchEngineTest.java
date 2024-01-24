/*
 * Copyright 2021 Marc Liberatore.
 */

package index;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


import documents.DocumentId;

public class SearchEngineTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private SearchEngine searchEngine;

	/*
	 * Text below taken from Wikipedia on 2016-11-02, used under CC BY-SA 3.0 Unported License.
	 */
	private static final String DOCUMENT1 = "this is a a sample";
	private static final DocumentId DOCUMENT1_ID = new DocumentId("DOCUMENT1");
	private static final String DOCUMENT2 = "this is another another example example example";
	private static final DocumentId DOCUMENT2_ID = new DocumentId("DOCUMENT2");
	private static final String DOCUMENT3 = "here is another sample example";
	private static final DocumentId DOCUMENT3_ID = new DocumentId("DOCUMENT3");
	private static final String PIZZA = "Pizza is a flatbread generally topped with tomato sauce and cheese and baked in an oven. It is commonly topped with a selection of meats, vegetables and condiments. The term was first recorded in the 10th century, in a Latin manuscript from Gaeta in Central Italy. The modern pizza was invented in Naples, Italy, and the dish and its variants have since become popular and common in many areas of the world.";
	private static final DocumentId PIZZA_ID = new DocumentId("PIZZA");
	private static final String SPAGHETTI = "Spaghetti (Italian pronunciation: [spaˈɡetti]) is a long, thin, cylindrical, solid pasta. It is a staple food of traditional Italian cuisine. Like other pasta, spaghetti is made of milled wheat and water. Italian spaghetti is made from durum wheat semolina, but elsewhere it may be made with other kinds of flour.";
	private static final DocumentId SPAGHETTI_ID = new DocumentId("SPAGHETTI");
	private static final String TOMATO_SAUCE = "Tomato sauce (also known as Neapolitan sauce, and referred to in Italy as Napoletana sauce), refers to any of a very large number of sauces made primarily from tomatoes, usually to be served as part of a dish (rather than as a condiment). Tomato sauces are common for meat and vegetables, but they are perhaps best known as sauces for pasta dishes.";
	private static final DocumentId TOMATO_SAUCE_ID = new DocumentId("TOMATO_SAUCE");
	private static final String RATATOUILLE = "Ratatouille (/ˌrætəˈtuːiː/ rat-ə-TOO-ee; French: [ʁatatuj]) is a French Provençal stewed vegetable dish, originating in Nice, and sometimes referred to as ratatouille niçoise. The word ratatouille derives from the Occitan ratatolha and is related to the French ratouiller and tatouiller, expressive forms of the verb touiller, meaning \"to stir up\". From the late 18th century, in French, it merely indicated a coarse stew. The modern ratatouille - tomatoes as a foundation for sautéed garlic, onions, zucchini, eggplant, bell peppers, marjoram, fennel and basil, or bay leaf and thyme, or a mix of green herbs like herbes de Provence - does not appear in print until c. 1930.";
	private static final DocumentId RATATOUILLE_ID = new DocumentId("RATATOUILLE");
	private static final String PAELLA = "Paella (Catalan pronunciation: [paˈeʎa] or [pəˈeʎə], Spanish: [paˈeʎa]; English approximation: /pɑːˈeɪlə, -ˈeɪljə, -ˈeɪjə, -ˈɛlə, -ˈjɛlə/ or /paɪˈɛlə/) is a Valencian rice dish with ancient roots that originated in its modern form in the mid-19th century near Albufera lagoon on the east coast of Spain adjacent to the city of Valencia. Many non-Spaniards view paella as Spain's national dish, but most Spaniards consider it to be a regional Valencian dish. Valencians, in turn, regard paella as one of their identifying symbols. Valencian paella is believed to be the original recipe and consists of white rice, green beans (bajoqueta and tavella), meat (chicken and rabbit), white beans (garrofón), snails, and seasoning such as saffron and rosemary. Another very common but seasonal ingredient is artichokes. Seafood paella replaces meat with seafood and omits beans and green vegetables. Mixed paella is a free-style combination of meat from land animals, seafood, vegetables, and sometimes beans. Most paella chefs use bomba rice due to it being harder to overcook, but Valencians tend to use a slightly stickier (and thus more susceptible to overcooking) variety known as Senia. All types of paellas use olive oil.";
	private static final DocumentId PAELLA_ID = new DocumentId("PAELLA");
	private static final String FRIED_RICE = "Fried rice (Chinese: 炒飯; pinyin: chǎo fàn) is a Chinese dish of steamed rice that has been stir-fried in a wok and, usually, mixed with other ingredients, such as eggs, vegetables, and meat, and as such, often served as a complete dish. It is sometimes served as the penultimate dish in Chinese banquets, just before dessert. As a homemade dish, fried rice is typically made with leftover ingredients (including vegetables and/or meat) from other dishes, leading to countless variations, being an economic hodgepodge like it is done with fried noodles or pyttipanna.";
	private static final DocumentId FRIED_RICE_ID = new DocumentId("FRIED_RICE");
	private static final String CHOW_MEIN = "Chow mein (/ˈtʃaʊ ˈmeɪn/) are stir-fried noodles, the name being the romanization of the Taishanese chāu-mèing. The dish is popular throughout the Chinese diaspora and appears on the menus of Chinese restaurants.In American Chinese cuisine, it is a stir-fried dish consisting of noodles, meat (chicken being most common but pork, beef, shrimp or tofu sometimes being substituted), onions and celery. It is often served as a specific dish at westernized Chinese restaurants. Vegetarian or vegan Chow Mein is also common.";
	private static final DocumentId CHOW_MEIN_ID = new DocumentId("CHOW_MEIN");
	private static final String WAT = "Wat, we̠t’, wot (Amharic: ወጥ?, IPA: [wətʼ]) or tsebhi (Tigrinya: ጸብሒ?, IPA: [sʼɐbħi]) is an Ethiopian and Eritrean stew or curry that may be prepared with chicken, beef, lamb, a variety of vegetables, spice mixtures such as berbere, and niter kibbeh, a seasoned clarified butter. Several properties distinguish wats from stews of other cultures. Perhaps the most obvious is an unusual cooking technique: the preparation of a wat begins with chopped onions slow cooked, without any fat or oil, in a dry skillet or pot until much of their moisture has been driven away. Fat (usually niter kibbeh) is then added, often in quantities that might seem excessive by modern Western standards, and the onions and other aromatics are sautéed before the addition of other ingredients. This method causes the onions to break down and thicken the stew. Wat is traditionally eaten with injera, a spongy flat bread made from the millet-like grain known as teff. There are many types of wats. The popular ones are doro wat and siga wat, (Ge'ez: ሥጋ śigā) made with beef.";
	private static final DocumentId WAT_ID = new DocumentId("WAT");

	@Before
	public void setup() {
		searchEngine = new SearchEngine();		
	}
	
	public void addFood() throws IOException {
		searchEngine.addDocument(PIZZA_ID, new StringReader(PIZZA));
		searchEngine.addDocument(SPAGHETTI_ID, new StringReader(SPAGHETTI));
		searchEngine.addDocument(TOMATO_SAUCE_ID, new StringReader(TOMATO_SAUCE));
		searchEngine.addDocument(RATATOUILLE_ID, new StringReader(RATATOUILLE));
		searchEngine.addDocument(PAELLA_ID, new StringReader(PAELLA));
		searchEngine.addDocument(FRIED_RICE_ID, new StringReader(FRIED_RICE));
		searchEngine.addDocument(CHOW_MEIN_ID, new StringReader(CHOW_MEIN));
		searchEngine.addDocument(WAT_ID, new StringReader(WAT));
	}
	
	@Test
	public void testTrivialAdd() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
	}

	@Test
	public void testEmptyIndex() throws Exception {
		assertEquals(new HashSet<DocumentId>(), searchEngine.indexLookup("foo"));
	}
	
	@Test
	public void testNotInIndex() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		assertEquals(new HashSet<DocumentId>(), searchEngine.indexLookup("foo"));
	}

	@Test
	public void testInIndex() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		HashSet<DocumentId> result = new HashSet<>();
		result.add(DOCUMENT1_ID);
		assertEquals(result, searchEngine.indexLookup("sample"));
	}
	
	@Test
	public void testMultipleInIndex() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		HashSet<DocumentId> result = new HashSet<>();
		result.add(DOCUMENT1_ID);
		result.add(DOCUMENT2_ID);
		assertEquals(result, searchEngine.indexLookup("this"));
	}

	@Test
	public void testAddCaseInsensitivity() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1.toUpperCase()));
		HashSet<DocumentId> result = new HashSet<>();
		result.add(DOCUMENT1_ID);
		assertEquals(result, searchEngine.indexLookup("sample"));
	}

	@Test
	public void testIndexLookupCaseInsensitivity() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		HashSet<DocumentId> result = new HashSet<>();
		result.add(DOCUMENT1_ID);
		assertEquals(result, searchEngine.indexLookup("SAMPLE"));
	}
	
	@Test
	public void testAddTokenizing() throws Exception {
		searchEngine.addDocument(PIZZA_ID, new StringReader(PIZZA));
		HashSet<DocumentId> result = new HashSet<>();
		result.add(PIZZA_ID);
		assertEquals(result, searchEngine.indexLookup("oven"));
		assertEquals(new HashSet<>(), searchEngine.indexLookup("oven."));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTermFrequencyIllegalArgument() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.termFrequency(DOCUMENT2_ID, "this");
	}

	@Test
	public void testTermFrequencyZero() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		assertEquals(0, searchEngine.termFrequency(DOCUMENT1_ID, "foo"));
	}

	@Test
	public void testTermFrequencyOne() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		assertEquals(1, searchEngine.termFrequency(DOCUMENT1_ID, "sample"));
	}
	
	@Test
	public void testTermFrequencyN() throws Exception {
		searchEngine.addDocument(PIZZA_ID, new StringReader(PIZZA));
		assertEquals(6, searchEngine.termFrequency(PIZZA_ID, "and"));
	}

	@Test
	public void testInverseTermFrequencyZero() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		assertEquals(Math.log(2), searchEngine.inverseDocumentFrequency("foo"), 0.0);
	}

	@Test
	public void testInverseTermFrequencyOne() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		assertEquals(0.0, searchEngine.inverseDocumentFrequency("sample"), 0.0);
	}

	@Test
	public void testInverseTermFrequencyZeroOfTwo() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		assertEquals(Math.log(3.0), searchEngine.inverseDocumentFrequency("foo"), 0.0);
	}
	
	@Test
	public void testInverseTermFrequencyOneOfTwo() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		assertEquals(Math.log(3.0 / 2.0), searchEngine.inverseDocumentFrequency("sample"), 0.0);
	}

	@Test
	public void testInverseTermFrequencyTwoOfTwo() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		assertEquals(0.0, searchEngine.inverseDocumentFrequency("this"), 0.0);
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void testTfIdfIllegalArgument() throws Exception {
		searchEngine.tfIdf(DOCUMENT1_ID, "this");
	}
	
	@Test
	public void testTfIdfOneOfTwo() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		assertEquals(Math.log(3.0 / 2.0), searchEngine.tfIdf(DOCUMENT1_ID, "sample"), 0.0);
	}
		
	@Test
	public void testTfIdfMultipleOne() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		searchEngine.addDocument(DOCUMENT3_ID, new StringReader(DOCUMENT3));
		assertEquals(2 * Math.log(4.0 / 2.0), searchEngine.tfIdf(DOCUMENT1_ID, "a"), 0.0);
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT2_ID, "a"), 0.0);
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT3_ID, "a"), 0.0);
	}

	@Test
	public void testTfIdfMultipleTwo() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		searchEngine.addDocument(DOCUMENT3_ID, new StringReader(DOCUMENT3));
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT1_ID, "another"), 0.0);
		assertEquals(2 * Math.log(4.0 / 3.0), searchEngine.tfIdf(DOCUMENT2_ID, "another"), 0.0);
		assertEquals(Math.log(4.0 / 3.0), searchEngine.tfIdf(DOCUMENT3_ID, "another"), 0.0);
	}
	
	@Test
	public void testTfIdfMultipleThree() throws Exception {
		searchEngine.addDocument(DOCUMENT1_ID, new StringReader(DOCUMENT1));
		searchEngine.addDocument(DOCUMENT2_ID, new StringReader(DOCUMENT2));
		searchEngine.addDocument(DOCUMENT3_ID, new StringReader(DOCUMENT3));
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT1_ID, "is"), 0.0);
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT2_ID, "is"), 0.0);
		assertEquals(0.0, searchEngine.tfIdf(DOCUMENT3_ID, "is"), 0.0);
	}
	
	@Test
	public void testPizzaTfIdf() throws Exception {
		addFood();
		assertEquals(3.0082, searchEngine.tfIdf(PIZZA_ID, "pizza"), 0.0001);
	}

	@Test
	public void testNoPizzaTfIdf() throws Exception {
		addFood();
		assertEquals(0.0, searchEngine.tfIdf(SPAGHETTI_ID, "pizza"), 0.0);
	}
	
	@Test
	public void testTomatoTfIdf() throws Exception {
		addFood();
		assertEquals(1.0986, searchEngine.tfIdf(PIZZA_ID, "tomato"), 0.0001);
		assertEquals(2.1972, searchEngine.tfIdf(TOMATO_SAUCE_ID, "tomato"), 0.0001);
	}

	@Test
	public void testTomatoRelevance() throws Exception {
		addFood();
		assertEquals(Arrays.asList(TOMATO_SAUCE_ID, PIZZA_ID), searchEngine.relevanceLookup("tomato"));
	}
	
	@Test
	public void testMeatRelevanceLookup() throws Exception {
		addFood();
		assertEquals(Arrays.asList(PAELLA_ID, FRIED_RICE_ID, CHOW_MEIN_ID, TOMATO_SAUCE_ID), 
				searchEngine.relevanceLookup("meat"));
	}
	
	@Test
	public void testPastaRelevanceLookup() throws Exception {
		addFood();
		assertEquals(Arrays.asList(SPAGHETTI_ID, TOMATO_SAUCE_ID), 
				searchEngine.relevanceLookup("pasta"));
	}

	@Test
	public void testBeefRelevanceLookup() throws Exception {
		addFood();
		assertEquals(Arrays.asList(WAT_ID, CHOW_MEIN_ID), 
				searchEngine.relevanceLookup("beef"));
	}
	
	@Test
	public void testIrrelevantLookup() throws Exception {
		addFood();
		assertEquals(Arrays.asList(),
				searchEngine.relevanceLookup("foobar"));
	}
}