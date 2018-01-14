package com.tek.gtbh;

import com.tek.gtbh.lib.MatchLib;

public class Launcher {
	
	public static void main(String[] args) {
		MatchLib.loadDictionary();
		
		new Window();
	}
	
}
