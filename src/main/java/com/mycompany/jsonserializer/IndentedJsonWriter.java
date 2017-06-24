
package com.mycompany.jsonserializer;

import java.io.Writer;

/**
 *
 * @author YARUS
 */
public class IndentedJsonWriter extends JsonWriter {
    
    private int indentSize;
    private int currentLevel;

    public IndentedJsonWriter(){        
        super();
        indentSize = 5;
        currentLevel = 0;
    }
    
    public IndentedJsonWriter(Writer writer){
        super(writer);
        indentSize = 3;
        currentLevel = 0;
    }
    
    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    public int getIndentSize() {
        return indentSize;
    }
    
    @Override
    public void writeObjectBegin(){
        super.writeObjectBegin();          
        currentLevel++;
        data.append('\n');
        for(int i = 0; i < currentLevel*indentSize; i++){
            data.append(' ');
        }
    }
    
    @Override
    public void writeObjectEnd(){
        for(int i = 0; i < currentLevel*indentSize; i++){
            data.deleteCharAt(data.length()-1);
        }
        if(data.charAt(data.length()-1) == '\n'){
            data.deleteCharAt(data.length()-1);            
        }
        if(data.charAt(data.length()-1) == ','){
            data.deleteCharAt(data.length()-1);
            currentLevel--;
            data.append('\n');
            for(int i = 0; i < currentLevel*indentSize; i++){
                data.append(' ');
            }
            super.writeObjectEnd();
        } else {
            currentLevel--;                
            super.writeObjectEnd();
        }
    }
    
    @Override
    public void writeArrayBegin(){
        super.writeArrayBegin();
        currentLevel++;
        data.append('\n');
        for(int i = 0; i < currentLevel*indentSize; i++){
            data.append(' ');
        }
    }
    
    @Override
    public void writeArrayEnd(){
        for(int i = 0; i < currentLevel*indentSize; i++){
            data.deleteCharAt(data.length()-1);
        }
        if(data.charAt(data.length()-1) == '\n'){
            data.deleteCharAt(data.length()-1);            
        }
        if(data.charAt(data.length()-1) == ','){
            data.deleteCharAt(data.length()-1);
            currentLevel--;
            data.append('\n');
            for(int i = 0; i < currentLevel*indentSize; i++){
                data.append(' ');
            }
            super.writeArrayEnd();
        } else {
            currentLevel--;                
            super.writeArrayEnd();
        }                
    }
    
    @Override
    public void writeString(String str){        
        super.writeString(str);
    }
    
    @Override
    public void writeNumber(Number num){                
        super.writeNumber(num);
    }
    
    @Override
    public void writeSeparator(){
        super.writeSeparator();
        data.append('\n');
        for(int i = 0; i < currentLevel*indentSize; i++){
            data.append(' ');
        }
    }
    
    @Override
    public void writePropertySeparator(){
        data.append(' ');
        super.writePropertySeparator();
        data.append(' ');
    }
    
}
