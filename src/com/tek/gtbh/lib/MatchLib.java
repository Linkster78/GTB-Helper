package com.tek.gtbh.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class MatchLib {
	
	static ArrayList<String> words = new ArrayList<String>();
	
	public static void loadDictionary() {
		words.clear();
		
		String surl = "https://raw.githubusercontent.com/RedstoneTek/GTB-Helper/master/dictionary.txt";
		
		try {
			URL url = new URL(surl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			String line;
			while((line = bufferedReader.readLine()) != null) {
				words.add(line);
			}
			
			bufferedReader.close();
		}catch(Exception e) { }
		
		System.out.println("Loaded " + words.size() + " words");
	}
	
	public static ArrayList<String> findMatches(char ignored, String query) {
		if(words.isEmpty()) return null;
		
		ArrayList<String> matches = new ArrayList<String>();
		
		int length = query.length();
		
		for(String word : words) {
			if(word.length() != length) continue;
			
			CharacterIterator queryIterator = new StringCharacterIterator(query);
			CharacterIterator wordIterator = new StringCharacterIterator(word);
			 
			int result = 1;
			int i = 0;
			
			while(true) {
				char queryChar = 0;
				char wordChar = 0;
				
				if(i == 0) {
					queryChar = Character.toUpperCase(queryIterator.current());
					wordChar = Character.toUpperCase(wordIterator.current());
				}else {
					queryChar = Character.toUpperCase(queryIterator.next());
					wordChar = Character.toUpperCase(wordIterator.next());
				}
				
				if(queryChar != wordChar && queryChar != ignored) result = 0;
				
				if(queryChar == CharacterIterator.DONE) break;
				
				i++;
			}
			
			if(result != 0) {
				matches.add(word.toLowerCase());
			}
		}
		
		if(matches.isEmpty()) return null;
		return matches;
	}
	
}
