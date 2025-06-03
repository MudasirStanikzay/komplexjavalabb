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