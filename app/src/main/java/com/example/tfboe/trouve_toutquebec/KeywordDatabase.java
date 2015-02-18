/*
 * HotelInitialisation.java
 *
 * Created on 6 March 2006, 14:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.example.tfboe.trouve_toutquebec;

/**
 *
 * @author John
 */
public class KeywordDatabase {
    
    private static final Keyword[] KEYWORDS = {    
        new Keyword("1","Toilette","Toilettes, Chiottes, chiasses, caca, Centre communautaire, commodités, hygiène, lieux d'aisances, pavillons de services, salles d'eau, toilettes publiques", "Service"),
        new Keyword("2","Lieu de culte","Terroristes, esclavage, fraudes, Jésus, culte, lieu, amis imaginaires, Chapelle, culte, église, mosquée, religion, sacré, saint, sanctuaire, synagogue, temple", "Service"),
        new Keyword("3","Proche", "proche, proximité, prés, à côt., voisine, immédiate", "Proximite"),
        new Keyword("4","Loin", "loin, éloignée, distante, écarté, lointaine, ailleurs", "Proximite")
        
    };
    
    public static Keyword[] getKeywords() {
        return KEYWORDS;
    }
    
    public static Keyword getKeyword(String id) {
        for(Keyword keyword : KEYWORDS) {
            if (id.equals(keyword.getId())) {
                return keyword;
            }
        }
        return null;
    }
}