# Library Management System

Višekorisnički desktop sistem za upravljanje bibliotekom, razvijena u Java-i sa client-server arhitekturom.

## 📋 Sadržaj

- [Pregled](#pregled)
- [Karakteristike](#karakteristike)
- [Tehnologije](#tehnologije)
- [Arhitektura](#arhitektura)
- [Instalacija](#instalacija)
- [Konfiguracija](#konfiguracija)
- [Pokretanje](#pokretanje)
- [Upotreba](#upotreba)
- [Baza podataka](#baza-podataka)
- [API Operacije](#api-operacije)
- [Struktura projekta](#struktura-projekta)
- [Primenjene tehnike i obrascI](#primenjene-tehnike-i-obrasci)

---

## 🎯 Pregled

**Library Management System** je distribuirana desktop aplikacija koja omogućava bibliotekama da upravljaju:

- **Knjigama** - dodavanje, pretraga, brisanje, upravljanje asortimanom
- **Autorima** - evidencija autora i povezivanje sa knjigama
- **Članovima** - registracija članova, upravljanje članskom kartom i validsošću članarine
- **Pozajmicama** - evidentiranje pozajmljenih knjiga, praćenje roka vraćanja
- **Prijem računima** - logovanje nabavke novih knjiga
- **Bibliotekara** - upravljanje korisnicima sistema sa login autentifikacijom

Sistem koristi **client-server arhitekturu** gde desktop klijent komunicira sa centralizovanim serverom koji upravlja bazom podataka.

---

## ✨ Karakteristike

### Klijentske mogućnosti
- ✅ Bezbedan login za bibliotekare
- ✅ Grafički interfejs (GUI) sa Swing framework-om
- ✅ CRUD operacije za sve entitete
- ✅ Napredna pretraga i filtriranje podataka
- ✅ Upravljanje pozajmicama sa rokom vraćanja
- ✅ Prikaz isteklih pozajmica
- ✅ Produženje članske validsosti
- ✅ Upravljanje asortimanom - brojanje raspoloživih primjeraka

### Serverske mogućnosti
- ✅ Višekorisnički pristup - simultani rad više klijenata
- ✅ Upravljanje sesijama i stanjem klijenta
- ✅ Perzistencija podataka u relacijskoj bazi (MySQL/MariaDB)
- ✅ Poslovni sloj sa validacijom
- ✅ Transakcijska konzistentnost
- ✅ Bezbedna komunikacija preko TCP socket-a
- ✅ Logovanje aktivnosti korisnika

---

## 🛠️ Tehnologije

| Komponenta | Tehnologija | Verzija |
|-----------|-----------|---------|
| **Jezik** | Java | 24 |
| **GUI Framework** | Swing | JDK 24 |
| **Baza Podataka** | MySQL / MariaDB | 10.4.32+ |
| **JDBC Drajver** | MySQL Connector/J | 8.0.31 |
| **Build sistem** | Apache Ant | Gradle kompatibilan |
| **IDE** | NetBeans | Prenosiv na Eclipse/IntelliJ |
| **Komunikacija** | Java Socket API (TCP) | - |
| **Serijalizacija** | Java Object Serialization | - |

---

## 🏗️ Arhitektura

### Sistemska arhitektura

```
┌─────────────────────────────────────────────────────────────┐
│                    CLIENT LAYER (Swing GUI)                │
│                                                              │
│  ┌────────────┐  ┌────────────┐  ┌────────────┐           │
│  │ LoginForm  │  │  MainForm  │  │ ViewForms  │ ...        │
│  └────────────┘  └────────────┘  └────────────┘           │
│         │               │              │                    │
│         └───────────────┴──────────────┘                    │
│                    │                                        │
│              ┌─────────────┐                               │
│              │ Controller  │  (Singleton pattern)          │
│              └─────────────┘                               │
│                    │                                        │
└─────────────────────────────────────────────────────────────┘
                        │
                   ┌────────────┐
                   │Communication│ (Sender/Receiver)
                   └────────────┘
                        │
                   TCP Socket (Port 9000)
                        │
┌─────────────────────────────────────────────────────────────┐
│                   SERVER LAYER                              │
│                                                              │
│  ┌─────────────────────────────────────────────────────┐  │
│  │              ServerThread                           │  │
│  │  (Accepts client connections on port 9000)         │  │
│  └─────────────────────────────────────────────────────┘  │
│                        │                                   │
│     ┌──────────────────┼──────────────────┐               │
│     ▼                  ▼                  ▼               │
│  ┌──────────┐      ┌──────────┐      ┌──────────┐       │
│  │ClientH1  │      │ClientH2  │      │ClientH3  │ ...   │
│  └──────────┘      └──────────┘      └──────────┘       │
│     (Thread)          (Thread)          (Thread)         │
│                                                          │
│  ┌────────────────────────────────────────────────────┐ │
│  │          Controller (Business Logic)               │ │
│  │                                                    │ │
│  │  ┌──────────────┐  ┌──────────────┐             │ │
│  │  │BookOperations│  │MemberOps ... │             │ │
│  │  └──────────────┘  └──────────────┘             │ │
│  └────────────────────────────────────────────────────┘ │
│                        │                                 │
│  ┌────────────────────────────────────────────────────┐ │
│  │          Repository (Data Access Layer)           │ │
│  │   - Database connection management                │ │
│  │   - JDBC queries                                  │ │
│  └────────────────────────────────────────────────────┘ │
│                        │                                 │
└─────────────────────────────────────────────────────────┘
                        │
                   ┌──────────┐
                   │  MySQL   │
                   │ MariaDB  │
                   └──────────┘
```

### Slojevi arhitekture

```
┌─────────────────────────────────────────────┐
│     Presentation Layer (GUI - Swing)       │
│  Forms + Event Listeners + Validators       │
└─────────────────────────────────────────────┘
              │              △
              │              │
              ▼              │
┌─────────────────────────────────────────────┐
│   Application Layer (Controller)           │
│  - Login/Logout                             │
│  - CRUD operacije                           │
│  - Poslovne validacije                      │
└─────────────────────────────────────────────┘
              │              △
              │              │
              ▼              │
┌─────────────────────────────────────────────┐
│  Domain Layer (SO - Service Operations)    │
│  - AbstractSO (template pattern)            │
│  - BookSO, MemberSO, LoanSO, itd.          │
│  - Poslovne operacije i validacije         │
└─────────────────────────────────────────────┘
              │              △
              │              │
              ▼              │
┌─────────────────────────────────────────────┐
│  Persistence Layer (Repository)            │
│  - Repository.java (factory pattern)        │
│  - Database CRUD                            │
│  - Connection pooling                       │
└─────────────────────────────────────────────┘
              │              △
              │              │
              ▼              │
┌─────────────────────────────────────────────┐
│     Database (MySQL/MariaDB)               │
│  - Relacijska baza sa normalizacijom       │
│  - Foreign keys i constraints              │
└─────────────────────────────────────────────┘
```

### Komunikacioni protokol

```
Klijent                           Server
   │                                 │
   │  ┌──────────────┐              │
   │  │ Request obj. │──────────► parse request
   │  └──────────────┘              │
   │     (serialize)            ┌───────────┐
   │                            │ClientHandler
   │                            │ Thread    │
   │                            └───────────┘
   │                                │
   │                           execute operation
   │                            via Controller
   │                                │
   │                        ┌───────────────────┐
   │                        │ Business logic &  │
   │                        │ Database access   │
   │                        └───────────────────┘
   │                                │
   │  ◄──────── Response object ────│
   │     (serialize)                │
   │  ┌──────────────┐              │
   │  │ Result obj.  │              │
   │  │ isSuccessful │              │
   │  │ Exception    │              │
   │  └──────────────┘              │
   │
  Display to user
```

---

## 📦 Instalacija

### Zahtevi

- **Java Development Kit (JDK)** verzija 24 ili viša
  - Za Java 17/21 LTS, prilagoditi `project.properties` fajlove
- **MySQL Server** verzija 5.7+ ili **MariaDB** verzija 10.4+
- **NetBeans IDE** (opciono, ali preporučeno) ili Eclipse/IntelliJ
- **Git** za kloniranje repozitorijuma

### Koraci instalacije

#### 1. Kloniranje repozitorijuma

```bash
git clone https://github.com/Barxh/library-management-system.git
cd library-management-system
```

#### 2. Instalacija JDK-a

**Windows:**
```bash
# Preuzmi sa https://www.oracle.com/java/technologies/downloads/
# Instalacija kroz GUI
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-24-jdk
```

**macOS:**
```bash
brew install openjdk@24
```

#### 3. Instalacija MySQL/MariaDB

**Windows:**
- Preuzmi sa https://dev.mysql.com/downloads/mysql/
- Ili https://mariadb.org/download/

**Linux (Ubuntu/Debian):**
```bash
sudo apt install mysql-server
# ili
sudo apt install mariadb-server
```

**macOS:**
```bash
brew install mysql
# ili
brew install mariadb
```

#### 4. Pokretanje baze podataka

```bash
# Linux/macOS
sudo systemctl start mysql
# ili
sudo systemctl start mariadb

# Windows - kroz Services ili Command Prompt (admin)
net start MySQL80
```

#### 5. Otvaranje projekta

**U NetBeans IDE:**
- File → Open Project
- Selektuj `SeminarskiCommon`, `SeminarskiServer`, `SeminarskiClient` redom
- Svaki folder je odvojen NetBeans projekat

---

## ⚙️ Konfiguracija

### 1. Konfiguracija baze podataka

Uredi `SeminarskiServer/config/dbconfig.properties`:

```properties
# Database connection
url=jdbc:mysql://localhost:3306/library
username=root
password=
```

**Primer sa lozinkom:**
```properties
url=jdbc:mysql://localhost:3306/library
username=root
password=vasalozinka
```

**Za MariaDB na drugom portu:**
```properties
url=jdbc:mysql://localhost:3307/library
username=root
password=
```

### 2. Konfiguracija servera

Uredi `SeminarskiServer/config/serverconfig.properties`:

```properties
port = 9000
```

Prosledi port ako je 9000 već u upotrebi:

```properties
port = 9001
```

### 3. Konfiguracija klijenta

Klijent automatski koristi server na `localhost:9000`. Ako se server pokreće na drugoj mašini ili portu, uredi u `SeminarskiClient/src/communication/Communication.java`:

```java
// Primer: Prosledi na drugom serveru
Socket socket = new Socket("192.168.1.100", 9000);
```

### 4. Import baze podataka

Kreiraj bazu i učitaj šemu sa podacima:

```bash
mysql -u root -p < baza.sql
```

Ili kroz MySQL Workbench:
1. File → Open SQL Script
2. Izaberi `baza.sql`
3. Execute

Provera:
```bash
mysql -u root -p
mysql> SHOW DATABASES;
mysql> USE library;
mysql> SHOW TABLES;
```

---

## 🚀 Pokretanje

### Opcija 1: Kroz NetBeans IDE

#### Pokretanje servera

1. U NetBeans, desni klik na `SeminarskiServer` projekat
2. Run → Run Project (ili F6)
3. Server se pokreće na portu 9000
4. Poruka: "Awaiting clients..."

#### Pokretanje klijenta

1. U NetBeans, desni klik na `SeminarskiClient` projekat
2. Run → Run Project (ili F6)
3. Otvara se `LoginForm`

### Opcija 2: Kroz komandnu liniju (Ant)

#### Build-ovati sve projekte

```bash
cd SeminarskiCommon
ant clean build
ant jar

cd ../SeminarskiServer
ant clean build
ant jar

cd ../SeminarskiClient
ant clean build
ant jar
```

#### Pokretanje servera

```bash
cd SeminarskiServer
java -cp "dist/SeminarskiServer.jar:../SeminarskiCommon/dist/SeminarskiCommon.jar:mysql-connector-j-8.0.31.jar:config" main.Server
```

#### Pokretanje klijenta

```bash
cd SeminarskiClient
java -cp "dist/SeminarskiClient.jar:../SeminarskiCommon/dist/SeminarskiCommon.jar" main.Client
```

### Opcija 3: Direktno pokretanje JAR fajlova

```bash
# Terminal 1 - Server
java -cp SeminarskiServer/dist/SeminarskiServer.jar:SeminarskiCommon/dist/SeminarskiCommon.jar:SeminarskiServer/mysql-connector-j-8.0.31.jar:SeminarskiServer/config main.Server

# Terminal 2 - Klijent 1
java -cp SeminarskiClient/dist/SeminarskiClient.jar:SeminarskiCommon/dist/SeminarskiCommon.jar main.Client

# Terminal 3 - Klijent 2 (opciono)
java -cp SeminarskiClient/dist/SeminarskiClient.jar:SeminarskiCommon/dist/SeminarskiCommon.jar main.Client
```

### Prvo pokretanje - Login podaci

Iz `baza.sql` imamo dva testna bibliotekara:

```
Korisnik 1:
  username: n
  password: 123

Korisnik 2:
  username: nikola
  password: 123
```

---

## 💻 Upotreba

### LoginForm

1. Unesite korisničko ime i lozinku
2. Kliknite "Login"
3. Ako je uspešan, prelazite na `MainForm`
4. Ako nije, poruka greške

### MainForm - Glavni interfejs

Navigacioni meni sa opcijama:

#### 📚 Knjige
- **Prikaži sve** - Prikazuje sve knjige u tabeli
- **Pretraži** - Pretraga po naslovu, autoru ili žanru
- **Dodaj novu** - Otvara `AddBookForm`
- **Obriši** - Brisanje izabrane knjige iz tabele

#### 👥 Članovi
- **Prikaži sve** - Lista svih članova
- **Pretraži** - Pretraga po imenu, prezimenu ili JMBG-u
- **Dodaj članara** - Registracija novog člana
- **Ažuriranje** - Editovanje podataka člana
- **Produženje članarine** - Produžuje validsost za 1 godinu

#### ✍️ Autori
- **Prikaži sve** - Lista autora
- **Pretraži** - Pretraga po imenu
- **Dodaj autora** - Dodavanje novog autora

#### 📤 Pozajmice
- **Prikaži sve** - Svi pozajmljeni evi
- **Pretraži** - Pretraga po članu, knjizi ili datumu
- **Dodaj pozajmicu** - Nova pozajmica članu
- **Vrati pozajmicu** - Vraćanje pozajmljene knjige
- **Istekle pozajmice** - Prikaz prekoračenih rokova

#### 💼 Prijem
- **Novi prijem** - Logovanje nabavke novih primjeraka
- **Dodaj stavke** - Dodavanje stavki u prijem

#### 🚪 Odjava
- **Logout** - Izlazak iz sistema

---

## 🗄️ Baza podataka

### Šema baze

#### Tabela `author`
```sql
CREATE TABLE author (
  authorID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  dateOfBirth DATE NOT NULL,
  PRIMARY KEY (authorID)
);
```

#### Tabela `book`
```sql
CREATE TABLE book (
  bookID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  genre VARCHAR(20) NOT NULL,
  totalQuantity INT UNSIGNED,
  stockQuantity INT UNSIGNED,
  PRIMARY KEY (bookID)
);
```

**Relacija book-author (Many-to-Many):**
```sql
CREATE TABLE authorbook (
  bookID BIGINT UNSIGNED NOT NULL,
  authorID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (bookID, authorID),
  FOREIGN KEY (bookID) REFERENCES book(bookID),
  FOREIGN KEY (authorID) REFERENCES author(authorID)
);
```

#### Tabela `member`
```sql
CREATE TABLE member (
  memberID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  JMBG VARCHAR(13) NOT NULL UNIQUE,
  dateOfBirth DATE NOT NULL,
  address VARCHAR(30) NOT NULL,
  city VARCHAR(30) NOT NULL,
  phone VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  membershipValidityDate DATE NOT NULL,
  PRIMARY KEY (memberID)
);
```

#### Tabela `loan`
```sql
CREATE TABLE loan (
  loanID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  loanDate DATE NOT NULL,
  memberID BIGINT UNSIGNED NOT NULL,
  bookID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (loanID),
  FOREIGN KEY (memberID) REFERENCES member(memberID),
  FOREIGN KEY (bookID) REFERENCES book(bookID)
);
```

#### Tabela `librarian`
```sql
CREATE TABLE librarian (
  librarianID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  username VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  PRIMARY KEY (librarianID)
);
```

#### Tabela `receipt`
```sql
CREATE TABLE receipt (
  receiptID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  releaseDate DATE NOT NULL,
  librarianID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (receiptID),
  FOREIGN KEY (librarianID) REFERENCES librarian(librarianID)
);
```

#### Tabela `itemreceipt`
```sql
CREATE TABLE itemreceipt (
  itemReceiptID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  purchasedQuantity INT UNSIGNED,
  bookID BIGINT UNSIGNED NOT NULL,
  receiptID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (itemReceiptID),
  FOREIGN KEY (bookID) REFERENCES book(bookID),
  FOREIGN KEY (receiptID) REFERENCES receipt(receiptID)
);
```

### Relacijski dijagram

```
                      author (1)
                         │
                         │ N:M
                         │
    ┌─────────────────────────────────────┐
    │          authorbook (Junction)      │
    └─────────────────────────────────────┘
            │                        │
            └────────────┬───────────┘
                         │ N:1
                         │
                      book (1)
                         │ 1:N
                         │
                      loan (N)
                         │ N:1
                         │
                    member (1)
                         │ 1:N
                         │
                  librarian (1)
                         │
                         │ 1:N
                         │
                      receipt (N)
                         │ 1:N
                         │
                   itemreceipt (N)
                         │ N:1
                         │
                    book (1)
```

---

## 🔄 API Operacije

### Operation Enum

Sve dostupne operacije definirane su u `communication/Operation.java`:

```java
public enum Operation {
    // Authentication
    LOGIN,
    LOGOUT,
    
    // Authors
    GET_ALL_AUTHORS,
    GET_AUTHORS_BY_QUERY,
    ADD_AUTHOR,
    
    // Books
    GET_ALL_BOOKS,
    GET_BOOKS_BY_QUERY,
    ADD_BOOK,
    DELETE_BOOK,
    
    // Members
    GET_ALL_MEMBERS,
    GET_MEMBERS_BY_QUERY,
    ADD_MEMBER,
    UPDATE_MEMBER,
    MEMBERSHIP_RENEWAL,
    
    // Loans
    ADD_LOAN,
    DELETE_LOAN,
    GET_ALL_LOANS,
    GET_LOAN_BY_QUERY,
    GET_EXPIRED_LOANS,
    
    // Receipts
    ADD_RECEIPT
}
```

### Request-Response protokol

**Request objekt:**
```java
public class Request implements Serializable {
    private Operation operation;
    private Object argument;
}
```

**Response objekt:**
```java
public class Response implements Serializable {
    private Object result;           // Rezultat operacije
    private boolean isSuccessfull;  // Uspešnost
    private Exception exception;    // Greška ako je bilo
}
```

### Primeri korišćenja na klijentskoj strani

```java
// Login
Librarian librarian = new Librarian();
librarian.setUsername("nikola");
librarian.setPassword("123");

Request request = new Request(Operation.LOGIN, librarian);
Communication.getInstance().sendRequest(request);
Response response = Communication.getInstance().receiveResponse();

if (response.isIsSuccessfull()) {
    Librarian loggedUser = (Librarian) response.getResult();
    System.out.println("Ulogovani korisnik: " + loggedUser.getFirstName());
} else {
    System.out.println("Greška: " + response.getException().getMessage());
}
```

```java
// Pretraga knjiga
String searchQuery = "Milan";
Request request = new Request(Operation.GET_BOOKS_BY_QUERY, searchQuery);
Communication.getInstance().sendRequest(request);
Response response = Communication.getInstance().receiveResponse();

List<Book> books = (List<Book>) response.getResult();
for (Book book : books) {
    System.out.println(book.getTitle() + " - " + book.getGenre());
}
```

---

## 📁 Struktura projekta

### SeminarskiCommon (Zajednički moduli)

```
SeminarskiCommon/
├── src/
│   ├── communication/
│   │   ├── Operation.java         # Enum svih mogućih operacija
│   │   ├── Request.java           # Zahtev od klijenta
│   │   ├── Response.java          # Odgovor od servera
│   │   ├── Sender.java            # Slanje objekata preko socket-a
│   │   ├── Receiver.java          # Primanje objekata iz socket-a
│   │   └── Communication.java     # Singleton za upravljanje komunikacijom
│   │
│   └── domain/                    # Modeli podataka
│       ├── AbstractDomain.java    # Bazna klasa sa ID
│       ├── Author.java
│       ├── Book.java
│       ├── Librarian.java
│       ├── Member.java
│       ├── Loan.java
│       ├── Receipt.java
│       ├── ItemReceipt.java
│       ├── AuthorBook.java
│       └── Genre.java             # Enum žanrova
│
├── build.xml                      # Ant build skript
├── build/                         # Kompajlirani .class fajlovi
└── dist/                          # SeminarskiCommon.jar
```

### SeminarskiServer (Serverska aplikacija)

```
SeminarskiServer/
├── src/
│   ├── main/
│   │   └── Server.java            # Entry point
│   │
│   ├── thread/
│   │   ├── ServerThread.java      # Glavna server socket petlja
│   │   └── ClientHandler.java     # Thread za obradu klijentskih zahteva
│   │
│   ├── controller/
│   │   └── Controller.java        # Poslovni sloj - obrada operacija
│   │
│   ├── repository/
│   │   ├── Repository.java        # Factory za DB operacije
│   │   └── db/
│   │       └── [DB specifične klase]
│   │
│   ├── so/                        # Service Operations (poslovni sloj)
│   │   ├── AbstractSO.java        # Template pattern
│   │   ├── author/
│   │   ├── book/
│   │   ├── member/
│   │   ├── loan/
│   │   └── receipt/
│   │
│   ├── util/
│   │   └── ServerConstants.java   # Konstante putanja i ključeva
│   │
│   ├── form/
│   │   └── ServerForm.java        # GUI za prikazivanje statusa
│   │
│   ├── config/
│   │   ├── dbconfig.properties    # Konfiguracija baze podataka
│   │   └── serverconfig.properties # Port servera
│   │
│   ├── build.xml
│   ├── mysql-connector-j-8.0.31.jar
│   ├── build/
│   └── dist/
```

### SeminarskiClient (Klijentska aplikacija)

```
SeminarskiClient/
├── src/
│   ├── main/
│   │   └── Client.java            # Entry point
│   │
│   ├── controller/
│   │   └── Controller.java        # Singleton za klijentsku logiku
│   │
│   ├── communication/
│   │   └── Communication.java     # Singleton za socket komunikaciju
│   │
│   ├── forms/                     # Swing GUI forme
│   │   ├── LoginForm.java/.form
│   │   ├── MainForm.java/.form
│   │   ├── AddBookForm.java/.form
│   │   ├── ViewBooksForm.java/.form
│   │   ├── LoadBookForm.java/.form
│   │   ├── AddMemberForm.java/.form
│   │   ├── UpdateMemberForm.java/.form
│   │   ├── ViewMembersForm.java/.form
│   │   ├── AddLoanForm.java/.form
│   │   ├── ViewLoansForm.java/.form
│   │   ├── LoadLoansForm.java/.form
│   │   ├── AddReceiptForm.java/.form
│   │   ├── AddItemReceiptForm.java/.form
│   │   ├── PickAuthorForm.java/.form
│   │   ├── PickMemberForm.java/.form
│   │   ├── PickBookForm.java/.form
│   │   └── AddAuthorForm.java/.form
│   │
│   ├── model/                     # Klijentski modeli (ako se koriste)
│   │
│   └── build.xml
├── dist/
└── nbproject/                     # NetBeans konfiguracija
```

---

## 🎨 Primenjene tehnike i obrasci

### Design Paterni

#### 1. **Singleton Pattern**
Koristi se za upravljanje единственом instancom ključnih komponenti:

```java
// Controller - Singleton na klijentskoj strani
public class Controller {
    private static Controller instance;
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
}

// Korišćenje
Controller.getInstance().login(librarian);
```

```java
// Communication - Singleton za socket komunikaciju
public class Communication {
    private static Communication instance;
    
    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }
}
```

#### 2. **Template Method Pattern (Service Operations)**

```java
// Bazna klasa
public abstract class AbstractSO {
    protected Repository repository;
    
    public AbstractSO() {
        repository = new Repository();
    }
    
    // Template method - definiše kostur operacije
    protected abstract void validate() throws Exception;
    protected abstract void execute() throws Exception;
    protected abstract Object getResult();
}

// Konkretna implementacija
public class AddBookSO extends AbstractSO {
    private Book book;
    
    public AddBookSO(Book book) {
        super();
        this.book = book;
    }
    
    @Override
    protected void validate() throws Exception {
        if (book.getTitle().isEmpty()) {
            throw new Exception("Naslov je obavezan");
        }
    }
    
    @Override
    protected void execute() throws Exception {
        repository.insert(book);
    }
    
    @Override
    protected Object getResult() {
        return "Knjiga uspešno dodana";
    }
}
```

#### 3. **Factory Pattern**

```java
// Repository factory
public class Repository {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Factory method za pravljenje konekcije
    private Connection getConnection() throws SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(connectionString, username, password);
    }
}
```

#### 4. **Observer Pattern (Swing Event Listeners)**

```java
// Button klik - Observer pattern
jButtonLogin.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        handleLogin();
    }
});

// Text field - Document listener
jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        handleSearch();
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        handleSearch();
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        handleSearch();
    }
});
```

#### 5. **MVC Pattern (Model-View-Controller)**

```
VIEW (Swing Forms)
    │
    │ UserAction (klik, unos)
    ▼
CONTROLLER (Business Logic)
    │
    │ Command/Query
    ▼
MODEL (Domain Objects)
    │
    │ Serialize
    ▼
NETWORK (Socket Communication)
```

### Arhitekturni obrasci

#### 1. **Client-Server Architecture**
- Centralizovana baza na serveru
- Distribuirana prezentacija (Swing GUI)
- Komunikacija preko TCP socket-a

#### 2. **Layered Architecture**
```
Presentation Layer (GUI Forms)
    ↓
Application Layer (Controllers)
    ↓
Business Logic Layer (Service Operations)
    ↓
Persistence Layer (Repository + JDBC)
    ↓
Database Layer (MySQL)
```

#### 3. **Request-Response Pattern**
Svaki zahtev klijenta se pakuje u `Request` objekat:
```java
Request {
    Operation operation;
    Object argument;
}
```

Server vraća `Response`:
```java
Response {
    Object result;
    boolean isSuccessful;
    Exception exception;
}
```

### Tehnike koje se koriste

#### 1. **Object Serialization**
Java objekti (Request, Response, Domain objects) se serijalizuju za prenos preko mreže:

```java
// Slanje
ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
oos.writeObject(request);
oos.flush();

// Primanje
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
Response response = (Response) ois.readObject();
```

#### 2. **Multi-threading na serveru**

```java
public class ServerThread extends Thread {
    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            // Novi thread za svakog klijenta
            ClientHandler handler = new ClientHandler(this, clientSocket);
            handler.start();  // Pokretanje u novoj niti
        }
    }
}

// Svaki ClientHandler je zasebna nit
public class ClientHandler extends Thread {
    @Override
    public void run() {
        while (isRunning) {
            Request request = receiver.receive();
            Response response = processRequest(request);
            sender.send(response);
        }
    }
}
```

#### 3. **JDBC Database Access**

```java
// Pripremljena SQL iskaza
String sql = "SELECT * FROM book WHERE title LIKE ?";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setString(1, "%" + searchTerm + "%");
ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
    Book book = new Book();
    book.setBookID(rs.getLong("bookID"));
    book.setTitle(rs.getString("title"));
    books.add(book);
}
```

#### 4. **Connection Management**

```java
public class Repository {
    public static Connection getConnection() {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/dbconfig.properties"));
        
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        
        return DriverManager.getConnection(url, username, password);
    }
}
```

#### 5. **Exception Handling i Validation**

```java
public void login(Librarian librarian) throws Exception {
    // Validacija
    if (librarian.getUsername().isEmpty() || librarian.getPassword().isEmpty()) {
        throw new Exception("Korisničko ime i lozinka su obavezni!");
    }
    
    // Slanje zahteva
    Communication.getInstance().sendRequest(
        new Request(Operation.LOGIN, librarian)
    );
    
    // Primanje odgovora
    Response response = Communication.getInstance().receiveResponse();
    
    if (response.isIsSuccessfull()) {
        connectedUser = (Librarian) response.getResult();
    } else {
        throw response.getException();  // Bacanje greške
    }
}
```

#### 6. **Enum korišćenje za operacije i žanrove**

```java
public enum Operation {
    LOGIN, LOGOUT, GET_ALL_BOOKS, ADD_BOOK, ...
}

public enum Genre {
    NOVEL, DRAMA, POETRY, CHILDREN, ...
}

// Korišćenje
Operation op = Operation.LOGIN;
Genre genre = Genre.NOVEL;
```

#### 7. **Properties fajlovi za konfiguraciju**

```properties
# dbconfig.properties
url=jdbc:mysql://localhost:3306/library
username=root
password=

# serverconfig.properties
port = 9000
```

#### 8. **Transaction Management**

```java
// Implicitne transakcije kroz auto-commit
connection.setAutoCommit(false);

try {
    // Više operacija
    insertBook(book);
    insertAuthorBook(book, author);
    
    // Ako sve prođe
    connection.commit();
} catch (Exception e) {
    // Ako bilo šta ne valja
    connection.rollback();
    throw e;
}
```

#### 9. **Foreach petlje i Java Collections**

```java
// Iteracija kroz listu rezultata
List<Book> books = (List<Book>) response.getResult();
for (Book book : books) {
    System.out.println(book.getTitle());
}

// ArrayList za dinamičke liste
List<Author> authors = new ArrayList<>();
authors.add(new Author("Milan", "Paić", new Date()));
```

#### 10. **Swing GUI Builder (NetBeans Designer)**

GUI forme su kreirane kroz NetBeans vizuelni editor:
- `.form` fajlovi - XML reprezentacija GUI-ja
- `.java` fajlovi - Generisani kod + custom event handleri

```java
public class LoginForm extends JFrame {
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordFieldPassword;
    private JButton jButtonLogin;
    
    // Inicijalizacija komponenti
    private void initComponents() {
        // Auto-generisano
    }
    
    // Custom event handler
    private void jButtonLoginActionPerformed(ActionEvent evt) {
        handleLogin();
    }
}
```

---

## 📊 Detaljne operacije

### Login operacija - Tok izvršavanja

```
1. Korisnik unosi username i password u LoginForm
2. jButtonLogin.actionPerformed() → handleLogin()
3. Krijanje/validacija podataka:
   - if (username.isEmpty() || password.isEmpty()) → greška
4. Kreiranje objekta:
   Librarian lib = new Librarian(username, password)
5. Slanje zahteva:
   Controller.getInstance().login(lib)
6. Komunikacija:
   Request req = new Request(Operation.LOGIN, lib)
   Communication.getInstance().sendRequest(req)
7. Socket komunikacija:
   - Klijent serijalizuje Request
   - Slanje preko socket stream-a
   - Server prima preko socket stream-a
8. Serverska strana:
   ClientHandler.run() → receiver.receive() → Request obj
9. Serverska obrada:
   Response resp = login(request)
   → Controller.getInstance().login((Librarian) request.getObject())
10. Serverska validacija:
    - SELECT * FROM librarian WHERE username=? AND password=?
    - Ako postoji → setIsSuccessful(true), setResult(librarian)
    - Ako ne postoji → setIsSuccessful(false), setException(...)
11. Serverski odgovor:
    sender.send(response)
12. Klijentska strana prima:
    Response response = Communication.getInstance().receiveResponse()
13. Klijentska obrada rezultata:
    if (response.isIsSuccessfull()) {
        connectedUser = (Librarian) response.getResult()
        Otvori MainForm
    } else {
        Prikaži error poruku
    }
```

### Dodavanje nove knjige - Tok izvršavanja

```
1. Korisnik klikne "Dodaj novu" u MainForm
2. Otvori AddBookForm
3. Korisnik popuni:
   - Naslov: "Mali princ"
   - Žanr: ROMAN
   - Broju primjeraka: 5
4. Odabir autora:
   - Kliknuti "Izaberi autora"
   - Otvori PickAuthorForm
   - Pretraži/Izaberi: "Antoine de Saint-Exupéry"
5. Slanje zahteva:
   Book book = new Book("Mali princ", Genre.ROMAN, 5, 5)
   book.setAuthor([Antoine de Saint-Exupéry])
   
   Request req = new Request(Operation.ADD_BOOK, book)
   Communication.getInstance().sendRequest(req)
6. Serverska obrada:
   ClientHandler → Controller.getInstance().addBook(book)
7. Serverska validacija:
   - Book.getTitle().isEmpty() → greška
   - Book.getAuthor().isEmpty() → greška
8. Serverska obrada u bazi:
   BEGIN TRANSACTION
   
   INSERT INTO book (title, genre, totalQuantity, stockQuantity)
   VALUES ('Mali princ', 'NOVEL', 5, 5)
   → Dobij bookID
   
   INSERT INTO authorbook (bookID, authorID) VALUES (?, ?)
   
   COMMIT
9. Serverski odgovor:
   Response.setIsSuccessful(true)
   Response.setResult("Knjiga uspešno dodana")
10. Klijent prikazuje success poruku
11. Osvežavanje liste knjiga
```

---

## 🔒 Bezbednost (Napomene)

### ⚠️ Trenutni problemi

- Lozinke u bazi se čuvaju u **čistom tekstu** (KRITIČNO!)
- Nema enkripcije komunikacije
- Kredencijali baze su u konfiguraciji u repozitorijumu
- Test podaci sadrže stvarne JMBG, adrese itd.

### Preporuke za poboljšanja

```java
// Heširanje lozinke
import java.security.MessageDigest;
import java.util.Base64;

public static String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}

// Korišćenje pri login-u
String hashedPassword = hashPassword(inputPassword);
// Poređenje sa heširovanom lozinkom iz baze
```

```java
// Korišćenje environment varijabli
String dbPassword = System.getenv("DB_PASSWORD");
```

---

## 📝 Zaključak

Ovo je **funkcionalan i dobro strukturiran sistem** za seminarsku nastavu sa:
- ✅ Client-server arhitekturom
- ✅ Multi-threading na serveru
- ✅ JDBC pristupom bazi
- ✅ Swing GUI
- ✅ Design patternima
- ✅ Kompleksnim poslovnom logikom

Za produkciju, preporuke su:
1. Heširanje lozinki (SHA-256, bcrypt, itd.)
2. HTTPS/SSL enkriptovanje komunikacije
3. Input validacija i SQL injection zaštita
4. Jedinični testovi
5. Dokumentacija API-ja
6. Error logging i monitoring

---

## 📧 Kontakt i podrška

Za pitanja ili probleme, kreirajte GitHub Issue ili kontaktirajte [email].

**Verzija:** 1.0  
**Zadnja ažuriranja:** 2025-08-27  
**Licenca:** MIT / Apache 2.0
