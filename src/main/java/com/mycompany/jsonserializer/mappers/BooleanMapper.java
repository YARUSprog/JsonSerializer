
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public class BooleanMapper implements JsonMapper<Boolean>{

    private JsonSerializer serializer;
    
    public BooleanMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Boolean object, JsonWriter writer) {        
        writer.writeBoolean(object);
    }    
}
