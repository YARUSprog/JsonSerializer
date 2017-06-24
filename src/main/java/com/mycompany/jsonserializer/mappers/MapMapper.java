
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author YARUS
 */
public class MapMapper implements JsonMapper<Map>{

    private JsonSerializer serializer;
    
    public MapMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Map mapObjects, JsonWriter writer) {        
        writer.writeArrayBegin();
        Collection keys = mapObjects.keySet();
        Collection values = mapObjects.values();
        Object[] keyArray = keys.toArray();
        Object[] valueArray = values.toArray();
        for(int i = 0; i < keyArray.length; i++){
            writer.writeString(keyArray[i].toString());
            writer.writePropertySeparator();
            serializer.serialize(valueArray[i], writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();   
    }
    
}
