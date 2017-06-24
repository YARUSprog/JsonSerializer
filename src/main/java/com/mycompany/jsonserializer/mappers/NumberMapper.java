
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public class NumberMapper implements JsonMapper<Number>{

    private JsonSerializer serializer;
    
    public NumberMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Number object, JsonWriter writer) {        
        writer.writeNumber(object);
    }
    
}
