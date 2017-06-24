
package com.mycompany.jsonserializer.mappers;

import com.mycompany.jsonserializer.JsonSerializer;
import com.mycompany.jsonserializer.JsonWriter;
import com.mycompany.jsonserializer.Reflections;
import com.mycompany.jsonserializer.annotations.JsonIgnore;
import com.mycompany.jsonserializer.annotations.JsonProperty;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class SyntheticClassMapper implements JsonMapper<Object>{

    private JsonSerializer serializer;
    private Reflections reflection = new Reflections();        
    
    public SyntheticClassMapper(JsonSerializer serializer){
        this.serializer = serializer;
    }
    
    @Override
    public void write(Object object, JsonWriter writer) {        
        writer.writeObjectBegin();        
        ArrayList<Field> fields = getSerializeFields(object);        
            for(Field field: fields){
                try {                    
                    String fieldName = (String) field.getName();
                    String newName = null;
                    Annotation a = field.getAnnotation(JsonProperty.class);
                    if(a != null){
                        Method method = a.getClass().getDeclaredMethod("name");                    
                        if(method != null)
                            newName = (String) method.invoke(a);
                    }
                    
                    if(newName != null && newName.length() != 0){
                        fieldName = newName;
                    }                    
                    Object fieldValue = field.get(object);                    
                    writer.writeString(fieldName);
                    writer.writePropertySeparator();                    
                    serializer.serialize(fieldValue, writer);
                    writer.writeSeparator();                    
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(SyntheticClassMapper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(SyntheticClassMapper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchMethodException ex) {
                Logger.getLogger(SyntheticClassMapper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(SyntheticClassMapper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SyntheticClassMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            }            
        writer.writeObjectEnd();
    }
    
    private ArrayList<Field> getSerializeFields(Object obj){
        
        Field[] allFields = obj.getClass().getDeclaredFields();        
        ArrayList<Field> res = new ArrayList<>(); 
        for (Field field :allFields) {            
            if(!reflection.hasAnnotation(field, JsonIgnore.class.getCanonicalName()) 
              && Modifier.isPublic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())){
                res.add(field);
            }
            Annotation[] annotations = field.getAnnotations();
            for (Annotation an : annotations) {
                if (an.annotationType().getCanonicalName().equals(JsonProperty.class.getCanonicalName())){
                    field.setAccessible(true);
                    res.add(field);
                }                
            }            
        }
        return res;
    }    
}
