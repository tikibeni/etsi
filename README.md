# Ohjelmistotekniikka, kevät 2020
Tällä kurssilla toteutan kevytmuotoisen seurantaohjelman opintojen suunnittelua varten käyttäen Javaa.


## Opintojen suunnittelutyökalu

Sovelluksen ideana on auttaa käyttäjää opintojen suunnittelussa.

Toimintamalli perustuu siihen, että käyttäjä voi suunnitelmassaan valita itselleen ainekohtaisesti jo suoritetut kurssit, jonka myötä ohjelma ehdottaa esitietovaatimuksiin pohjautuen seuraavaksi valittavia kursseja.

Näin sovelluksesta voi varsin helposti tarkistaa ilman suurempia tiedon kaivamisia, mitä kursseja voi mahdollisesti valita seuraavaksi.
Aluksi ohjelma tulee toimimaan TKT-kurssien osalta näin, jonka myötä lisään ohjelmaan muiden ainekokonaisuuksien kursseja ja esitietovaatimuksia.

## Dokumentaatio

Vaatimusmäärittely: [vaatimusmaarittely.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

Tuntikirjanpito: [tuntikirjanpito.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)


### Komentorivitoiminnot

### Ohjelman ajaminen

Ohjelman voi vaihtoehtoisesti ajaa konsolista hakemiston /PlanApp sisällä komennolla:

```mvn compile exec:java -Dexec.mainClass=planapp.Main```

### Testit

Testit voi ajaa terminaalista komennolla: 

```mvn test```

Kattavuusraportti index.html testeistä sovelluksen kansioon PlanApp/target/site/jacoco/: 

```mvn jacoco:report```

### Ohjelman jar-tiedoston luominen

Sovelluksen hakemistoon PlanApp/target/ voi luoda suoritettavan jar-tiedoston ohjelmasta komennolla:

```mvn package```