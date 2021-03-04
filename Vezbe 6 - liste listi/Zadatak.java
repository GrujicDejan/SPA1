class Zadatak {
  
  public static void main(String[] arg) {
    Ulica ul = new Ulica();
    ul.dodajKucu(5);
    ul.dodajKucu(3);
    ul.dodajKucu(8);
    ul.dodajKucu(4);
    ul.dodajKucu(1);
    ul.dodajKucu(22);
    ul.dodajKucu(15);
    ul.dodajKucu(30);
    ul.dodajKucu(30);
    System.out.println(ul);
    
    ul.dodajOsobu("Nikola", 5);
    ul.dodajOsobu("Ana", 80);
    ul.dodajOsobu("Mirko", 22);
    ul.dodajOsobu("Anastasija", 5);
    ul.dodajOsobu("Vanja", 15);
    ul.dodajOsobu("Goran", 8);
    ul.dodajOsobu("Aleksa", 4);
    ul.dodajOsobu("Katarina", 22);
    ul.dodajOsobu("Tijana", 3);
    ul.dodajOsobu("Igor", 4);
    ul.dodajOsobu("Ivan", 1);
    ul.dodajOsobu("Marko", 1);
    ul.dodajOsobu("Vujadin", 5);
    ul.dodajOsobu("Vladimir", 3);
    ul.dodajOsobu("Djura", 4);
    ul.dodajOsobu("Marija", 8);
    System.out.println(ul);
    
    
    
    ul.najviseStanovnika(45); //primer 1
    ul.najviseStanovnika(6); //primer 2
    
    ul.prebaciPoslednjuOsobu(70,3); //primer 1
    ul.prebaciPoslednjuOsobu(30,70); //primer 2
    ul.prebaciPoslednjuOsobu(15,1); //primer 3
    ul.prebaciPoslednjuOsobu(5,3); //primer 4
    System.out.println(ul);
  }
  
}

class Ulica {
  
  class Kuca {
    int redniBr;
    Kuca veza;
    Osoba osobe;
    
    public Kuca(int rb) {
      this.redniBr = rb;
      this.veza = null;
      this.osobe = null;
    }
    
    public String toString() {
      String rez = "[Kuca: " + redniBr + ": ";
      Osoba tek = osobe;
      if (tek != null) {
        rez += tek;
        tek = tek.veza;
        while (tek != null) {
          rez += ", " + tek;
          tek = tek.veza;
        }
      }
      rez += "]";
      return rez;
    }
  }
  
  class Osoba {
    String licnoIme;
    Osoba veza;
    
    public Osoba(String ime) {
      this.licnoIme = ime;
      this.veza = null;
    }
    
    public String toString() {
      return licnoIme;
    }
  }
  
  private Kuca prvaKuca;
  
  public Ulica() {
    this.prvaKuca = null;
  }
  
  //Dodaje novu kucu sa datim rednim brojem, tako da se postuje redosled od najmanjeg ka najvecem.
  //Ako vec postoji kuca sa datim rednim brojem, ne radi nista.
  public void dodajKucu(int redniBr) {
    if (!postojiKuca(redniBr)) { // Ako ne postoji kuca sa prosledjenim rednim brojem izvrsava se sledeci kod
      Kuca tek = prvaKuca;
      if (tek == null || tek.redniBr > redniBr) { // Ako ne postoji ni jedna kuca ili ako je prosledjeni redni broj manji od rednog broja prve kuce
        Kuca nova = new Kuca(redniBr);
        nova.veza = prvaKuca;
        prvaKuca = nova;
      }
      else { // Ako clasa Ulica nije prazna
        while (tek.veza != null && tek.veza.redniBr < redniBr) //sve dok pokazivac ne pokazuje na poslednji element ili 
          tek = tek.veza;                                      // dok  redni br tekuceg elementa nije veci od prosledjenog
        if (tek.veza == null) { // ako je stiglo do kraja
          Kuca nova = new Kuca(redniBr);
          tek.veza = nova;
        }
        else { // ako je izasao iz while petlje zbog poredjenja rednig brojeva
          Kuca nova = new Kuca(redniBr);
          nova.veza = tek.veza;
          tek.veza = nova;
        }
      }
    }
    else // ako prvi if nije ispinjun
      System.out.println("Kuca sa rednim brojem " + redniBr + " vec postoji.");
  }
  
  private boolean postojiKuca(int redniBr) {
    return nadjiKucu(redniBr) != null; 
  }
  
  private Kuca nadjiKucu(int redniBr) {
    Kuca tek = prvaKuca;
    while (tek != null) {
      if (tek.redniBr == redniBr)
        return tek;
      tek = tek.veza;
    }
    return tek;
  }
  
  public void dodajOsobu(String ime, int redniBr) {
    Kuca tek = nadjiKucu(redniBr);
    if (tek != null) {
      Osoba nova = new Osoba(ime);
      nova.veza = tek.osobe;
      tek.osobe = nova;
    }
    else 
      System.out.println("Ne postoji kuca sa rednim brojem " + redniBr + ".");
  }
  
  //Metod koji za zadatu duzinu vraca redni broj kuce sa najvecim brojem stanovnima cije je ime strogo duze od N.
  //Ako ne postoji kuca vraca -1
  private int najviseStan(int duzina) {
    Kuca tek = prvaKuca;
    int brojKuce = -1; // broj kuce sa najv stanovnika
    int najveciBrStanara = 0; 
    while (tek != null) { // prolazimo kroz celu listu, tj. sve kuce
      Osoba pom = tek.osobe; // pokazivac pom pokazuje na sve osobe unutar tekuce kuce
      int brojStanara = 0; // postavlja brojac na nulu
      while (pom != null) { // dokle god postoje osobe u kuci
        if (pom.licnoIme.length() > duzina) //pitaj da li su njihova imena duza od prosledjene duzine
          brojStanara++; // ako jeste, povecaj brojac za 1
        pom = pom.veza;
      }
      if (brojStanara > najveciBrStanara) { //ako je vr brojaca veca od maksimalne vrednosti
        brojKuce = tek.redniBr; // zapamti broj kuce
        najveciBrStanara = brojStanara; // postavi novi maksimum
      }
      tek = tek.veza;
    }
    return brojKuce; // Ukoliko ne postoji ni jedna kuca vraca vrednost -1.
  }
  
  // metod koji je ubacen kako proveru ne bi vrsili u main-u
  public void najviseStanovnika(int duzina) { 
    int max = najviseStan(duzina); // promenljivoj max dodeljuje vrednost koju prosledjuje metod "najviseStan(int duzina)"
    if (max == -1) // ako je vrednost -1, to znaci da ni jedna kuca nema stanovnike cija su imena duza od "duzina" 
      System.out.println("Ne postoji kuca u kojoj zive osobe cija su imena duza od " + duzina + " slova");
    else
      System.out.println("Kuca sa najvise stanovnika cija su imena duza od " + duzina + " slova ima redni broj " + max);
  }
  
  //Prebacuje poslednju osobu iz kuce A u kucu B.
  //Ako kuca A ne postoji ili nema osoba u njoj, ne radi nista
  //Ako kuca B ne postji a kuca A postoji, izbacuje samo poslednju osobu iz kuce A
  public void prebaciPoslednjuOsobu(int kucaA, int kucaB) {
    Kuca tekA = nadjiKucu(kucaA); // tekA pokazuje na kucaA, ukoliko kucaA ne postoji, tekA je null
    Kuca tekB = nadjiKucu(kucaB); // isto samo za tekB i kucaB
    
    if (tekA == null) // ako kucaA ne postoji
      System.out.println("Ne postoji kuca sa rednim brojem " + kucaA + ".");
    else if (tekA.osobe == null) // Ako u kucaA nema stanovnika
      System.out.println("Kuca sa rednim brojem " + kucaA + " nema osobe.");
    else {
      Osoba tekO = tekA.osobe;
      if (tekO.veza == null) { // ako u kucaA ima samo jedan stanovnik
        if (tekB != null) { // Ako postoji kucaB premesti u kucaB
          tekO.veza = tekB.osobe;
          tekB.osobe = tekO;
        }
        tekA.osobe = null; // za kucaA postavi da nema osobe u kuci
      }
      else { // ako kuca ima vise od jedne osobe
        while (tekO.veza.veza != null) // nadji pretposlednji element
          tekO = tekO.veza;
        if (tekB != null) { // ako postoji kucaB, premesti u kucaB 
          tekO.veza.veza = tekB.osobe;
          tekB.osobe = tekO.veza;
        }
        tekO.veza = null; // izbaci poslednji element iz kucaA
      }
    }
  }
  
  
  public String toString() {
    String rez = "Ulica:";
    Kuca tek = prvaKuca;
    while (tek != null) {
      rez += " " + tek;
      tek = tek.veza;
    }
    return rez;
  }
  
  
}

















