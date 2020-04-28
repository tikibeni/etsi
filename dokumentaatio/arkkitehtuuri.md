# Sovellusarkkitehtuuri

## Näkymät

Sovelluksessa on tällä hetkellä kolme eri näkymää: kirjautuminen, rekisteröinti ja itse sovelluksen päänäkymä.

## Luokka- ja pakkauskaavio

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/kaaviot.png "Kaavio")

Sovelluksen pääasialliset olioluokat ovat Plan sekä Course. Plan, eli suunnitelma voi sisältää useamman valitun/suoritetun kurssin, kun taas sama kurssi voi kuulua useampaan suunnitelmaan.

Riippuvuudet on merkitty katkoviivalla. Näin ollen planapp.ui riippuu planapp.domain:sta ja planapp.domain riippuu planapp.dao:sta.

UI-luokka käyttää domainin luokkia PlanService, Plan ja Course.

Domainin PlanService käyttää PlanDao:ta ja CourseDao:ta.

## Sekvenssikaaviot sovelluksen perustoiminnallisuuksista

### Rekisteröinti

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/regSequence.png "Rekisteröintikaavio")

Rekisteröintinäkymään voidaan siirtyä kirjautumisnäkymästä nappia painamalla.

Rekisteröinti tapahtuu käyttäjän toimesta rekisteröintinäkymässä sopivat tiedot syötettyään ja rekisteröintinappia painamalla. 
Tällöin UI saa tiedon, että rekisteröintiä yritetään, jonka myötä se antaa syötetyt tiedot planService-luokalle, joka tarkistaa planDaon avulla tietokannasta onko tietoja vastaavaa suunnitelmaa jo olemassa. Mikäli suunnitelmaa ei entuudestaan ole olemassa, planService luo uuden Plan-olion ja syöttää sen planDaolle kantaan tallentamista varten. Ketjun onnistuttua näkymä vaihtuu takaisin kirjautumisnäkymään.

### Kirjautuminen

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/arkkitehtuuri/logSequence.png "Kirjautumiskaavio")

Kirjautuminen tapahtuu käyttäjän toimesta kirjautumisnäkymässä nappia painamalla.

Kun käyttäjä on syöttänyt kirjautumiseen suunnitelman nimen ja painaa nappia, UI lähettää kirjautumistiedot planService-luokalle, joka tarkistaa planDaolta, löytyykö tietokannasta kyseisen nimistä suunnitelmaa. Mikäli suunnitelma on olemassa, planDao palauttaa suunnitelman palvelulle, joka myöntää UI:lle luvan vaihtaa näkymä kirjautumisnäkymästä päänäkymään. Ketjun onnistuttua näkymä vaihtuu kirjautumisnäkymästä suunnitelma-/päänäkymään.

### Kurssin syöttäminen suunnitelmaan

![alt-text]( "Kurssin syöttökaavio")

Kurssin tallentaminen suunnitelmaan tapahtuu suunnitelmanäkymästä checkboxeja valitsemalla ja painamalla Save-nappia.