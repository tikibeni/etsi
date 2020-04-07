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
  * Virheilmoitus mikäli validointi ei onnistu, tai käyttäjätunnus on jo olemassa.
- TEHTY: Sovellukseen voi kirjautua rekisteröidyllä suunnitelmanimellä
  * Virheilmoitus mikäli yrittää kirjautua virheellisellä merkkijonolla tai suunnitelmalla jota ei ole olemassa.
  * Ei luo muutoksia plans.txt-tiedostoon virhetilanteissa.
- TEHTY: Kirjautumisen jälkeen avautuu suunnittelusivu opinnoista

### Suunnittelusivu

- TEHTY: Oikealla logout-painike, josta menee takaisin kirjautumissivulle.
- TEHTY: Sivulla myös delete-painike, josta voi poistaa oman suunnitelman.
  * Tähän vahvistuskysymys, jottei suunnitelma poistu misclickin johdosta.
- Suunnittelusivulla on mahdollista poimia jo suoritettuja kursseja, jonka myötä ohjelma tarjoaa, mitä kursseja voi suorittaa seuraavaksi.
  * Toistaiseksi ei pysty valita kursseja, joiden esitietoihin pohjautuvia kursseja ei ole vielä valittu.
- Suunnittelua varten oma "laatikko" ainetta varten.

## Kehitysideoita

- Sovellukseen voi syöttää suoritettujen kurssien arvosanat, jonka myötä laskee keskiarvot (koko, pääaine, sivuaine(et))
- Useamman aineen (esim. matematiikka) esitiedot ja suunnittelumahdollisuus.
- Sovellukseen voi itse syöttää kurssikokonaisuuksia, kursseja ja niihin liittyviä tietoja.