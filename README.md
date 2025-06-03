# 🎬 Movie Management Application

Detta projekt är en enkel Java-applikation som hanterar en samling filmer. Det bygger på **Jakarta EE 11** och erbjuder ett REST API för att skapa, läsa, uppdatera och ta bort filmer (CRUD).

## 🔧 Vad applikationen gör

Med denna applikation kan du:

- Skapa nya filmer
- Hämta alla eller specifika filmer
- Uppdatera information om en film
- Radera filmer
- Filtrera filmer baserat på titel, regissör eller längd

## 🧱 Projektstruktur

Applikationen är uppdelad i olika lager för att hålla koden organiserad:

- **Entity:** `Movie` är modellen som sparas i databasen.
- **DTO:** Används för att ta emot/skicka data via API:t (`MovieDTO`, `CreateMovieDTO`, `UpdateMovieDTO`).
- **Mapper:** Konverterar mellan `Movie` och DTO:er.
- **Repository:** Ansluter till databasen med hjälp av Jakarta Data.
- **Service:** Innehåller affärslogik och hanterar datavalidering.
- **REST Resource:** Hanterar HTTP-förfrågningar från klienten.

## ✅ Validering

Alla fält valideras med Jakarta Bean Validation. Exempel:

- Titel och regissör måste vara ifyllda
- Längd måste vara större än 0
- Utgivningsdatum måste vara giltigt

## 🐳 Docker

Applikationen kan köras med Docker och PostgreSQL:

- `Dockerfile` för att bygga Java-applikationen
- `docker-compose.yml` för att starta både backend och databas

## ↺ Commits 

Jag förstår inte varför mina commits är på all gång, eftersom jag comitta
i detta projektet i delar! Men ändå visar det att commitsen är gjort på en gång...
Som tur så noterade jag commitsen i min mobile notes varje gång jag gjorde en commit då 🤓

!!OBS... de här commits är från "Add Movie entity class with validation and JPA annotations" tills "Docker finsished setup"


2025-06-01

- Commit 3 Kl 22:10 
- Commit 4 Kl 22:23
- Commit 5 Kl 22:42
- Commit 6 Kl 22:48
- Commit 7 Kl 23:03
- Commit 8 Kl 23:27
- Commit 9 Kl 23:53
- Commit 10 Kl 00:30
- Commit 11 Kl 01:18
- Commit 12 Kl 01:41
- Commit 13 Kl 02:17

2025-06-02

- Commit 14 Kl 14:27
- Commit 15 Kl 15:21
- Commit 16 kl 20:27
- Commit 17 Kl 20:57
- Commit 18 Kl 20:58
- Commit 19 Kl 21:04
- Commit 20 Kl 21:12
- Commit 21 Kl 21:19
- Commit 22 Kl 21:56
- Commit 23 Kl 22:30
- Commit 24 Kl 23:18

2025-06-03

- Commit 25 Kl 03:20
- Commit 26 Kl 03:24
- Commit 27 Kl 03:25
- Commit 28 Kl 03:26
- Commit 29 Kl 03:28
- Commit 30 Kl 03:29
- Commit 31 Kl 03:30
- Commit 32 Kl 03:44
- Commit 33 Kl 04:36 (Updaterad README.md)