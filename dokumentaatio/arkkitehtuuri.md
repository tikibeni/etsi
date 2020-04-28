# Sovellusarkkitehtuuri

## Näkymät

Sovelluksessa on tällä hetkellä kolme eri näkymää: kirjautuminen, rekisteröinti ja itse sovelluksen päänäkymä.

## Luokka- ja pakkauskaavio

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/kaaviot.png "Kaavio")

Sovelluksen pääasialliset olioluokat ovat Plan sekä Course. Plan, eli suunnitelma voi sisältää useamman valitun/suoritetun kurssin, kun taas sama kurssi voi kuulua useampaan suunnitelmaan.

Riippuvuudet on merkitty katkoviivalla. Näin ollen planapp.ui riippuu planapp.domain:sta ja planapp.domain riippuu planapp.dao:sta.

UI-luokka käyttää domainin luokkia PlanService, Plan ja Course.

Domainin PlanService käyttää PlanDao:ta ja CourseDao:ta.

## Tiedon käsittely ja tallennus

Sovellus tallentaa tietoa suunnitelmista ja kursseista DAO-rajapintojen (planDao, courseDao) toteuttavien luokkien (planFileDao, courseFileDao) avulla. Nämä DAO-luokat toteuttavat sovelluksen palvelun (planService).

Tämän toimiakseen sovelluksessa on määritetty konfiguroinnit tiedostoon _config.file_, joka määrittää tiedostojen nimet (plans.txt, courses.txt) ja sisältää kaikkien kurssien ennaltamääritetyt tiedot.

Näin jokaisen käynnistyksen yhteydessä sovellus kutsuu DAO-luokkien konstruktoreita, jotka joko luovat puuttuvat tiedostot, mahdollisesti kirjoittavat niihin (kuten courses.txt:n tapauksessa) jonka myötä palvelu saa käyttöönsä listoina kaikki tiedostoissa (tietokannassa) olevat tiedot.

Uutta tietoa tallennettaessa palvelu kokoaa tallennettavat tai poistettavat oliot kasaan, jonka myötä sovellus tallentaa DAO-luokkia hyödyntäen muutokset tiedostoon.

## Sekvenssikaaviot sovelluksen perustoiminnallisuuksista

### Rekisteröinti

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/regSequence.png "Rekisteröintikaavio")

Rekisteröintinäkymään voidaan siirtyä kirjautumisnäkymästä nappia painamalla.

Rekisteröinti tapahtuu käyttäjän toimesta rekisteröintinäkymässä sopivat tiedot syötettyään ja rekisteröintinappia painamalla. 
Tällöin UI saa tiedon, että rekisteröintiä yritetään, jonka myötä se antaa syötetyt tiedot planService-luokalle, joka tarkistaa planDaon avulla tietokannasta onko tietoja vastaavaa suunnitelmaa jo olemassa. Mikäli suunnitelmaa ei entuudestaan ole olemassa, planService luo uuden Plan-olion ja syöttää sen planDaolle kantaan tallentamista varten. Ketjun onnistuttua näkymä vaihtuu takaisin kirjautumisnäkymään.

### Suunnitelman poistaminen

Suunnitelman poistaminen tapahtuu käyttäjän toimesta Delete plan -nappia painamalla suunnitelmanäkymässä. 

Tämän myötä UI pyytää palvelua poistamaan nykyisen suunnitelman. Palvelu hakee suunnitelman nimen ja siihen pohjautuen pyytää planDaota poistamaan suunnitelman ja siihen liittyvät tiedot tietokannasta. Lopulta UI vaihtaa näkymänsä kirjautumisruutuun.

### Kirjautuminen

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/logSequence.png "Kirjautumiskaavio")

Kirjautuminen tapahtuu käyttäjän toimesta kirjautumisnäkymässä nappia painamalla.

Kun käyttäjä on syöttänyt kirjautumiseen suunnitelman nimen ja painaa nappia, UI lähettää kirjautumistiedot planService-luokalle, joka tarkistaa planDaolta, löytyykö tietokannasta kyseisen nimistä suunnitelmaa. Mikäli suunnitelma on olemassa, planDao palauttaa suunnitelman palvelulle, joka myöntää UI:lle luvan vaihtaa näkymä kirjautumisnäkymästä päänäkymään. Ketjun onnistuttua näkymä vaihtuu kirjautumisnäkymästä suunnitelma-/päänäkymään.

### Uloskirjautuminen

Uloskirjautuminen tapahtuu käyttäjän toimesta suunnitelmanäkymästä logout-nappia painamalla.

Tämän myötä UI pyytää palvelua resetoimaan nykyisen suunnitelman attribuutin tyhjäksi. Lopulta UI vaihtaa näkymänsä kirjautumisruutuun.

### Kurssin syöttäminen suunnitelmaan

![alt-text]( "Kurssin syöttökaavio")

Kurssin tallentaminen suunnitelmaan tapahtuu suunnitelmanäkymästä checkboxeja valitsemalla ja painamalla Save-nappia.

Tämän jälkeen UI lähettää checkboxien tiedot palvelulle, joka hakee Course-oliot courseDaolta. Tämän tehtyään UI pyytää palvelua vertailemaan nyt haettuja kursseja suunnitelman valittuihin kursseihin. Mikäli jokin juuri valituista kursseista ei löydy selectedCourses-listasta, niin palvelua pyydetään lisäämään kurssi nykyiseen suunnitelmaan. Tämän myötä palvelu lisää kurssin suunnitelmaan ja pyytää planDaota kirjoittamaan päivityksen tietokantaan.

### Kurssin poistaminen suunnitelmasta

Kurssin poistaminen suunnitelmasta tapahtuu suunnitelmanäkymästä checkboxien valintoja poistamalla ja painamalla Save-nappia.

Tämän myötä UI tekee lähes identtisen ketjun kuin em. Kurssien syöttämisessä, mutta lisäilyjen ja valintojen puuttumisen sijaan, se poistaa nykysuunnitelmasta kurssit joita ei ole nyt valittuna checkboxeissa. Lopulta planService (palvelu) pyytää planDaota tallentamaan muutokset tietokantaan.

### Kurssiehdotuksien toimintaperiaate

Kurssiehdotusten toimintaperiaate perustuu aiemmin esiteltyihin kurssivalintojen syöttämiseen ja poistamiseen ja aktivoituu siis painamalla nappia Save.

Tämän pohjalta UI alkaa palvelun kanssa tarkistamaan, mitä kursseja käyttäjälle voidaan ehdottaa. UI hakee kurssien esitietovaatimukset ja vertailee niitä aktiivisesti nykyisen suunnitelman valittuihin kursseihin. Mikäli kaikki esitiedoiksi merkityt kurssit on suoritettu, kurssia voidaan ehdottaa. Tämän myötä UI hakee kultakin sopivalta kurssilta tiedot ja näyttää ne käyttäjälle listana.
