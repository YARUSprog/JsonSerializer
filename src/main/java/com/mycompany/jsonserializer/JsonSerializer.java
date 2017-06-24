
package com.mycompany.jsonserializer;

import com.mycompany.jsonserializer.mappers.BooleanMapper;
import com.mycompany.jsonserializer.mappers.CollectionMapper;
import com.mycompany.jsonserializer.mappers.JsonMapper;
import com.mycompany.jsonserializer.mappers.MapMapper;
import com.mycompany.jsonserializer.mappers.NumberMapper;
import com.mycompany.jsonserializer.mappers.ObjectArrayMapper;
import com.mycompany.jsonserializer.mappers.PrimitiveArrayMapper;
import com.mycompany.jsonserializer.mappers.StringMapper;
import com.mycompany.jsonserializer.mappers.SyntheticClassMapper;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class JsonSerializer {

    private static final Logger log = Logger.getLogger(JsonSerializer.class.getName());
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private boolean indent = false;
    private Map<String, JsonMapper> mappersCashe = new HashMap<String, JsonMapper>();
    private Reflections reflection = new Reflections();
        
    public JsonSerializer(){
        log.info("Create JsonSerializer");
        mappersCashe.put("BooleanMapper", new BooleanMapper(this));
        mappersCashe.put("CollectionMapper", new CollectionMapper(this));
        mappersCashe.put("MapMapper", new MapMapper(this));
        mappersCashe.put("NumberMapper", new NumberMapper(this));
        mappersCashe.put("ObjectArrayMapper", new ObjectArrayMapper(this));
        mappersCashe.put("StringMapper", new StringMapper(this));
        mappersCashe.put("PrimitiveArrayMapper", new PrimitiveArrayMapper(this));
        mappersCashe.put("SyntheticClassMapper", new SyntheticClassMapper(this));
    }
    
    public boolean isIndent() {
        return indent;
    }

    public void setIndent(boolean indent) {
        log.info("Indent: " + indent);
        this.indent = indent;
    }

    public String serialize(Object obj) throws  IllegalStateException {
        StringWriter sw = new StringWriter(100);
        serialize(obj, sw);
        return sw.toString();
    }

    public void serialize(Object obj, OutputStream stream) {
        if(stream == null)
            throw new IllegalArgumentException("obj == null || stream == null");
        serialize(obj, stream, DEFAULT_CHARSET);
    }

    public void serialize(Object obj, OutputStream stream, Charset charset) {
        if(stream == null || charset == null)
            throw new IllegalArgumentException("stream == null || charset == null");
        serialize(obj, new OutputStreamWriter(stream, charset));
    }

    public void serialize(Object obj, Writer w) {
        if(w == null)
            throw new IllegalArgumentException("writer == null");
        JsonWriter writer;
        if(isIndent())
            writer = new IndentedJsonWriter(w);
        else
            writer = new JsonWriter(w);            
        
        if(obj == null){
            writer.writeNull();
            return;
        }        
        JsonMapper mapper = getMapper(obj);
        if(mapper != null){
            mapper.write(obj, writer);        
        }
        writer.close();        
    }
    
    public void serialize(Object obj, JsonWriter writer) {
        if(writer == null)
            throw new IllegalArgumentException("writer == null");  
        if(obj == null){
            writer.writeNull();            
            return;
        }
        JsonMapper mapper = getMapper(obj);
        if(mapper != null){
            mapper.write(obj, writer);        
        } 
        writer.close();        
    }
    
    private JsonMapper getMapper(Object obj){
        JsonMapper mapper = null;
        if(obj.getClass().isArray() && obj.getClass().getComponentType().isPrimitive()){
            mapper = mappersCashe.get("PrimitiveArrayMapper");
        } else if(obj.getClass().isArray()){            
            mapper = mappersCashe.get("ObjectArrayMapper");
        } else if(obj.getClass().getName().equalsIgnoreCase(String.class.getName())){            
            mapper = mappersCashe.get("StringMapper");
        } else if(reflection.isDescendant(obj.getClass(), Number.class.getCanonicalName())){            
            mapper = mappersCashe.get("NumberMapper");
        } else if(Boolean.class.isInstance(obj)){            
            mapper = mappersCashe.get("BooleanMapper");
        } else if(reflection.isImplements(obj.getClass(), Collection.class.getCanonicalName())){            
            mapper = mappersCashe.get("CollectionMapper");
        } else if(reflection.isImplements(obj.getClass(), Map.class.getCanonicalName())){            
            mapper = mappersCashe.get("MapMapper");
        } else if(Character.class.isInstance(obj)){            
            mapper = mappersCashe.get("StringMapper");
        } else {            
            mapper = mappersCashe.get("SyntheticClassMapper");
        }
        log.info("Getting mapper: " + mapper.getClass().getName());
        return mapper;
    }
}
