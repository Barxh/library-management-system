/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

import java.io.Serializable;





/**
 *
 * @author nikol
 */
public enum Genre implements Serializable{
    NOVEL, SCIENCE_FICTION, LOVE_STORY, HISTORICAL, BIOGRAPHY, CHILDREN_BOOK;
    
    public String toTitle(){
        return this.toString().replace('_', ' ' );
    }
    public static Genre parseString(String string){
        return Genre.valueOf(string.replace(' ', '_'));
    }
}
