# Sovellusarkkitehtuuri

Riippuvuudet on merkitty katkoviivalla. Näin ollen planapp.ui riippuu planapp.domain:sta ja planapp.domain riippuu planapp.dao:sta.

Ui-luokka käyttää domainin luokkia PlanService, Plan ja Course.

Domainin PlanService käyttää PlanDao:ta ja CourseDao:ta.

## Luokka- ja pakkauskaavio

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kaaviot.png "Kaavio")

## Sekvenssikaaviot sovelluksen perustoiminnallisuuksista

### Rekisteröinti

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/registerationSequence.PNG "Rekisteröintikaavio")

### Kirjautuminen

![alt text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/loginSequence.PNG "Kirjautumiskaavio")