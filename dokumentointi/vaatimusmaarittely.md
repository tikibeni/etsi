# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on helpottaa käyttäjiä opintojen suunnittelussa ja seurannassa. 
Sovelluksen avulla käyttäjä voi seurata ja suunnitella opintojaan. 
Sovellukseen voi rekisteröityä, ja se säilyttää kunkin käyttäjän syöttämiä seurantatietoja.

## Käyttäjät

- Käyttäjäroolina toimii peruskäyttäjä. 
* Peruskäyttäjä voi rakentaa ja seurata opintosuunnitelmaa.

## Näkymät

- Kirjautumissivu
- Rekisteröintisivu
- Suunnittelusivu

## Toiminnallisuudet

### Rekisteröinti ja kirjautuminen
- Sovellukseen voi rekisteröityä uniikilla käyttäjätunnuksella (min 3 merkkiä, max 12 merkkiä)
* Virheilmoitus mikäli validointi ei onnistu, tai käyttäjätunnus on jo olemassa.
- Sovellukseen voi kirjautua rekisteröidyllä käyttäjällä
* Virheilmoitus mikäli yrittää kirjautua käyttäjällä jota ei ole
- Kirjautumisen jälkeen avautuu suunnittelusivu opinnoista

### Suunnittelusivu
- Näyttää suoritettujen opintopisteiden lukumäärän ja keskiarvon.
- Oikealla yläkulmassa logout-painike, josta menee takaisin kirjautumissivulle.
- Yhteenvetosivulla on mahdollista valita itselle pääaine, 2 sivuainetta ja muut opinnot.
- Ainekokonaisuuksien valitsemisen jälkeen "ainelaatikosta" voi merkitä kursseja suoritetuiksi ja / tai valita kursseja tuleville ajanjaksoille
* Merkitsemisessä: lukuvuosi, periodi, (arvosana)
* Tulevissa kursseissa: lukuvuosi, periodi
- Samoin valittuja kursseja voi myös poistaa ja muokata (ajanjaksoa ja arvosanaa)

## Kehitysideoita
- Ohjelma huomioi tulevissa kursseissa niiden esitietovaatimukset ja tarkistaa onko käyttäjä suorittanut / tuleeko käyttäjä suorittamaan edeltävät vaaditut kurssit ennen kurssia.
- Kursseja pystyy valita vain niille periodeille, millä ne järjestetään (tämä tosin haastava toteuttaa muuttuvien järjestelyiden takia)
- Näyttää tällä hetkellä (tässä periodissa) aktiiviset omat opinnot.
- Sivuaineita pystyy valita dynaamisesti, jottei rajoitus ole kahdessa.