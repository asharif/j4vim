/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author asharif
 */
public class ClassCompleteTest extends TestCase {
    
    public ClassCompleteTest(String testName ) {
        
        super(testName);
    }
    
    public static Test suite()
    {
        return new TestSuite( ClassCompleteTest.class );
    }
    
    public void testGetClassesByPrefix() throws Exception {
        
        String jar = "/Users/asharif/.m2/repository/org/mockito/mockito-all/1.8.2/mockito-all-1.8.2.jar";
        
        ClassComplete cc = new ClassComplete(jar);
        
        System.out.println(cc.getClassesByPrefix("Port"));
    }
    
}
