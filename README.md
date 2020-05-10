# Ohjelmistotekniikka, kevät 2020
Tällä kurssilla toteutan kevytmuotoisen seurantaohjelman opintojen suunnittelua varten käyttäen Javaa.

## ETSI (Esitietojen Tarkistus ja Seuranta Instrumentti)

Sovelluksen ideana on auttaa käyttäjää opintojen suunnittelussa kurssien esitietovaatimuksiin pohjautuen.

Toimintamalli perustuu siihen, että käyttäjä voi suunnitelmassaan valita itselleen ainekohtaisesti jo suoritetut kurssit, jonka myötä ohjelma ehdottaa esitietovaatimuksiin pohjautuen seuraavaksi valittavia kursseja.

Näin sovelluksesta voi varsin helposti tarkistaa ilman suurempia tiedon kaivamisia, mitä kursseja voi mahdollisesti valita seuraavaksi.
Aluksi ohjelma tulee toimimaan TKT-kurssien osalta näin, jonka myötä lisään ohjelmaan muiden ainekokonaisuuksien kursseja ja esitietovaatimuksia.

## Dokumentaatio

Luethan käyttöohjeen ennen ohjelman käsittelyä:

Käyttöohje: [kayttoohje.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

Vaatimusmäärittely: [vaatimusmaarittely.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

Sovellusarkkitehtuuri: [arkkitehtuuri.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

Testausdokumentti: [testausdokumentti.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

Tuntikirjanpito: [tuntikirjanpito.md](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

Viimeisin [täältä](https://github.com/tikibeni/ot-harjoitustyo/releases)

Kurssin _Ohjelmistotekniikka_ loppupalautus: [tästä](https://github.com/tikibeni/ot-harjoitustyo/releases/tag/v1.2)

## Komentorivitoiminnot

### Ohjelman ajaminen

Ohjelman voi vaihtoehtoisesti ajaa konsolista hakemiston _PlanApp/_ sisällä komennolla:

`mvn compile exec:java -Dexec.mainClass=planapp.Main`

### Testit

Testit voi ajaa terminaalista komennolla: 

`mvn test`

Kattavuusraportti _index.html_ testeistä sovelluksen kansioon _PlanApp/target/site/jacoco/_ komennolla: 

`mvn jacoco:report`

### Ohjelman jar-tiedoston luominen

Sovelluksen hakemistoon _PlanApp/target/_ voi luoda suoritettavan .jar-tiedoston (sen jossa ei lue original) ohjelmasta komennolla:

`mvn package`

### Checkstyle

Checkstyle-asetukset on määritelty _checkstyle.xml_ -tiedostossa. Checkstyle ei tarkista PlanAppUi-luokkaa, kuten `skipped_files.xml`-tiedostossa on määritetty.

Näin checkstylen ajaminen tarvitsee _checkstyle.xml_- ja `skipped_files.xml`-tiedoston suoritushakemistoon toimiakseen oikein.

`mvn jxr:jxr checkstyle:checkstyle`

Checkstyleraportti _checkstyle.html_ sovelluksen kansioon _PlanApp/target/site/_

### JavaDoc

JavaDocin tiedostot voi luoda kansioon _PlanApp/target/site/apidocs/_ komennolla

`mvn javadoc:javadoc`