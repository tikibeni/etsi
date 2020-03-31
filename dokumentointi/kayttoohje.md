# Käyttöohje

## Konfigurointi

Tällä hetkellä ohjelma tarvitsee toimiakseen sovelluksen juurihakemistoon tiedoston "config.file", jonka sisältö on seuraava:

plans = plans.txt

Tämän avulla ohjelma tallentaa luodut suunnitelmat plans.txt -tiedostoon.

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

Tällä hetkellä suunnitelmanäkymässä voi kirjautua ulos painikkeesta "Logout" ja poistaa suunnitelma painikkeesta "Delete plan"

Kummastakin painikkeesta näkymä vaihtuu takaisin kirjautumisnäkymään.