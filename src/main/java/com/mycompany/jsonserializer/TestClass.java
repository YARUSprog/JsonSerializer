
package com.mycompany.jsonserializer;

import com.mycompany.jsonserializer.annotations.JsonIgnore;
import com.mycompany.jsonserializer.annotations.JsonProperty;

/**
 *
 * @author YARUS
 */
public class TestClass {
    @JsonProperty
    private transient String name = "Yaroslav";
    
    @JsonProperty
    private int age = 21;
    
    @JsonProperty(name = "isMen")
    private boolean man = true;
    
    @JsonProperty
    private transient String serialize = "сериализовать";
     
    public String[] strArray = {"wer", "", "345"};
     
    public char[] charArray = {'r','g','b','5'};
    
    public int[] intArray = {};
    
    public boolean[] boolArray = {true, true , false , true};
    
    public char char1 = 'r';
    
    @JsonIgnore    
    public String notSerialize = "Не сериализовать 1";
        
    public transient String notSerialize2 = "Не сериализовать 2";    
    
    public String serialize3 ;
}
