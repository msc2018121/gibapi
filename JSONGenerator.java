package com.msc.gib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONGenerator {

	static GsonBuilder gsonBuilder = new GsonBuilder();
	static Gson gson = gsonBuilder.create();
	
	public static void main(String[] args) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("file1.json");
			bw = new BufferedWriter(fw);
			bw.write(getGson2());
			bw.flush();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally {
			
			try {
				fw.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static String getGson() {
		List<JSO2> jso2s = new ArrayList<>();
		for(int i=0; i<10000; i++) {
			JSO2 jso2 = new JSO2();
			jso2.setName1("name1");
			jso2.setName2("name2");
			jso2.setName3("name3");
			jso2.setName4("name4");
			jso2.setName5("name5");
			jso2.setName6("name6");
			jso2.setName7("name7");
			jso2.setName8("name8");
			jso2.setName9("name9");
			jso2.setName10("name10");
			jso2s.add(jso2);
		}
		return gson.toJson(jso2s);
	}
	
	private static String getGson2() {
		List<JSO2> jso2s = new ArrayList<>();
		for(int i=0; i<10000; i++) {
			JSO2 jso2 = new JSO2();
			jso2.setName1("name1");
			jso2.setName2("name2");
			jso2.setName3("name3");
			jso2.setName4("name4");
			jso2.setName5("name5");
			jso2.setName6("name6");
			jso2.setName7("name7");
			jso2.setName8("name8");
			jso2.setName9("name9");
			jso2.setName10("name10");
			jso2s.add(jso2);
		}
		Top top = new Top();
		top.setJso2s(jso2s);
		return gson.toJson(top);
	}
}
