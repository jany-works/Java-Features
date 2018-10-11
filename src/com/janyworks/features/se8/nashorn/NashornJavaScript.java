/**
 * 
 */
package com.janyworks.features.se8.nashorn;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn JS Engine can be run from command line, the cmd is jjs
 * 
 * The JS can create and refer to Java Class and instances
 * 
 * @author Jany
 *
 */
public class NashornJavaScript {

	public static void main(String[] args) {
		learnNashornJavaScript();
		learnNashornJavaScriptFromFile();
		learnReadRSSFeed();
	}
	
	public static void learnNashornJavaScript() {
		System.out.println("learnNashornJavaScript");
		
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
		
		String script = "var message='Hello Welcome to Java JS';"
				+ "message = message+' John';"
				+ "message;";
		
		try {
			Object result = scriptEngine.eval(script);
			System.out.println(result);
		} catch (ScriptException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void learnNashornJavaScriptFromFile() {
		System.out.println("learnNashornJavaScriptFromFile");
		
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
		
		File scriptFile = new File("scripts/NashorJSExample.js");
		try (Reader reader = new FileReader(scriptFile)){
			
			Object result = scriptEngine.eval(reader);
			System.out.println(result);
		} catch (ScriptException | IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void learnReadRSSFeed() {
		System.out.println("learnReadRSSFeed");
		
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
		
		File scriptFile = new File("scripts/ViewRSSFeed.js");
		try (Reader reader = new FileReader(scriptFile)){
			
			Object result = scriptEngine.eval(reader);
			System.out.println(result);
		} catch (ScriptException | IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
