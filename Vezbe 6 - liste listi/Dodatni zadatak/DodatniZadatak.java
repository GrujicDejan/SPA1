class DodatniZadatak {
  public static void main(String[] arg) {
    RacunarskeUcionice ru = new RacunarskeUcionice();
    // a)
    ru.dodajUcionicu("plava");
    ru.dodajUcionicu("bela");
    ru.dodajUcionicu("siva");
    ru.dodajUcionicu("crvena");
    System.out.println(ru);
    
    // b)
    /*System.out.println(ru.dodajOpremuPoBrojuUcionice("Monitor", "BenQ", 0));
     System.out.println(ru.dodajOpremuPoBrojuUcionice("Monitor", "BenQ", 0));
     System.out.println(ru.dodajOpremuPoBrojuUcionice("Monitor", "Samsung", 1));
     System.out.println(ru.dodajOpremuPoBrojuUcionice("Stolica", "Drvena", 2));
     System.out.println(ru.dodajOpremuPoBrojuUcionice("Stolica", "Drvena", 0));
     System.out.println(ru.dodajOpremuPoBrojuUcionice("Monitor", "BenQ", 0));
     System.out.println(ru);*/
    
    // c)
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "Samsung", "plava", 0));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "BenQ", "plava", 0));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "BenQ", "plava", 0));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "Samsung", "plava", 1));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "Samsung", "plava", 0));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Stolica", "Drvena", "plava", 2));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Stolica", "Drvena", "plava", 0));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Monitor", "BenQ", "plava", 1));
    System.out.println(ru.dodajOpremuPoBrojuIBojiUcionice("Stolica", "Drvena", "bela", 1));
    System.out.println(ru);
    
    // d)
    ru.ispisiOpremu("plava");
    ru.ispisiOpremu("bela");
    
    // e)
    System.out.println("Opreme tipa Monitor ukupno ima " + ru.prebojOpremu("Monitor"));
    System.out.println("Opreme tipa Telefon ukupno ima " + ru.prebojOpremu("Telefon"));
    
    // f)
    ru.dodajUNajprazniju("Racunar", "Dell");
    System.out.println(ru);
    
    // g)
    System.out.println(ru.izbaci("Monitor", 0)); 
    System.out.println(ru.izbaci("Sto", 1)); 
    System.out.println(ru);
    
    // h)
    System.out.println(ru.izbaci("Monitor", "Benq", "plava")); 
    System.out.println(ru.izbaci("Sto", "Stakleni", "bela")); 
    System.out.println(ru.izbaci("Sto", "Stakleni", "crvena"));
    System.out.println(ru);
    
    // i)
    ru.izbaciSve("Stolica", 0);
    System.out.println(ru);
    
    // j)
    ru.prebaci("Monitor");
    System.out.println(ru);
    
  }
  
}

class RacunarskeUcionice {
  
  class Ucionica {
    String boja;
    Ucionica veza;
    Oprema oprema;
    
    public Ucionica(String boja) {
      this.boja = boja;
      this.veza = null;
      this.oprema = null;
    }
    
    public String toString() {
      String rez = "[ " + boja + ":";
      Oprema tek = oprema;
      while (tek != null) {
        rez += " " + tek;
        tek = tek.veza;
      }
      rez += " ]";
      return rez;
    }
  }
  
  class Oprema {
    String tip, opis;
    Oprema veza;
    
    public Oprema(String tip, String opis) {
      this.tip = tip;
      this.opis = opis;
      this.veza = null;
    }
    
    public String toString() {
      return "tip:" + tip + ", opis:" + opis;
    }
    
  }
  
  int br = 0;
  
  Ucionica prva, poslednja;
  
  public RacunarskeUcionice() {
    this.prva = null;
    this.poslednja = null;
    this.br = 0;
  }
  
  // a) dodavanje nove u?ionice u listu u?ionica, prosledjuje se boja kao parametar.
  public void dodajUcionicu(String boja) {
    Ucionica nova = new Ucionica(boja);
    if (poslednja == null) {
      nova.veza = prva;
      prva = nova;
      poslednja = nova;
    } 
    else {
      poslednja.veza = nova;
      poslednja = nova;
    }
    br++;
  }
  
  // b) dodavanje komada opreme (sa zadatim tipom i opisom) u u?ionicu sa prosle?enim brojem. Ako taj broj ne postoji, odustati od dodavanja. Vratiti podatak o uspešnosti dodavanja (boolean).
  public boolean dodajOpremuPoBrojuUcionice(String tip, String opis, int br) {
    Ucionica tek = nadjiUcionicuPoBroju(br);
    if (tek != null) {
      Oprema novi = new Oprema(tip, opis);
      if (tek.oprema == null || tek.oprema.tip.equals(tip)) {
        novi.veza = tek.oprema;
        tek.oprema = novi;
        return true;
      }
      else {
        Oprema pom = tek.oprema;
        while (pom.veza != null && !pom.veza.tip.equals(tip)) {
          pom = pom.veza;
        }
        novi.veza = pom.veza;
        pom.veza = novi;
        return true;
      }
    }
    return false;
  }
  
  private Ucionica nadjiUcionicuPoBroju(int broj) {
    if (broj < 0 || broj > br)
      return null;
    int brojac = 0;
    Ucionica tek = prva;
    while (brojac < broj) {
      tek = tek.veza;
      brojac++;
    }    
    return tek;
  }
  
  // c) na osnovu parametara tip, opis, boja i n dodati novi komad opreme u n-tu u?ionicu sa zadatom bojom. Ako ne postoji takva u?ionica odustati.
  public boolean dodajOpremuPoBrojuIBojiUcionice(String tip, String opis, String boja, int br) {
    Ucionica tek = nadjiUcionicuPoBrojuIBoji(br, boja);
    if (tek != null) {
      Oprema novi = new Oprema(tip, opis);
      if (tek.oprema == null || tek.oprema.tip.equals(tip)) {
        novi.veza = tek.oprema;
        tek.oprema = novi;
        return true;
      }
      else {
        Oprema pom = tek.oprema;
        while (pom.veza != null && !pom.veza.tip.equals(tip)) {
          pom = pom.veza;
        }
        novi.veza = pom.veza;
        pom.veza = novi;
        return true;
      }
    }
    return false;
  }
  
  private Ucionica nadjiUcionicuPoBrojuIBoji(int broj, String boja) {
    if (broj < 0 || broj > br)
      return null;
    int brojac = 0;
    Ucionica tek = prva;
    while (brojac < broj) {
      tek = tek.veza;
      brojac++;
    }
    if (tek.boja.equals(boja))
      return tek;
    return null;
  }
  
  // d) ispisivanje svih komada opreme u u?ionicama zadate boje
  public void ispisiOpremu(String boja) {
    Ucionica tek = prva;
    while (tek != null && !tek.boja.equals(boja))
      tek = tek.veza;
    Oprema tekO = tek.oprema;
    while (tekO != null) {
      Svetovid.out.println(tekO);
      tekO = tekO.veza;
    }
  }
  
  // e) prebrojati koliko opreme nekog tipa ima u svim u?ionicama zajedno
  public int prebojOpremu(String tip) {
    Ucionica tek = prva;
    int brojac = 0;
    while (tek != null) {
      Oprema tekO = tek.oprema;
      while (tekO != null) {
        if (tekO.tip.equals(tip))
          brojac++;
        tekO = tekO.veza;
      }
      tek = tek.veza;
    }
    return brojac;
  }
  
  // f) dodati komad opreme sa datim tipom i opisom u najprazniju u?ionicu
  public void dodajUNajprazniju(String tip, String opis){
    Ucionica najpraznija = nadjiNajprazniju();
    if (najpraznija != null) {
      if (najpraznija != null) {
        Oprema novi = new Oprema(tip, opis);
        if (najpraznija.oprema == null || najpraznija.oprema.tip.equals(tip)) {
          novi.veza = najpraznija.oprema;
          najpraznija.oprema = novi;
        }
        else {
          Oprema pom = najpraznija.oprema;
          while (pom.veza != null && !pom.veza.tip.equals(tip)) {
            pom = pom.veza;
          }
          novi.veza = pom.veza;
          pom.veza = novi;
        }
      }
    }
  }
  
  private Ucionica nadjiNajprazniju() {
    Ucionica tek = prva;
    Ucionica najpraznija = null;
    int min = Integer.MAX_VALUE;
    int brO = 0;
    
    while (tek != null) {
      brO = 0;
      Oprema o = tek.oprema;
      while (o != null) {
        brO++;
        o = o.veza;
      }
      if (brO < min) {
        najpraznija = tek;
        min = brO;
      }
      tek = tek.veza;
    }
    return najpraznija;
  }
  
  // g) izbaciti jedan komad opreme datog tipa iz u?ionice zadate brojem.
  public boolean izbaci(String tip, int broj) {
    Ucionica pom = nadjiUcionicuPoBroju(broj);
    Oprema tek = pom.oprema;
    if (tek != null) {
      if (tek.tip.equals(tip)) {
        pom.oprema = pom.oprema.veza;
        return true;
      }
      else {
        while (tek.veza != null && !tek.veza.tip.equals(tip)) {
          tek = tek.veza;
        }
        if (tek.veza != null) {
          tek = tek.veza.veza;
          return true;
        }
      }
    }
    return false;
  }
  
  // h) izbaciti jedan komad opreme datog tipa i opisa iz bilo koje u?ionice zadate bojom.  
  public boolean izbaci(String tip, String opis, String boja) {
    Ucionica pom = nadjiUcionicuPoBoji(boja);
    Oprema tek = pom.oprema;
    if (tek != null) {
      if (tek.tip.equals(tip) && tek.opis.equals(opis)) {
        pom.oprema = pom.oprema.veza;
        return true;
      }
      else {
        while (tek.veza != null) {
          if (tek.veza.tip.equals(tip) && tek.veza.opis.equals(opis)) {
            tek.veza = tek.veza.veza;
            return true;
          }
          tek = tek.veza;
        }
      }
    }
    return false;
  }
  
  private Ucionica nadjiUcionicuPoBoji(String boja) {
    Ucionica tek = prva;
    while (tek != null && !tek.boja.equals(boja)) {
      tek = tek.veza;
    }
    return tek;
  }
  
  // i) izbaciti svu opremu zadatog tipa iz u?ionice sa datim brojem
  public void izbaciSve(String tip, int broj) {
    Ucionica pom = nadjiUcionicuPoBroju(broj);
    if (pom != null) {
      while (pom.oprema != null && pom.oprema.tip.equals(tip)) {
        pom.oprema = pom.oprema.veza;
      }
      if (pom.oprema != null) {
        Oprema tek = pom.oprema;
        while (tek.veza != null) {
          if (tek.veza.tip.equals(tip)) {
            tek.veza = tek.veza.veza;
          }
          else
            tek = tek.veza;
        }
      }
    }
  }
  
  // j) prebaciti svu opremu zatatog tipa iz u?ionice sa najviše opreme tog tipa u u?ionicu sa najmanje opreme tog tipa.
  public void prebaci(String tip) {
    Ucionica tek = prva;
    Ucionica zapamtiNajv = null;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    String boja = "";
    int brUcionice = 0;
    int zapamptiBr = 0;
    
    while (tek != null) {
      int brO = 0;
      Oprema o = tek.oprema;
      while (o != null) {
        if (o.tip.equals(tip))
          brO++;
        o = o.veza;
      }
      if (brO > max) {
        zapamtiNajv = tek;
        max = brO;
      }
      else if (brO < min) {
        min = brO;
        boja = tek.boja;
        zapamptiBr = brUcionice;
      }
      tek = tek.veza;
      brUcionice++;
    }
    
    
    while (zapamtiNajv.oprema != null && zapamtiNajv.oprema.tip.equals(tip)) {
      dodajOpremuPoBrojuIBojiUcionice(zapamtiNajv.oprema.tip, zapamtiNajv.oprema.opis, boja, zapamptiBr);
      zapamtiNajv.oprema = zapamtiNajv.oprema.veza;
    }
    if (zapamtiNajv.oprema != null) {
      Oprema tekuci = zapamtiNajv.oprema;
      while (tekuci.veza != null) {
        if (tekuci.veza.tip.equals(tip)) {
          dodajOpremuPoBrojuIBojiUcionice(tekuci.veza.tip, tekuci.veza.opis, boja, zapamptiBr);
          tekuci.veza = tekuci.veza.veza;
        }
        if (tekuci.veza != null)
          tekuci = tekuci.veza;
      }
    }
  }
  
  
  public String toString() {
    String rez = "Rac.Ucionice:";
    Ucionica tek = prva;
    while (tek != null) {
      rez += " " + tek;
      tek = tek.veza;
    }
    return rez;
  }
}
























