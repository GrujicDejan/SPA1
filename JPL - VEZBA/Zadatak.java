public class Zadatak {
  
  public static void main(String[] arg) {
    ListaBrojeva lista = new ListaBrojeva();  
    int n = Svetovid.in.readInt("unesite n (broj brojeva): ");
    System.out.println("unesite brojeve");
    for (int i = 0; i < n; i++) {
      double br = Svetovid.in.readDouble();
      lista.dodajNaPocetak(br);
    }
    lista.stampajNaEkran();
    
    //lista.uvecajSvakiElementZaPrethodni();
    //lista.stampajNaEkran();
    
    //lista.zbirElemenataNovaLista();
    //lista.stampajNaEkran();
    
    /*ListaBrojeva lista2 = new ListaBrojeva();  
     int n2 = Svetovid.in.readInt("unesite n (broj brojeva): ");
     System.out.println("unesite brojeve");
     for (int i = 0; i < n2; i++) {
     double br = Svetovid.in.readDouble();
     lista2.dodajNaPocetak(br);
     }
     lista2.stampajNaEkran();
     lista.spojElementeNaizmenicno(lista2);
     lista.stampajNaEkran();*/
    
    //lista.izbaciElementeSaDecimalnimZarezom();
    //lista.stampajNaEkran();
    
    //lista.skratiListu();
    //lista.stampajNaEkran();
    
    //ListaBrojeva lista3 = lista.izdvojElmenteNaNeparnimPozicijama();
    //lista.stampajNaEkran();
    //lista3.stampajNaEkran();
    
    //ListaBrojeva lista4 = lista.izdvojElmenteNaParnimPozicijama();
    //lista.stampajNaEkran();
    //lista4.stampajNaEkran();
  }
}


class ListaBrojeva {
  
  class Element {
    double info;
    Element veza;
    
    public Element(double br) {
      this.info = br;
      this.veza = null;
    }
    
    public String toString() {
      return info + "";
    }
  }
  
  
  Element prvi;
  
  
  public ListaBrojeva() {
    this.prvi = null;
  }
  
  
  public void dodajNaPocetak(double br) {
    Element novi = new Element(br);
    novi.veza = prvi;
    prvi = novi;
  }
  
  public boolean jePrazna() {
    return prvi == null;
  }
  
  public void stampajNaEkran() {
    if (jePrazna()) {
      Svetovid.out.println("Lista je prazna");
    } else {
      Svetovid.out.println("Sadrzaj liste:");
      Element tekuci = prvi;
      while (tekuci != null) {
        System.out.println(tekuci.info);
        tekuci = tekuci.veza;
      }
      System.out.println();
    }
  }
  
  /* Napraviti metod koji uve?ava svaki element za veli?inu svog prethodnika, 
   i bri�e ga ako je ve?i od broja b koji se prosle?uje kao parametar metoda. */
  public void uvecajSvakiElementZaPrethodni() {
    double b = Svetovid.in.readDouble("Unesite granicnu vrednost liste: ");
    while (prvi != null && prvi.info > b) {
      prvi = prvi.veza; // Ako je prvi veci od b, nisam siguran da li je potrebno, ne trazi se posebno u zadatku, pa bih zamolio Vas da mi kazete da li je ovaj korak suvisan ili nije.
    } 
    if (prvi != null) {
      Element prethodni = null;
      Element tekuci = prvi;
      while (tekuci.veza != null) {
        prethodni = tekuci;
        tekuci = tekuci.veza;
        tekuci.info += prethodni.info;
        if (tekuci.info > b) {
          prethodni.veza = tekuci.veza;
          tekuci = prethodni;
        }
      }
    }
  }
  
  /* Napraviti metod koji vra?a novu listu u kojoj ?e elementi biti jednaki zbiru po dva elementa iz originalne.
   Npr prvi je jednak zbiru prvog i drugog iz originalne, drugi je jednak zbiru drugog i tre?eg itd. 
   Nova lista treba da je iste du�ine, tj poslednji elementi su jednaki. */
  public void zbirElemenataNovaLista() {
    if (!jePrazna()) {
      Element tekuci = null;
      Element sledeci = prvi;
      while (sledeci.veza != null) {
        tekuci = sledeci;
        sledeci = sledeci.veza;
        tekuci.info += sledeci.info;
      }
    }
  }
  
  /* Napraviti metod kome se prosle?uje druga lista i koji spoja elemente te 
   liste u onu na kojoj je pozvan, uzimaju?i naizmeni?no elemente iz listi.*/
  public void spojElementeNaizmenicno(ListaBrojeva novaLista) {
    if (novaLista.prvi == null)
      return;
    else if (jePrazna())
      prvi = novaLista.prvi;
    else {
      Element tek = prvi.veza;
      Element novaListaTek = novaLista.prvi;
      Element pomocni = prvi;
      while (tek != null && novaListaTek != null) {
        pomocni.veza = novaListaTek;
        pomocni = pomocni.veza;
        novaListaTek = novaListaTek.veza;
        
        pomocni.veza = tek;
        pomocni = pomocni.veza;
        tek = tek.veza;
        
      }
      if (novaListaTek != null)
        pomocni.veza = novaListaTek;
    }
  }
  
  //izbaciti iz liste sve elemente koji imaju decimalne cifre
  public void izbaciElementeSaDecimalnimZarezom() {
    while (prvi != null && (int)prvi.info != prvi.info) {
      prvi = prvi.veza;
    }
    if (prvi != null) {
      Element tekuci = prvi;
      Element prethodni;
      while (tekuci.veza != null) {
        prethodni = tekuci;
        tekuci = tekuci.veza;
        if ((int)tekuci.info != tekuci.info) {
          prethodni.veza = tekuci.veza;
          tekuci = prethodni;
        }
      }
    }
  }
  
  //obrce listu, 1,2,3,..,10 postaje 10,9,8,...,2,1.
  public void obrniListu() {
    if (prvi != null && prvi.veza != null) {
      Element preth = null;
      Element tek = prvi;
      
      while (tek != null) {
        Element sled = tek.veza;
        
        tek.veza = preth;
        preth = tek;
        tek = sled;
      }
      prvi = preth;
    }
  }
  
  //Napisati metod koji skra?uje listu na poslednjih n elemenata.
  public void skratiListu() {
    if (!jePrazna()) {
      int n = Svetovid.in.readInt("Unesite broj elemenata za skracivanje: ");
      obrniListu(); //Zato sto se trazi odsecanje poslednjih n elemenata
      while (prvi != null && n > 0) {
        prvi = prvi.veza;
        n--;
      }
      obrniListu(); //Vraca listu u prvobitni redosled
    }
  }
  
  /*Napraviti metod koji vra?a novu listu tako da se u njoj nalaze izdvojeni 
   elementi iz originalne (na kojoj je pozvan metod) koji su bili neparni, 
   a u originalnoj da ostanu samo oni elementi koji su bili na parni.*/
  //IDEJA: parne pozicije su 0,2,4...., itd. Tj, kod liste [1,2,3,4,5,6], na parnim mestima su 1,3,5.
  public ListaBrojeva izdvojElmenteNaNeparnimPozicijama() {
    if (!jePrazna()) {
      ListaBrojeva neparni = new ListaBrojeva();
      
      Element neparniKraj = null;
      Element tek = prvi;
      Element preth = null;
      int br = 0;
      
      while (tek.veza != null) {
        preth  = tek;
        tek = tek.veza;
        if (br % 2 == 0) {
          preth.veza = tek.veza;
          if (neparni.prvi == null) {
            neparni.prvi = tek;
            tek.veza = null;
            neparniKraj = tek;
          }
          else {
            neparniKraj.veza = tek;
            tek.veza = null;
            neparniKraj = tek;
          }
          tek = preth;
        }
        br++;
      }
      return neparni;
    }
    return null;
  }
  
  /*Napraviti metod koji vra?a novu listu tako da se u njoj nalaze izdvojeni
   elementi iz originalne (na kojoj je pozvan metod) bili na parnim mestima, 
   a u originalnoj da ostanu samo oni koji su bili na neparnim mestima.*/
  //IDEJA: neparne pozicije su 1,3,5...., itd. Tj, kod liste [1,2,3,4,5,6], na parnim mestima su 2,4,6.
  public ListaBrojeva izdvojElmenteNaParnimPozicijama() {
    if (prvi != null) {
      ListaBrojeva parni = new ListaBrojeva();
      
      Element tek = prvi;
      Element preth = null;
      Element parniKraj = null;
      int br = 0;
      
      while (tek.veza != null) {
        preth = tek;
        tek = tek.veza;
        
        if (br % 2 != 0) {
          preth.veza = tek.veza;
          if (parni.prvi == null) {
            parni.prvi = tek;
            parniKraj = tek;
            tek.veza = null;   
          } else {
            parniKraj.veza = tek;
            tek.veza = null;
            parniKraj = parniKraj.veza;
          }
          tek = preth;
        }
        br++;
      }
      /*prvi element iz liste je paran, ali je preskocen, 
       tako da ga sad izbacujemo iz liste i dodajemo u novu*/
      Element pom = prvi;
      prvi = prvi.veza;
      pom.veza = parni.prvi;
      parni.prvi = pom;
      return parni;
    }
    return null;
  }
  
  public String toString() {
    String rez = "Lista: [ ";
    Element tekuci = prvi;
    while (tekuci != null) {
      rez += tekuci.info + " ";
      tekuci = tekuci.veza;
    }
    rez += "]";
    return rez;
  }
  
}
