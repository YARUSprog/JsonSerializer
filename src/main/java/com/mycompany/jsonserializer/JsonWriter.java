
package com.mycompany.jsonserializer;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class JsonWriter {    
    private static final Logger log = Logger.getLogger(JsonWriter.class.getName());
    
    protected StringBuilder data;
    protected Writer writer;
    
    public JsonWriter(){        
        data = new StringBuilder(100);
        writer = new StringWriter(100);
    }
    
    public JsonWriter(Writer writer){
        if(writer == null)
            throw new IllegalArgumentException("writer == null");
        this.writer = writer;
        data = new StringBuilder(100);
    }
    
    public void writeObjectBegin(){
        data.append('{');        
    }
    
    public void writeObjectEnd(){        
        if(data.charAt(data.length()-1) == ','){
            data.deleteCharAt(data.length()-1);
        }        
        data.append("}");
        flush();
    }
    
    public void writeArrayBegin(){
        data.append('[');
    }
    
    public void writeArrayEnd(){        
        if(data.charAt(data.length()-1) == ','){
            data.deleteCharAt(data.length()-1);
        }        
        data.append(']');  
        flush();
    }
    
    public void writeString(String str){
        if(str == null)
            throw new IllegalArgumentException("str == null");        
        if(str.length() <= 2 || (str.charAt(0) != '"' && str.charAt(str.length()-1) != '"')){
            str = '"'+ str;
            str += '"';
        }
        data.append(str);
    }
    
    public void writeNumber(Number num){
        if(num == null)
            throw new IllegalArgumentException("str == null || str.length() == 0");
        data.append(num);
    }
    
    public void writeSeparator(){
        data.append(',');
    }
    
    public void writePropertySeparator(){
        data.append(':');
    }
    
    public void writeBoolean(boolean bool){
        data.append(bool);
    }
    
    public void writeNull(){
        data.append("null");
    }
    
    public void flush(){
        try {
            writer.write(data.toString());        
            writer.flush();
            data = new StringBuilder();
        } catch (IOException ex) {            
            log.log(Level.SEVERE, "Exception: ", ex);
        }
    }
    
    public void close(){
        try {
            writer.close();
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Exception: ", ex);
        }
    }
}
