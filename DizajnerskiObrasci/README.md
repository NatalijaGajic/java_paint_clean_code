## Opis zahteva za projekat iz predmeta Reinženjering informacionih sistema

### Zadatak
1. Student preuzeti kod pokriva testovima. Testovi treba da služe da olakšaju dalji reinženjering aplikacije, kao i za otkrivanje grešaka u kodu. Obavezna je upotreba alata po izboru studenta za merenje pokrivenosti koda testovima.
2. Vrši se reinženjering aplikacije po pravilima CleanCode-a koja se obrađuju na vežbama.
3. Krajnji ishod treba da bude aplikacija koja radi po specifikaciji aplikacije sa predmeta Dizajnerski obrasci, bez grešaka, koja je pokrivena testovima i napisana u skladu sa CleanCode-om.

### Specifikacija aplikacije sa predmeta Dizajnerski obrasci
Korišćenjem *Java Swing* implementirati desktop aplikaciju za rad sa 2D grafikom. 
Zahtevi:
1. crtanje oblika različitim bojama (boja ivice i boja unutrašnjosti) za šta je potrebno koristiti *JColorChooser* klasu,
2. nazivi klasa, metoda i promenljivih moraju biti na engleskom jeziku,
3. aplikacija mora biti realizovana u skladu sa MVC arhitektonskim obrascem,
4. dodavanje, brisanje i modifikacija šestougla (hexagon) koristeći Adapter obrazac za hexagon.jar,
5. poništavanje izvršenih komandi (*undo* funkcionalnost) – *Command* i *Prototype* obrazac, ponovno izvršenje poništenih komandi (*redo* funkcionalnost) – *Command* i *Prototype* obrazac, *undo* i *redo* dugme treba da budu dostupni samo kada je dostupna i funkcionalnost,
6. generisanje i prikaz loga izvršenih komandi u okviru aplikacije,
7. zapis u tekstualnu datoteku loga izvršenih komandi na eksterni memorijski medijum, zapis kompletnog crteža (*Serialization*) na eksterni memorijski medijum, - *Strategy* obrazac,
8. učitavanje tekstualne datoteke koja sadrži log izvršenih komandi i na osnovu sadržaja, kreiranje odgovarajućeg crteža, komandu po komandu u interakciji sa korisnikom, učitavanje kompletnog crteža,
9. promenu pozicije oblika po Z osi, *To Front* (pozicija po pozicija), *To Back* (pozicija po pozicija), *Bring To Front* (na najvišu poziciju), *Bring To Back* (na najnižu poziciju),
10. prikazati trenutno aktivne boje za crtanje ivice i popunjavanje oblika, klikom na boju, otvara se dijalog sa mogućnošću promene trenutno aktivne boje,
11. omogućiti selekciju više oblika,
12. dugme za brisanje treba da bude dostupno samo u slučaju da postoje selektovani objekti – *Observer* obrazac,
13. dugme za modifikaciju treba da bude dostupno samo u slučaju kada je selektovan samo jedan oblik – *Observer* obrazac.

