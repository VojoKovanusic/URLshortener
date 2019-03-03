-aplikacija služi za pravljenje vlastitih skraćenih url-ova

-konfiguracija je uradjena uz pomoć spring boota, klasični RESTFul servis

-maven je korišten

-framevork: spring

-nikava baza podataka nije korištena, čak ni embedded(konflikte sa portovima imam), tako da neke stvari izgledaju malo drugačije od uobičajenih, pre svega se odnosi na servise DAO(I CRUDRepository) koji nedostaju
, tako da sam morao napraviti singlton @Bean users(), u koji sam smjestio sve korisnike i njihove podatke.

-svi pozivi idu preko http://localhost:8090

-projekat bi treba da radi odmah nakon pokretanja na vašoj mašini, deteljnije mogućnosti možete pogladti na 
http://localhost:8090/help) koja sadrži upute za pokretanje i korištenje.

-za provjeru rada koristio sam i preporučujem postman, gdje se morate registrovat prvo kao korisnik, potom ulogovati, gdje dobijate vaš token...koji koristite u daljim radnjama, 
a i help stranica je daleko preglednija.

