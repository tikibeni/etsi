# Käyttöohje

## Konfigurointi

Tällä hetkellä ohjelma tarvitsee toimiakseen sovelluksen juurihakemistoon tiedoston "config.file", jonka sisältö on seuraava:

plans = plans.txt
- Sisältää suunnitelmat ja niihin liittyvät kurssit

courses = courses.txt
- Sisältää ennaltamääritetyt kurssit (kurssikoodi, kurssinimi) ja niiden esitietovaatimukset
- HUOM! Ethän muokkaa courses.txt:n sisältöä. Toistaiseksi tiedoston sisältö on ennaltamääritetty eikä ohjelma muokkaa sisältöä mitenkään.
    * Tähän liittyen tulee validointi, joka luo tiedoston kokonaan ohjelmakoodiin pohjautuen.

Tämän avulla ohjelma käyttää em. tekstitiedostoja tietokantana.

## Käynnistäminen

Ohjelman voi ajaa komennolla

```java -jar planapp.jar```

## Kirjautumisnäkymä

Kirjautumisnäkymässä voit kirjautua sisään suunnitelmanäkymään luodun suunnitelman nimellä ja painamalla login.

Mikäli suunnitelmaa ei ole vielä luotu, sen voi luoda Register -painikkeesta


## Rekisteröintinäkymä

Rekisteröintinäkymässä voit luoda uuden suunnitelman syöttämällä suunnitelman nimen, joka toimii ikään kuin käyttäjätunnuksena ja oman nimesi

- Suunnitelman nimen tulee olla 3-12 merkkiä pitkä
- Nimen tulee olla 4-30 merkkiä pitkä

Rekisteröinnin voi sinetöidä painamalla Register, jonka myötä ohjelma palaa kirjautumissivulle.

## Suunnitelmanäkymä

Suunnitelmanäkymässä voi kirjautua ulos painikkeesta "Logout" ja poistaa suunnitelma painikkeesta "Delete plan"

Kummastakin painikkeesta näkymä vaihtuu takaisin kirjautumisnäkymään.

Suunnitelmanäkymän vasemmassa reunassa on eräänlainen menu toimintojen välille. Painikkeesta "TKT-courses" pääsee TKT-kurssien suunnitelmaan.

- Kurssisuunnitelma toimii siten, että ohjelma tarjoaa seuraavia kursseja jo suoritettujen kurssien myötä. Jo suoritetut kurssit voi valita laittamalla raksin ruutuun kunkin kurssin kohdalle, jonka on suorittanut ja painamalla "Save". Painikkeen painamisen myötä sivun oikeaan reunaan ilmestyy "Suggested courses" -palkki, jonka alapuolelle tulee ehdotetut kurssit.

- Esitietojen kaaviosivulle pääsee painamalla "Prerequisites graph" -painiketta. Sivulla näkyy TKT-kurssien esitiedot taulukkona.