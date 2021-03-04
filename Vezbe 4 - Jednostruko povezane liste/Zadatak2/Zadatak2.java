class Zadatak2 {
  public static void main(String[] args) {
    ListaZnakova lista = new ListaZnakova();
    lista.dodajElemente();
    lista.stampajNaEkran();
    lista.stampajVelikaSlova();
    Svetovid.out.println(lista);
    lista.izbaciMalaSlova();
    Svetovid.out.println(lista);
    ListaZnakova lista2 = lista.izdvojiSveCifre();
    Svetovid.out.println(lista);
    Svetovid.out.println(lista2);
  }
}

class ListaZnakova {
  
  class Element {
    char info;
    Element veza;
    
    public Element(char c) {
      this.info = c;
      this.veza = null;
    }
    
    public String toString() {
      return info + "";
    }
  }
  
  Element prvi;
  
  public ListaZnakova() {
    this.prvi = null;
  }
  
  public boolean jePrazna() {
    return prvi == null;
  }
  
  public void dodajNaPocetak(char c) {
    Element novi = new Element(c);
    novi.veza = prvi;
    prvi = novi;
  }
  
  public void dodajElemente() {
    int n;
    do {
      n = Svetovid.in.readInt("Unesite broj elemenata za unos: ");
    } while (n < 0);
    for (int i = 0; i < n; i++) {
      char c = Svetovid.in.readChar("Unesite " + i + ". znak: ");
      dodajNaPocetak(c);
    }
  }
  
  public void stampajNaEkran() {
    if (jePrazna())
      Svetovid.out.println("Lista je prazna.");
    else {
      Svetovid.out.println("Lista: ");
      Element tekuci = prvi; 
      while (tekuci != null) {
        Svetovid.out.println(tekuci.info);
        tekuci = tekuci.veza;
      }
      Svetovid.out.println();
    }
  }
  
  public void stampajVelikaSlova() {
    if (jePrazna())
      Svetovid.out.println("Lista je prazna.");
    else {
      Svetovid.out.println("Velika slova: ");
      Element tekuci = prvi; 
      while (tekuci != null) {
        if (Character.isUpperCase(tekuci.info))
          Svetovid.out.println(tekuci.info);
        tekuci = tekuci.veza;
      }
      Svetovid.out.println();
    }
  }
  
  public void izbaciMalaSlova() {
    int brojac = 0;
    while (prvi != null && Character.isLowerCase(prvi.info)){
      prvi = prvi.veza;
      brojac++;
    }
    if (prvi != null) {
      Element tekuci = prvi; 
      while (tekuci.veza != null) {
        Element prethodni = tekuci;
        tekuci = tekuci.veza;
        if (Character.isLowerCase(tekuci.info)) {
          prethodni.veza = tekuci.veza;
          brojac++;
          tekuci = prethodni;
        }
      }
    }
    if (brojac == 0)
      Svetovid.out.println("U listi nema malih slova.");
    else
      Svetovid.out.println("Broj izbacenih malih slova iz liste je: " + brojac);
  }
  
  public ListaZnakova izdvojiSveCifre() {
    ListaZnakova cifre = new ListaZnakova();
    
    Element cifreKraj = null;
    Element tek, preth;
    
    while(prvi != null && Character.isDigit(prvi.info)) {
      tek = prvi;
      prvi = prvi.veza;
      if (cifre.prvi == null) {
        cifre.prvi = tek;
        tek.veza = null;
        cifreKraj = tek;
      } else {
        cifreKraj.veza = tek;
        tek.veza = null;
        cifreKraj = tek;
      }
    }
    
    if (prvi != null) {
      tek = prvi;
      while (tek.veza != null) {
        preth = tek;
        tek = tek.veza;
        if (Character.isDigit(tek.info)) {
          preth.veza = tek.veza;
          if (cifre.prvi == null) {
            cifre.prvi = tek;
            tek.veza = null;
            cifreKraj = tek;
          } else {
            cifreKraj.veza = tek;
            tek.veza = null;
            cifreKraj = tek;
          }
          tek = preth;
        }
      }
    }
    return cifre;
  }
  
  public String toString() {
    String rez = "Lista: [";
    Element tekuci = prvi;
    while (tekuci != null) {
      rez += tekuci.info + " ";
      tekuci = tekuci.veza;
    }
    rez += "]";
    return rez;
  }
  
}






