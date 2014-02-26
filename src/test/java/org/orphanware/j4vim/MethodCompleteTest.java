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

	public void testGetMethodsForVarByPrefix(){

		MethodComplete mc = new MethodComplete("import java.util.LinkedList; LinkedList a = new LinkedList()");
		System.out.println(mc.getMethodsForVarByPrefix("a", ""));


	}






}
