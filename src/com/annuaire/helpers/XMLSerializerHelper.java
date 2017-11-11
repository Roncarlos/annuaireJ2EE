package com.annuaire.helpers;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

import com.annuaire.beans.*;


public class XMLSerializerHelper {
	
	public XMLSerializerHelper() {}
	
	public static void write(Annuaire f, String filename) throws Exception{
	        XMLEncoder encoder =
	           new XMLEncoder(
	              new BufferedOutputStream(
	                new FileOutputStream(filename)));
	        encoder.writeObject(f.getListOfPerson());
	        encoder.flush();
	        encoder.close();
	}

	public static ArrayList<Personne> read(String filename) throws Exception {
	        XMLDecoder decoder =
	            new XMLDecoder(new BufferedInputStream(
	                new FileInputStream(filename)));
	        ArrayList<Personne> o = (ArrayList<Personne>) decoder.readObject();
	        decoder.close();
	        return o;
	}

	
}
