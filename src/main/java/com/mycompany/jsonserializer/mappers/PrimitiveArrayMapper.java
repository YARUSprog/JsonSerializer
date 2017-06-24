
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public class PrimitiveArrayMapper implements JsonMapper<Object>{

    private JsonSerializer serializer;
    
    public PrimitiveArrayMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Object object, JsonWriter writer) {        
        writer.writeArrayBegin();                
        Number[] numbers = null;

        if(byte[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((byte[])object).length];
            for(int i = 0; i < ((byte[])object).length; i++){
                numbers[i] = ((byte[])object)[i];
            }
        } else if(short[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((short[])object).length];
            for(int i = 0; i < ((short[])object).length; i++){
                numbers[i] = ((short[])object)[i];
            }            
        } else if(char[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            char[] chars = (char[])object;
            if(chars != null && chars.length != 0){
                for(Character obj : chars){                
                    serializer.serialize(obj, writer);                
                    writer.writeSeparator();
                }
            }
        } else if(int[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((int[])object).length];
            for(int i = 0; i < ((int[])object).length; i++){
                numbers[i] = ((int[])object)[i];
            }
        } else if(long[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((long[])object).length];
            for(int i = 0; i < ((long[])object).length; i++){
                numbers[i] = ((long[])object)[i];
            }
        } else if(float[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((float[])object).length];
            for(int i = 0; i < ((float[])object).length; i++){
                numbers[i] = ((float[])object)[i];
            }
        } else if(double[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            numbers = new Number[((double[])object).length];
            for(int i = 0; i < ((double[])object).length; i++){
                numbers[i] = ((double[])object)[i];
            }
        } else if(boolean[].class.getCanonicalName().equals(object.getClass().getCanonicalName())) {
            boolean[] bools = (boolean[])object;
            if(bools != null && bools.length != 0){
                for(Object obj : bools){                
                    serializer.serialize(obj, writer);
                    writer.writeSeparator();
                }
            }
        } 
        
        if(numbers != null && numbers.length != 0){            
            for(Object obj : numbers){                
                serializer.serialize(obj, writer);
                writer.writeSeparator();
            }                        
        } 
        writer.writeArrayEnd();   
    }
}
