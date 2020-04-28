# Käyttöohje

## Konfigurointi

Tällä hetkellä ohjelma tarvitsee toimiakseen sovelluksen juurihakemistoon tiedoston _config.file_, jonka sisältö on seuraava:

`plans=plans.txt`
- Sisältää luodut suunnitelmat ja niihin liitetyt kurssit.

`courses=courses.txt`
- Sisältää ennaltamääritetyt kurssit (kurssikoodi, kurssinimi) ja niiden esitietovaatimukset

Sekä `coursesInfo=` -osio, jonka myötä ohjelma automaattisesti ajon yhteydessä alustaa em. kurssitiedot _courses.txt_ -tiedostoon.

Tämän avulla ohjelma käyttää em. tekstitiedostoja tietokantana.

_config.file_ sisältö löytyy myös [täältä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/PlanApp/config.file)

## Käynnistäminen

### Jar-tiedostona

Ohjelman voi ajaa ladattavan .jar-tiedoston kautta komentorivikomennolla:

```java -jar planapp.jar```

Oletuksella, että .jar-tiedoston kanssa samassa kansiossa on tiedosto _config.file_ sisältöineen. 

Viimeisimmän .jar-tiedoston voi ladata [täältä](https://github.com/tikibeni/ot-harjoitustyo/releases/tag/v1.0)

Tiedoston voi tarvittaessa luoda itse ja kopioida tiedot [täältä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/PlanApp/config.file).

### Source codena

Vaihtoehtoisesti .jar-tiedoston sijaan ohjelman voi ajaa lataamalla lähdekoodin, joka löytyy [täältä](https://github.com/tikibeni/ot-harjoitustyo/releases/tag/v1.0).

Ohjelman saa näin ajettua mm. Netbeansilla projektin avaamalla ja komentorivikomennolla (hakemistossa PlanApp/):

```mvn compile exec:java -Dexec.mainClass=planapp.Main```

Muut ohjelmaan liittyvät komentorivikomennot ja niiden ohjeet löydät [täältä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/README.md)


## Kirjautumisnäkymä

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/logininstru.png "Login view")

Kirjautumisnäkymässä voit kirjautua sisään suunnitelmanäkymään luodun suunnitelman nimellä ja painamalla `Login`.

Mikäli suunnitelmaa ei ole vielä luotu, sen voi luoda `Register` -painikkeesta


## Rekisteröintinäkymä

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/registeration.png "Registeration view")

Rekisteröintinäkymässä voit luoda uuden suunnitelman syöttämällä suunnitelman nimen, joka toimii ikään kuin käyttäjätunnuksena ja oman nimesi

- Suunnitelman nimen tulee olla 3-12 merkkiä pitkä
- Nimen tulee olla 4-30 merkkiä pitkä

Rekisteröinnin voi sinetöidä painamalla `Register`, jonka myötä ohjelma palaa kirjautumissivulle.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/registersuccess.png "Success")


## Suunnitelmanäkymä

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/mainview.png "Main view")

Suunnitelmanäkymässä voi kirjautua ulos painikkeesta `Logout` ja poistaa suunnitelma painikkeesta `Delete plan`

Kummastakin painikkeesta näkymä vaihtuu takaisin kirjautumisnäkymään.

Suunnitelmanäkymän vasemmassa reunassa on eräänlainen menu toimintojen välille. Painikkeesta `TKT-courses` pääsee TKT-kurssien suunnitelmaan.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/freshplan.png "View before selections")

- Kurssisuunnitelma toimii siten, että ohjelma tarjoaa seuraavia kursseja jo suoritettujen kurssien myötä. Jo suoritetut kurssit voi valita laittamalla raksin ruutuun kunkin kurssin kohdalle, jonka on suorittanut ja painamalla `Save`. Painikkeen painamisen myötä sivun oikeaan reunaan ilmestyy `Suggested courses` -palkki, jonka alapuolelle tulee ehdotetut kurssit.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/suggestions.png "Suggestions from selections")

- Esitietojen kaaviosivulle pääsee painamalla `TKT-Prerequisites` -painiketta. Sivulla näkyy TKT-kurssien esitiedot taulukkona.
