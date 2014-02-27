package org.orphanware.j4vim;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class MethodCompleteTest extends TestCase {


	public MethodCompleteTest(String testName) {

		super(testName);

	}


	public static Test suite() {
		return new TestSuite( MethodCompleteTest.class );
	}

	public void testGetMethodsForVarByPrefixNoGeneric(){

		MethodComplete mc = new MethodComplete("import java.util.LinkedList; LinkedList a = new LinkedList();");
		String out = mc.getMethodsForVarByPrefix("a", "");
		System.out.println(out);
		assertNotSame("", out);


	}


	public void testGetMethodsForVarByPrefixWithGeneric(){

		MethodComplete mc = new MethodComplete("import java.util.LinkedList; LinkedList<String, String> a = new LinkedList<>();");
		String out = mc.getMethodsForVarByPrefix("a", "");
		System.out.println(out);
		assertNotSame("", out);


	}






}
