package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alkuSaldoToimii() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void latausToimii(){
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    
    @Test
    public void ottoToimii1() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void ottoToimii2() {
        kortti.otaRahaa(1001);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void ottoToimii3(){
        boolean vastaus = kortti.otaRahaa(1000);
        assertEquals(true, vastaus);
    }
    
    @Test
    public void ottoToimii4(){
        boolean vastaus = kortti.otaRahaa(1001);
        assertEquals(false, vastaus);
    }
}
