/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cfranc.ilc;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ym350189
 */
public class YormaMAASSENTest {
    
String[] when2SimpleWords = new String[] {"parapluie", "parachute"};
	
	@Test
	public void getSimilarity_2SimpleWords_26() {
		MarkovWord m = new MarkovWord();
		double expected = 0.33;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[1], 2);
		assertEquals(expected, actual,0.01);		
	}
      
	@Test
	public void getSimilarity_SameWord_100() {		
		MarkovWord m = new MarkovWord();
		double expected = 1.0;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[0], 4);
		assertEquals(expected, actual,0.000000001);		
	}	
        
        @Test
        public void Common_SameWord_2(){
            //Compare 2 Listes de mots, retourne le nombre de mots identiques dans chaque liste
            MarkovWord m = new MarkovWord();
            MarkovData m1 = new MarkovData("Essai",5);
            MarkovData m2 = new MarkovData("Essai",5);
            MarkovData m3 = new MarkovData("Try",3);
            MarkovData m4 = new MarkovData("Try",3);
            MarkovData m5 = new MarkovData("light",5);
            MarkovData m6 = new MarkovData("dark",4);
            List<MarkovData> One = new ArrayList<MarkovData>();
            List<MarkovData> Two = new ArrayList<MarkovData>();
            One.add(m1);
            One.add(m3);
            One.add(m5);
            Two.add(m2);
            Two.add(m4);
            Two.add(m6);
            
            int expected = 2;
            int actual = m.common(One, Two);
            assertEquals(expected, actual);   
        }
        
         @Test
        public void Common_SameWord_1(){
            //Compare 2 Listes de mots, retourne le nombre de mots identiques dans chaque liste
            MarkovWord m = new MarkovWord();
            //Test caractère spéciaux (FAUX)
            MarkovData m1 = new MarkovData("Essai",5);
            MarkovData m2 = new MarkovData("Essaï",5);
            //Tests Majuscules (VRAI)
            MarkovData m3 = new MarkovData("TRY",3);
            MarkovData m4 = new MarkovData("Try",3);
            //Test espaces (FAUX)
            MarkovData m5 = new MarkovData("Dark ",5);
            MarkovData m6 = new MarkovData("dark",4);
            List<MarkovData> One = new ArrayList<MarkovData>();
            List<MarkovData> Two = new ArrayList<MarkovData>();
            One.add(m1);
            One.add(m3);
            One.add(m5);
            Two.add(m2);
            Two.add(m4);
            Two.add(m6);
            
            int expected = 1;
            int actual = m.common(One, Two);
            assertEquals(expected, actual);   
        }

        
        @Test
        public void union_SimpleWords_6(){
            //Méthode qui retourne l'union des 2 listes, en retirant les mots qui sont en commun, peu importe la casse et la position dans les listes
            MarkovWord m = new MarkovWord();
            MarkovData m1 = new MarkovData("Essai",6);
            MarkovData m2 = new MarkovData("Brie",4);
            MarkovData m3 = new MarkovData("TRY",3);
            MarkovData m4 = new MarkovData("Try",3);
            MarkovData m5 = new MarkovData("Essai",6);
            MarkovData m6 = new MarkovData("BRIE",4);
            MarkovData m7 = new MarkovData("Bou",3);
            MarkovData m8 = new MarkovData("bouu",4);
            //m1-m5, m2-m6, m3-m4 identiques, m7-m8 donc 5 mots au total
            List<MarkovData> One = new ArrayList<MarkovData>();
            List<MarkovData> Two = new ArrayList<MarkovData>();
            One.add(m1);
            One.add(m2);
            One.add(m3);
            One.add(m7);
            Two.add(m4);
            Two.add(m5);
            Two.add(m6);
            Two.add(m8);
            
            int expected = 5;
            int actual = m.union(One, Two);
            assertEquals(expected, actual);   
        }
        
        
        @Test
        public void contains_5(){
            //Retourne si le mot est contenu la position du premier caractère (+1)
            MarkovWord m = new MarkovWord("ABCDEFGHIJ",3);
            int expected = 5;
            int actual = m.contains("DEF");
            assertEquals(expected, actual);	
        }
        
         @Test
        public void contains_0(){
            //Retourne si le mot est contenu la position du premier caractère (+1), et 0 sinon
            MarkovWord m = new MarkovWord("ABCDEFGHIJ",3);
            int expected = 0;
            int actual = m.contains("KLM");
            assertEquals(expected, actual);	
        }
        
        
        @Test
        public void processString_Simple_Same(){
            MarkovWord m = new MarkovWord();
            List<MarkovData> expected = new ArrayList<MarkovData>();
            expected.add(new MarkovData("%y",1));
            expected.add(new MarkovData("ym",1));
            expected.add(new MarkovData("m%",1));
	    List<MarkovData> actual = m.processString("ym", 2);
            assertEquals(expected, actual);
        }
        
        
        
        
        
        
        
        
        
       
        
        
        
}
