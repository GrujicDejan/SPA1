class Zadatak2 {
  
  public static void main(String[] args) {
    ListaImena spisak = new ListaImena();
    String f = Svetovid.in.readLine("Unesite ime fajla za citanje: ");
    spisak.ucitajFajl(f);
    spisak.stampaj();
    String ime = Svetovid.in.readLine("Unesite ime koje trazite: ");
    spisak.proveriIme(ime);
  }
  
}

class ListaImena {
  
  class Element {
    public String info;
    public Element veza;
    
    public Element(String ime) {
      this.info = ime;
      this.veza = null;
    }
    
    public String toString() {
      return info + "";
    }
  }
  
  Element prvi;
  
  public ListaImena() {
    this.prvi = null;
  }
  
  public void ucitajFajl(String f) {
    while (!Svetovid.in(f).isEmpty()) {
      String ime = Svetovid.in(f).readLine();
      dodajElement(ime);
    }
    Svetovid.in(f).close();
  }
  
  public void dodajElement(String s) {
    Element novi = new Element(s);
    novi.veza = prvi;
    prvi = novi;
  }
  
  public boolean jePrazna() {
    return prvi == null;
  }
  
  public void stampaj() {
    if (jePrazna())
      Svetovid.out.println("Lista je prazna ");
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
  
  public void proveriIme(String ime) {
    if (jePrazna())
      Svetovid.out.println("Lista je prazna ");
    else {
      Element tekuci = prvi;
      
      while (tekuci != null && !ime.equals(tekuci.info)) {
        tekuci = tekuci.veza;
      }
      
      if (tekuci != null)
        Svetovid.out.println("Ime \"" + ime + "\" se nalazi u listi");
      else {
        Svetovid.out.println("Ime \"" + ime + "\" se ne nalazi u listi");
        dodajElement(ime);
        Svetovid.out.println("Novo ime je dodatu u listu. Sacuvati promene u fajlu:");
        String imef = Svetovid.in.readLine("Unesite ime fajla za cuvanje: ");
        snimiPromene(imef);
      }
      
    }
  }
  
  public void snimiPromene(String f) {
    Element tekuci = prvi;
    while (tekuci != null) {
      Svetovid.out(f).println(tekuci);
      tekuci = tekuci.veza;
    }
    Svetovid.out(f).close();
  }
  
  
  
  
  
}


















