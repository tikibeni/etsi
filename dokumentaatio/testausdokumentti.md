# Testausdokumentti

Ohjelmaa on testattu manuaalisesti luoduilla JUNIT-testiluokilla.

## Domain-pakkaus

Domain-paketin testaus koostuu kuudesta eri testiluokasta.

PlanTest.java sekä CourseTest.java testaavat kummankin vastaavaa luokkaa perustoimintojen osalta.

Monimutkaisin testattava osuus domain-paketista on itse sovelluslogiikan toiminnasta vastaava PlanService-luokka, jota testataan kahden testiluokan avulla: PlanServicePlanTest.java sekä PlanServiceCourseTest.java, jotka testaavat PlanServicen toimintaa luokan Plan sekä luokan Course toimintojen osalta.

Koska PlanService koostuu kahdesta DAO-interfacesta (PlanDao, CourseDao), niin domain-pakettiin on luotu oikeiden DAO-interfacejen toimintoja simuloivat CourseDaoTest.java sekä PlanDaoTest.java. Itse nämä luokat eivät testaa mitään, vaan tarjoavat toimintoja ylempien luokkien testausta varten. Kaikenkaikkiaan PlanServiceä testataan siis kahden laajemman PlanServicen toimintoihin keskittyvän luokan (PlanServicePlanTest, PlanServiceCourseTest), niiden toimintojen mahdollistavien rajapintaluokkien (CourseDaoTest, PlanDaoTest) sekä lopulta olioluokkien perustoimintoja käyttävien luokkien (PlanTest, CourseTest) avulla.


## DAO-pakkaus

DAO-paketin testaus koostuu kahdesta eri testiluokasta, jotka simuloivat tiedostojen käsittelyä.

CourseFileDaoTest.java simuloi courses.txt:n tiedostonhallintatoimintoja. Luokka luo itselleen väliaikaiskansion ja tiedoston sen sisälle, johon se testaa erilaisia CoursesDao.java-luokan toimintoja. Näin luokka testaa samalla myös CourseDaon toimintoja. Luokka käyttää tiedoston alustukseen ulkoista kirjastoa org.apache.commons.io.FileUtils.

PlanFileDaoTest.java simuloi plans.txt:n tiedostonhallintatoimintoja. Luokka luo itselleen väliaikaiskansion ja tiedoston sen sisälle, johon se testaa PlanDao-interfaceluokan toimintoja. Näin luokka testaa tiedostonhallintaa sekä PlanDao-toiminnallisuuksia.


## Huomioita testauksesta

Itse UI:n toiminnallisuudet ja niiden loogisuus on käyttäjätestattu eikä UI:lle ole testiluokkaa.

Testien testauskattavuus on tyydyttävällä tasolla, tosin olisin halunnut käyttää niiden tekemiseen enemmän aikaa. Tällä hetkellä ne ovat varsin yksinkertaisella tasolla, mutta testaavat valtaosaa toiminnallisuuksista.

PlanServicen uusia järjestelmän kursseja muokkaavia metodeja en ehtinyt tähän deadlineen mennessä testauttamaan JUNITilla vielä.

Jacoco-raportti:

![alt-text](https://github.com/tikibeni/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testaus/jacocoReport.png "Test report")