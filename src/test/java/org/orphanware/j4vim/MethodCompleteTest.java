package org.orphanware.j4vim;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;


public class MethodCompleteTest extends TestCase {


	public MethodCompleteTest(String testName) {

		super(testName);

	}


	public static Test suite() {
		return new TestSuite( MethodCompleteTest.class );
	}

	public void testGetMethodsForVarByPrefixNoGeneric(){

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("MethodTestNoGen.java"), "utf-8"));
			writer.write("import java.util.LinkedList; LinkedList a = new LinkedList();");
		} catch (IOException ex) {
		} finally {
			try {writer.close();} catch (Exception ex) {}
		}

		MethodComplete mc = new MethodComplete("MethodTestNoGen.java");
		String out = mc.getMethodsForVarByPrefix("a", "");
		System.out.println(out);
		File file = new File("MethodTestNoGen.java");
		file.delete();
		assertNotSame("", out);

	}


	public void testGetMethodsForVarByPrefixWithGeneric(){

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("MethodTestGen.java"), "utf-8"));
			writer.write("import java.util.LinkedList; LinkedList<String, String> a = new LinkedList<>();");
		} catch (IOException ex) {
		} finally {
			try {writer.close();} catch (Exception ex) {}
		}

		MethodComplete mc = new MethodComplete("MethodTestGen.java");
		String out = mc.getMethodsForVarByPrefix("a", "");
		System.out.println(out);
		File file = new File("MethodTestGen.java");
		file.delete();
		assertNotSame("", out);

	}






}
