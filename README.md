# Film API med JakartaEE och Docker Compose

## Beskrivning av implementeringen

Detta projekt är en RESTful API byggd med JakartaEE som tillhandahåller information om filmer. API:et gör det möjligt att hantera filmer, inklusive att lägga till, uppdatera, hämta och ta bort filmer från en **PostgreSQL**-databas. Applikationen använder Jakarta EE för att implementera REST API:et och Jakarta Persistence (JPA) för att hantera databasinteraktioner.

API:et körs i en Docker-miljö med hjälp av **Docker Compose**, där både PostgreSQL-databasen och applikationen körs som separata tjänster i container.

### Funktionalitet
- **GET /movies** - Hämta alla filmer.
- **GET /movies/{id}** - Hämta en specifik film baserat på ID.
- **POST /movies** - Lägg till en ny film.
- **PUT /movies/{id}** - Uppdatera en befintlig film baserat på ID.
- **DELETE /movies/{id}** - Ta bort en film baserat på ID.

Applikationen använder **PostgreSQL** som databas för att lagra filmdata och Jakarta Persistence (JPA) för att hantera dataåtkomst.

### Teknologier som används
- Jakarta EE
- JAX-RS för att skapa REST API:et
- Jakarta Persistence (JPA) för databasinteraktion
- PostgreSQL som databas
- Docker och Docker Compose för att orkestrera tjänsterna
- Maven för byggsystem

## Instruktioner för att bygga och köra applikationen med Docker Compose

### Förutsättningar
För att köra applikationen krävs följande:
- **Docker** och **Docker Compose** måste vara installerade på din dator. Följ installationen från [Docker's officiella sida](https://docs.docker.com/get-docker/).
- **Java 23** och **Maven** krävs för att bygga applikationen lokalt (valfritt).

### Steg för att sätta upp Docker Compose

1. **Klona projektet:**
   Först måste du klona projektet från GitHub:
   ```bash
   git clone https://github.com/kirre02/rest-labb1.git
   cd rest-labb1
