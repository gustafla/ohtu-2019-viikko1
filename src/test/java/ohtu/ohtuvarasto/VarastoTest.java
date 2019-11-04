package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEpatyhjanVaraston() {
        Varasto varasto = new Varasto(10, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiHyvaksyNegatiivistaSaldoa() {
        Varasto varasto = new Varasto(10, -10);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiHyvaksyNegatiivistaTilavuutta() {
        Varasto varasto = new Varasto(-10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiHyvaksyNegatiivistaTilavuutta2() {
        Varasto varasto = new Varasto(-10, 10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-1);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiNostaSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(1000);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiMuutaVarastonSaldoa() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(-1);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldonYliOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void tilavuudenYliOttaminenLisaaOikeanMaaranTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(20);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonMerkkijonoesitysSisaltaaSaldonJaPaljonkoMahtuu() {
        assertTrue(varasto.toString().contains(" 10"));
        assertTrue(varasto.toString().contains(" 0"));
        varasto.lisaaVarastoon(1);
        assertTrue(varasto.toString().contains(" 9"));
        assertTrue(varasto.toString().contains(" 1"));
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
