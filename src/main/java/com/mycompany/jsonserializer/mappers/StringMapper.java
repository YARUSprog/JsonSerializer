
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public class StringMapper implements JsonMapper<Object>{

    private JsonSerializer serializer;
    
    public StringMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Object object, JsonWriter writer) {        
        writer.writeString(""+object);        
    }
    
}
