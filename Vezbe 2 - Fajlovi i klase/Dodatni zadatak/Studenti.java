public class Studenti {
  
  // promenljiva koja ce se videti u glavnom programu mora biti static
  static SpisakStudenata spisak = null;
  
  public static void main(String[] args) {
    String imeF = Svetovid.in.readLine("Unesite ime fajla:");
    spisak = new SpisakStudenata();
    spisak.ucitajStudente(imeF);
    
    Svetovid.out.println("Studenti iz fajla:");
    spisak.ispisiStudenteNaEkran();
    Svetovid.out.println();
    
    Svetovid.out.println("Unos novog studenta:");
    String ime = Svetovid.in.readLine("Unesite ime studenta:");
    String prez = Svetovid.in.readLine("Unesite prezime studenta:");
    int god = Svetovid.in.readInt("Unesite godinu rodjenja studenta:");
    
    spisak.dodajStudenta(ime, prez, god);
    
    Svetovid.out.println();
    
    god = Svetovid.in
      .readInt("Unesite godinu na osnovu koje zelite izlistavanje:");
    Svetovid.out.println("Spisak studenata rodjenih " + god + ". godine: ");
    spisak.ispisiStudenteSaGodinom(god);
    Svetovid.out.println();
    
    Svetovid.out.println("Rodjenih pre " + god + ". godine ima: "
                           + spisak.prebrojStudentePreGodine(god));
    Svetovid.out.println();
    
    String imeF2 = Svetovid.in.readLine("Unesite ime fajla za ispis:");
    spisak.ispisiStudenteUFajl(imeF2);
    
  }
  
}

class Student {
  public String ime, prezime;
  public int godina;
  
  public Student(String i, String p, int g) {
    ime = i;
    prezime = p;
    godina = g;
  }
  
  public String toString() {
    return ime + " " + prezime + " " + godina;
  }
}

class SpisakStudenata {
  // konstante na nivou klase oznacavamo sa final static
  final static int MAX_BR_STUDENATA = 100;
  
  public Student[] spisakStudenata;
  public int brojSt;
  
  /** Kreira nov prazan spisak studenata */
  public SpisakStudenata() {
    spisakStudenata = new Student[MAX_BR_STUDENATA];
    brojSt = 0;
  }
  
  /** Ucitava studente iz datog fajla u ovaj spisak studenata */
  public void ucitajStudente(String f) {
    String i, p;
    int god;
    
    while (!Svetovid.in(f).isEmpty() && brojSt < MAX_BR_STUDENATA) {
      p = Svetovid.in(f).readToken();
      i = Svetovid.in(f).readToken();
      god = Svetovid.in(f).readInt();
      dodajStudenta(i, p, god);
    }
    
    if (!Svetovid.in(f).isEmpty()) {
      Svetovid.out.println("Previse studenata u fajlu! Ucitano prvih "
                             + MAX_BR_STUDENATA);
    }
    
    Svetovid.in(f).close();
  }
  
  /** Dodaje studenta sa datim podacima u ovaj spisak studenata */
  public void dodajStudenta(String i, String p, int g) {
    // proverimo da li ima mesta
    if (brojSt < MAX_BR_STUDENATA) {
      spisakStudenata[brojSt] = new Student(i, p, g);
      brojSt++;
    } else {
      Svetovid.out.println("Previse studenata u nizu!");
    }
  }
  
  public void ispisiStudenteUFajl(String f) {
    for (int i = 0; i < brojSt; i++) {
      Svetovid.out(f).print(spisakStudenata[i].prezime);
      Svetovid.out(f).print(" ");
      Svetovid.out(f).print(spisakStudenata[i].ime);
      Svetovid.out(f).print(" ");
      Svetovid.out(f).println(spisakStudenata[i].godina);
    }
    Svetovid.out(f).close();
  }
  
  public void ispisiStudenteNaEkran() {
    for (int i = 0; i < brojSt; i++) {
      Svetovid.out.print(spisakStudenata[i].prezime);
      Svetovid.out.print(" ");
      Svetovid.out.print(spisakStudenata[i].ime);
      Svetovid.out.print(" ");
      Svetovid.out.println(spisakStudenata[i].godina);
    }
  }
  
  public void ispisiStudenteSaGodinom(int god) {
    for (int i = 0; i < brojSt; i++) {
      if (spisakStudenata[i].godina == god) {
        Svetovid.out.println(spisakStudenata[i]);
      }
    }
  }
  
  public int prebrojStudentePreGodine(int god) {
    int br = 0;
    for (int i = 0; i < brojSt; i++) {
      if (spisakStudenata[i].godina < god) {
        br++;
      }
    }
    return br;
  }
  
  // vraca String koji predstavlja ovaj niz studenata
  public String toString() {
    String st = "Studenti [";
    if (brojSt > 0) {
      st += spisakStudenata[0];
    }
    for (int i = 1; i < brojSt; i++) {
      st += ", " + spisakStudenata[i];
    }
    st += "]";
    return st;
  }
}