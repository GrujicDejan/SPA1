public class Zadatak2 {
  
  static SpisakIgracaka spisak = null;
  
  public static void main(String[] args) {
    String imeFajla = Svetovid.in.readLine("Unesite ime fajla: ");
    spisak = new SpisakIgracaka();
    spisak.ucitajSpisak(imeFajla);
    
    Svetovid.out.println("Igracke iz fajla:");
    spisak.ispisiIgrackeNaEkran(imeFajla);
    Svetovid.out.println();
    
    Svetovid.out.println("Unos nove igracke:");
    String tip = Svetovid.in.readLine("Unesite tip igracke:");
    String boja = Svetovid.in.readLine("Unesite boju igracke:");
    double cena = Svetovid.in.readDouble("Unesite cenu igracke:");
    
    spisak.dodajIgracku(tip, boja, cena);
    
    Svetovid.out.println();
    
    boja = Svetovid.in.readLine("Unesite boju na osnovu koje zelite izlistavanje:");
    Svetovid.out.println("Spisak igracaka cija je boja " + boja);
    spisak.ispisiIgrackeSaBojom(boja);
    Svetovid.out.println();
    
    cena = Svetovid.in.readDouble("Unesite cenu na osnovu koje zelite izlistavanje:");
    Svetovid.out.println("Broj igracki cija je cena skuplja od "+ cena +" je " + spisak.prebrojIgrackePoCeni(cena));
    Svetovid.out.println();
    
    String imeFajla2 = Svetovid.in.readLine("Unesite ime fajla za ispis:");
    spisak.ispisiIgrackeUFajl(imeFajla2);
  }
  
}

class Igracka {
  public String tip, boja;
  public double cena;
  
  public Igracka(String t, String b, double c) {
    tip = t;
    boja = b;
    cena = c;
  }
  
  public String toString() {
    return tip + " " + boja + " " + cena + " ";
  }
}

class SpisakIgracaka {
  static final int MAX_BR_IGRACAKA = 100;
  
  public Igracka[] spisakIgracaka;
  public int brIgr;
  
  // nov prazan spisak 
  public SpisakIgracaka() {
    spisakIgracaka = new Igracka[MAX_BR_IGRACAKA];
    brIgr = 0;
  }
  
  // ucitavanje igracaka u ovaj spisak
  public void ucitajSpisak(String f) {
    String t, b;
    double c;
    
    while (!Svetovid.in(f).isEmpty() && brIgr < MAX_BR_IGRACAKA) {
      t = Svetovid.in(f).readToken();
      b = Svetovid.in(f).readToken();
      c = Svetovid.in(f).readDouble();
      dodajIgracku(t,b,c);
    }
    
    if (!Svetovid.in(f).isEmpty())
      System.out.println("Previse studenata u fajlu! Ucitano prvih " + MAX_BR_IGRACAKA);
    
    Svetovid.in(f).close();
  }
  
  public void dodajIgracku(String t, String b, double c) {
    if (brIgr < MAX_BR_IGRACAKA) {
      spisakIgracaka[brIgr] = new Igracka(t,b,c);
      brIgr++;
    }
    else
      System.out.println("Previse igracaka u nizu!");
  }
  
  public void ispisiIgrackeUFajl(String f) {
    for (int i = 0; i < brIgr; i++) {
      Svetovid.out(f).print(spisakIgracaka[i].tip);
      Svetovid.out(f).print(" ");
      Svetovid.out(f).print(spisakIgracaka[i].boja);
      Svetovid.out(f).print(" ");
      Svetovid.out(f).println(spisakIgracaka[i].cena);
    }
    Svetovid.out(f).close();
  }
  
  public void ispisiIgrackeNaEkran(String f) {
    for (int i = 0; i < brIgr; i++) {
      System.out.print(spisakIgracaka[i].tip);
      System.out.print(" ");
      System.out.print(spisakIgracaka[i].boja);
      System.out.print(" ");
      System.out.println(spisakIgracaka[i].cena);
    }
  }
  
  public void ispisiIgrackeSaBojom(String boja) {
    for (int i = 0; i < brIgr; i++) {
      if (boja.equals(spisakIgracaka[i].boja))
        Svetovid.out.println(spisakIgracaka[i]);
    }
  }
  
  public int prebrojIgrackePoCeni(double cena) {
    int broj = 0;
    for (int i = 0; i < brIgr; i++) {
      if (spisakIgracaka[i].cena > cena)
        broj++;
    }
    return broj;
  }
  
}
  
  
  


