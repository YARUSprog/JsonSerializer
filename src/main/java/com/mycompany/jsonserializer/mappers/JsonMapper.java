
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonWriter;

/**
 *
 * @author YARUS
 */
public interface JsonMapper<T> {       
    public void write(T object, JsonWriter writer);    
}
