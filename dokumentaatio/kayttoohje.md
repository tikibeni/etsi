# Käyttöohje

## Konfigurointi

Tällä hetkellä ohjelma tarvitsee toimiakseen sovelluksen juurihakemistoon tiedoston _config.file_, jonka sisältö on seuraava:

`plans=plans.txt`
- Sisältää luodut suunnitelmat ja niihin liitetyt kurssit.

`courses=courses.txt`
- Sisältää ennaltamääritetyt kurssit (kurssikoodi, kurssinimi) ja niiden esitietovaatimukset

Sekä `coursesInfo=` -osio, jonka myötä ohjelma automaattisesti ajon yhteydessä alustaa em. kurssitiedot _courses.txt_ -tiedostoon.

Tämän avulla ohjelma käyttää em. tekstitiedostoja tietokantana. Tarkempi kuvaus [täällä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

_config.file_ sisältö löytyy myös [täältä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/PlanApp/config.file)

## Käynnistäminen

### Jar-tiedostona

Ohjelman voi ajaa ladattavan .jar-tiedoston kautta komentorivikomennolla:

`java -jar planapp.jar`

Oletuksella, että .jar-tiedoston kanssa samassa kansiossa on tiedosto _config.file_ sisältöineen. 

Viimeisimmän .jar-tiedoston voi ladata [täältä](https://github.com/tikibeni/ot-harjoitustyo/releases)

Tiedoston _config.file_ voi tarvittaessa luoda itse ja kopioida tiedot [täältä](https://github.com/tikibeni/ot-harjoitustyo/blob/master/PlanApp/config.file).

### Source codena

Vaihtoehtoisesti .jar-tiedoston sijaan ohjelman voi ajaa lataamalla lähdekoodin, joka löytyy [täältä](https://github.com/tikibeni/ot-harjoitustyo/releases).

Ohjelman saa näin ajettua mm. Netbeansilla projektin avaamalla ja komentorivikomennolla (hakemistossa PlanApp/):

`mvn compile exec:java -Dexec.mainClass=planapp.Main`

Muut ohjelmaan liittyvät komentorivikomennot ja niiden ohjeet löydät [täältä](https://github.com/tikibeni/ot-harjoitustyo#komentorivitoiminnot)


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

Suunnitelmanäkymässä voi kirjautua ulos painikkeesta `Logout`, poistaa suunnitelma painikkeesta `Delete plan` ja resetoida järjestelmän kurssit `Reset courses`-painikkeesta.

Kaikista painikkeista näkymä vaihtuu takaisin kirjautumisnäkymään.

Suunnitelman poistaminen vaatii vahvistuksen vahinkopainallusten takia, joten `Delete plan`-painiketta painettaessa sivulle tulee ponnahdusikkuna:

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/planDeletion.png "Confirmation")

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/planDeletion1.png "After deleting plan")

Suunnitelmanäkymän vasemmassa reunassa on eräänlainen menu toimintojen välille. Painikkeesta `TKT-courses` pääsee TKT-kurssien suunnitelmaan.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/freshplan.png "View before selections")

- Kurssisuunnitelma toimii siten, että ohjelma tarjoaa seuraavia kursseja jo suoritettujen kurssien myötä. Jo suoritetut kurssit voi valita laittamalla raksin ruutuun kunkin kurssin kohdalle, jonka on suorittanut ja painamalla `Save`. Painikkeen painamisen myötä sivun oikeaan reunaan ilmestyy `Suggested courses` -palkki, jonka alapuolelle tulee ehdotetut kurssit.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/suggestions.png "Suggestions from selections")

- Kurssien muokkaussivulle pääsee painamalla `Modify TKT-Courses`. Sivulla voi luoda uusia kursseja esitietoineen ja poistaa kursseja.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/modify.png "Modify")

- Kursseja voi poistaa valitsemalla poistettavat kurssit ja painamalla `Delete`-painiketta. Tämä poistaa kurssin kaikkalta järjestelmästä.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/deletingCourse.png "Course deleting")

- Esim. JTKT-kurssi poistui nyt nykyisestä suunnitelmasta eikä se voi ilmestyä enää missään.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/deletingCourse1.png "Effect of deletion")

- Kursseja voi luoda painamalla nappia `Create new`. Kurssin luomiseen tarvitaan kurssikoodi (pituus 5-13), nimi (pituus 3-50) sekä vapaavalintaiset esitietokurssit.

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/newCourse.png "New course")

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/newCourse1.png "Another course with prerequisites")

- Tämän jälkeen kurssit näkyvät suunnitelmanäkymässä:

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoohje/newCourse2.png "Planning after new courses")