
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public class ObjectArrayMapper implements JsonMapper<Object[]> {

    private JsonSerializer serializer;
    
    public ObjectArrayMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }    

    @Override
    public void write(Object[] objects, JsonWriter writer) {        
        writer.writeArrayBegin();               
        for(int i = 0; i < objects.length; i++){
            Object obj = objects[i];
            serializer.serialize(obj, writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();    
    }
}
