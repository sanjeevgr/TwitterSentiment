import java.io.*;
import java.util.*;

public class SentimentalAnalysis {
	public SentimentalAnalysis(String fileName) throws IOException {

		String STOP_WORDS = "stopWords.txt";
		String BAG_OF_WORDS = "wordWithStrength.txt";
		String tweet;

		try{
			// create stop-words
			ArrayList<String> stopWords= new ArrayList<String>();
			BufferedReader stop = new BufferedReader(new FileReader(STOP_WORDS));
			String line = "";
			while ((line = stop.readLine()) != null) {
				stopWords.add(line);
			}
			stop.close();
			//System.out.println(stopWords);


			// create lexicon
			HashMap<String, String> map = new HashMap<String, String>();
			BufferedReader in = new BufferedReader(new FileReader(BAG_OF_WORDS));
			line = in.readLine();
			while (line != null) {
				String parts[] = line.split("\t");
				map.put(parts[0], (parts[1]));
				line = in.readLine();
			}
			in.close();
			//System.out.println(map.toString());

			Scanner inputStream = new Scanner(new FileReader(fileName));
			while (inputStream.hasNextLine()) {
				float tweetscore = 0;
				tweet = inputStream.nextLine();
				String[] word = tweet.split(" ");



				for (int i = 0; i < word.length; i++) {
					if (!stopWords.contains(word[i].toLowerCase())) {
						if (map.get(word[i]) != null) {
							String wordscore = map.get(word[i].toLowerCase());
							tweetscore = (float) tweetscore + Float.parseFloat((wordscore));
						}
					}
				}
				Map < String, Float > sentiment = new HashMap < String, Float > ();
				sentiment.put(tweet, tweetscore);
				System.out.println(sentiment.toString()+"\n");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

        }
	}

	public static void main(String [] args)throws IOException{

		String[] states = {
			"Connecticut",
			"Delaware",
			"Maine",
			"Maryland",
			"Massachusetts",
			"NewHampshire",
			"NewJersey",
			"NewYork",
			"RhodeIsland",
			"Vermont",
		};

		for(int i=0; i<states.length; i++)
			new SentimentalAnalysis(states[i]+".csv");
	}
}