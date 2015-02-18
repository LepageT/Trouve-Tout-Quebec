/*
 * Accommodation.java
 *
 * Created on 6 March 2006, 11:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.example.tfboe.trouve_toutquebec;

/**
 *
 * @author John
 */
public class Keyword {

    /** Creates a new instance of Accommodation */
    public Keyword(String id, 
                 String name, 
                 String keywords,
                 String type) {
        this.id = id;     
        this.name = name;     
        this.keywords = keywords;  
        this.type = type;
    }

    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String id;
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String keywords;
    public String getKeywords() {
        return this.keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    private String type;
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
        return "Keyword "
               + getId()
               +": "
               + getName();
    }
}
