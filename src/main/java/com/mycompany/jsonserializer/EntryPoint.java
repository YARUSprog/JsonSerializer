
package com.mycompany.jsonserializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LogManager;

/**
 * The <code>EntryPoint</code> class  - main class.  
 * @since Java_8
 * @author  Musienko Yaroslav
 */
public class EntryPoint {
    /**
     * Main methods - entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        try{
            LogManager.getLogManager().readConfiguration(EntryPoint.class.getResourceAsStream("/logging.properties"));
        } catch(IOException e){
            System.err.println("Could not setup logger configuration" + e.toString());
        }
        
        JsonSerializer sr = new JsonSerializer();
        TestClass tc = new TestClass();
        boolean[] objArray = {true, true};
        boolean b = true;   
        Boolean b2 = new Boolean(b);
        List<Integer> ar = new ArrayList<Integer>();
        ar.add(2);
        ar.add(6);
        ar.add(8);
        ar.add(14);
        HashMap<String, Integer> mc = new HashMap<String, Integer>();
        mc.put("a", 2);
        mc.put("b", 6);
        mc.put("sd", 8);
        mc.put("tr", 14);
        char[] intArray = {'r','g','b','5'};
        StringWriter sw = new StringWriter(100);
        sr.setIndent(true);
        System.out.println(sr.serialize(tc));
    }
}
