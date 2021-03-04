class DodatniZadatak {
  public static void main(String[] args) {
    ListaStringova lista = new ListaStringova();
    lista.dodajElemente();
    lista.stampajNaEkran();
    Svetovid.out.println("Broj Stringova sa velikim pocetnim slovom je: " + lista.velikoPocetnoSlovo());
    Svetovid.out.println("Prosecna duzina stringova u listi je " + lista.prosecnaVrednost());
    lista.izbrisiVeceOdPreth();
    lista.stampajNaEkran();
    
  }
}

class ListaStringova {
  
  class Element {
    String info;
    Element veza;
    
    public Element(String s) {
      this.info = s;
      this.veza = null;
    }
    
    public String toString() {
      return info + "";
    }
  }
  
  Element prvi;
  
  public ListaStringova() {
    prvi = null;
  }
  
  public void dodajNaPocetak(String s) {
    Element novi = new Element(s);
    novi.veza = prvi;
    prvi = novi;
  }
  
  public void dodajElemente() {
    int n = Svetovid.in.readInt("Unesite broj elemenata za unos: ");
    for (int i = 0; i < n; i++) {
      String s = Svetovid.in.readLine("Unesite " + i + ". element: ");
      dodajNaPocetak(s);
    }
  }
  
  public boolean jePrazna() {
    return prvi == null;
  }
  
  public void stampajNaEkran() {
    if (jePrazna()) 
      Svetovid.out.println("Lista je prazna.");
    else {
      Svetovid.out.println("Sadrzaj liste: ");
      Element tekuci = prvi;
      while (tekuci != null) {
        Svetovid.out.println(tekuci.info);
        tekuci = tekuci.veza;
      }
      Svetovid.out.println();
    }
  }
  
  public int velikoPocetnoSlovo() { //a
    if (jePrazna()) {
      return 0;
    }
    else {
      int brojac = 0;
      Element tekuci = prvi;
      while (tekuci != null) {
        if (tekuci.info.length() > 0 && Character.isUpperCase(tekuci.info.charAt(0)))
          brojac++;
        tekuci = tekuci.veza;
      }
      return brojac;
    }
  }
  
  public double prosecnaVrednost() { //b
    if (jePrazna()) {
      return 0.0;
    }
    else {
      int brojac = 0;
      double suma = 0;
      Element tekuci = prvi;
      while (tekuci != null) {
        brojac++;
        suma += tekuci.info.length();
        tekuci = tekuci.veza;
      }
      return suma/brojac;
    }
  }
  
  public void izbrisiVeceOdPreth() {
    if (!jePrazna()) {
      Element tek;
      Element sled = prvi;
      while (sled.veza != null) {
        tek = sled;
        sled = sled.veza;
        if (sled.info.length() > tek.info.length()) {
          tek.veza = sled.veza;
          sled = tek;
        }
      }
    }
  }
  
  public String toString() {
    String rez = "Lista [ ";
    Element tekuci = prvi;
    while (tekuci != null) {
      rez += "\"" + tekuci.info + "\" ";
      tekuci = tekuci.veza;
    }
    rez += "]";
    return rez;
  }
  
}