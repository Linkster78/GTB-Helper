package com.tek.gtbh.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class MatchLib {
	
	static ArrayList<String> words = new ArrayList<String>();
	
	public static void loadDictionary() {
		InputStream dictionaryStream = MatchLib.class.getClassLoader().getResourceAsStream("dictionary.txt");
		BufferedReader dictionaryReader = new BufferedReader(new InputStreamReader(dictionaryStream));
		
		String line;
		try {
			while((line = dictionaryReader.readLine()) != null) {
				words.add(line.replace(System.lineSeparator(), "").replace("-", " "));
			}
		} catch (IOException e1) { }
		
		try {
			dictionaryReader.close();
			dictionaryStream.close();
		} catch (IOException e) { }
		
		System.out.println("Loaded dictionary with size " + words.size());
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
