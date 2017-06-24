
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;
import java.util.Collection;

/**
 *
 * @author YARUS
 */
public class CollectionMapper implements JsonMapper<Collection>{

    private JsonSerializer serializer;
    
    public CollectionMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Collection objects, JsonWriter writer) {
        writer.writeArrayBegin();
        for(Object obj: objects){                
            serializer.serialize(obj, writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();   
    }
    
}
