package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void alustusToimii() {
        int rahaa = paate.kassassaRahaa();
        int myyty = paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty();
        
        assertEquals(100000, rahaa+myyty);
    }
    
    @Test
    public void ostoKateinenEdullinenToimii() {
        int vaihtorahaa = paate.syoEdullisesti(241);
        
        assertEquals(100241, paate.kassassaRahaa() + vaihtorahaa);
    }
    
    @Test
    public void ostoKateinenMaukasToimii() {
        int vaihtorahaa = paate.syoMaukkaasti(405);
        
        assertEquals(100405, paate.kassassaRahaa() + vaihtorahaa);
    }
    
    @Test
    public void ostoMyydytEdullisetKasvaa() {
        paate.syoEdullisesti(240);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void ostoMyydytMaukkaatKasvaa() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void liianVahanRahaa() {
        paate.syoEdullisesti(239);
        paate.syoMaukkaasti(399);
        
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiOstoToimiiEdullinen() {
        boolean toimii = paate.syoEdullisesti(kortti);
        assertEquals(true, toimii);
    }
    
    @Test
    public void korttiOstoToimiiMaukas() {
        boolean toimii = paate.syoMaukkaasti(kortti);
        assertEquals(true, toimii);
    }
    
    @Test
    public void korttiOstoNostaaMyytyja() {
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(2, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaLiianVahanRahaa() {
        kortti.otaRahaa(601);
        boolean toimii = paate.syoMaukkaasti(kortti);
        assertEquals(false, toimii);
    }
    
    @Test
    public void kortillaLiianVahanRahaa2() {
        kortti.otaRahaa(601);
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaLiianVahanEdulliseen() {
        kortti.otaRahaa(900);
        boolean ei = paate.syoEdullisesti(kortti);
        assertEquals(false, ei);
    }
    
    @Test
    public void kassaSummaEiMuutuKortilla() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void saldonLatausToimii() {
        paate.lataaRahaaKortille(kortti, 400);
        assertEquals(101800, paate.kassassaRahaa() + kortti.saldo());
    }
    
    @Test
    public void saldonLatauksenLoogisuus() {
        paate.lataaRahaaKortille(kortti, -200);
        assertEquals(1000, kortti.saldo());
    }
    
}
