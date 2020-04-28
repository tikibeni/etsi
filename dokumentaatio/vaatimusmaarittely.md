# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on helpottaa käyttäjiä opintojen suunnittelussa. 
Sovelluksen avulla käyttäjä voi tarkistella jo suoritettujen kurssien pohjalta, mitä kursseja voi valita seuraavaksi.
Sovellukseen voi rekisteröityä, ja se säilyttää kunkin käyttäjän syöttämiä suunnittelutietoja.

## Käyttäjät

- Käyttäjäroolina toimii peruskäyttäjä. 
  * Peruskäyttäjä voi rakentaa ja tarkastella kurssisuunnitelmaa.

## Näkymät

- Kirjautumissivu
- Rekisteröintisivu
- Suunnittelusivu

## Toiminnallisuudet

### Rekisteröinti ja kirjautuminen

- TEHTY: Sovellukseen voi rekisteröidä uuden suunnitelman uniikilla suunnitelmanimellä (min 3 merkkiä, max 12 merkkiä) ja nimellä (4-30 merkkiä)
  * TEHTY: Virheilmoitus mikäli validointi ei onnistu, tai käyttäjätunnus on jo olemassa.
- TEHTY: Sovellukseen voi kirjautua rekisteröidyllä suunnitelmanimellä
  * TEHTY: Virheilmoitus mikäli yrittää kirjautua virheellisellä merkkijonolla tai suunnitelmalla jota ei ole olemassa.
  * TEHTY: Ei luo muutoksia plans.txt-tiedostoon virhetilanteissa.
- TEHTY: Kirjautumisen jälkeen avautuu suunnittelusivu opinnoista
- TODO:
  * Rekisteröinnin yhteydessä validoi EventListenerillä

### Suunnittelusivu

- TEHTY: Oikealla logout-painike, josta menee takaisin kirjautumissivulle.
- TEHTY: Sivulla myös delete-painike, josta voi poistaa oman suunnitelman.
- TEHTY: Suunnittelusivulla on mahdollista poimia jo suoritettuja kursseja, jonka myötä ohjelma tarjoaa, mitä kursseja voi suorittaa seuraavaksi.
- TEHTY: Suunnittelua varten oma linkki ainetta varten.
- TEHTY: Sivulle oma yläpalkki, jonka Menussa logout, delete plan, edit jne.
- TODO:
  * Vahvistusilmoitus suunnitelman poiston yhteydessä
  * Esitietokaaviosivun sisältö
  * Yhdistä scrollpaneen

## Kehitysideoita

- TODO:
  * Sovelluslogiikan erottelun + graafisen käyttöliittymän hiominen
  * DONE: Näkymät pysyvät keskellä näyttöä
  
- Sovellukseen voi syöttää suoritettujen kurssien arvosanat, jonka myötä laskee keskiarvot (koko, pääaine, sivuaine(et))
- Useamman aineen (esim. matematiikka) esitiedot ja suunnittelumahdollisuus.
- Sovellukseen voi itse syöttää kurssikokonaisuuksia, kursseja ja niihin liittyviä tietoja.