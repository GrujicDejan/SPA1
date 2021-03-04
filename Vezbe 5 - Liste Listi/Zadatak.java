class Zadatak {
  public static void main(String[] args) {
    SpisakFilmova spisak = new SpisakFilmova();    
    spisak.dodajFilm("FILM6");
    spisak.dodajFilm("FILM5");
    spisak.dodajFilm("FILM4");
    spisak.dodajFilm("FILM3");
    spisak.dodajFilm("FILM3");
    spisak.dodajFilm("FILM1");
    spisak.dodajFilm("FILM2");
    System.out.println(spisak);
    
    spisak.dodajGlumca("FILM5","Glumac1");
    spisak.dodajGlumca("FILM4","Glumac1");
    spisak.dodajGlumca("FILM2","Glumac1");
    spisak.dodajGlumca("FILM33", "Nece se dodati");
    spisak.dodajGlumca("FILM2","Glumac1");
    spisak.dodajGlumca("FILM2","Glumac2");
    spisak.dodajGlumca("FILM1","Glumac1");
    spisak.dodajGlumca("FILM3","GlumacX");
    spisak.dodajGlumca("FILM6","GlumacY");
    System.out.println(spisak);
    
    System.out.println("Brisanje filmova u kojima glumi Glumac1.." + spisak.izbrisiFilmove("Glumac1"));
    System.out.println(spisak);
  }
}

class SpisakFilmova {
  class Film {
    String naslov;
    Film veza;
    Glumac glumci;
    
    public Film(String naslov) {
      this.naslov = naslov;
      this.veza = null;
    }
    
    public String toString() {
      String rez = "[" + naslov + ": ";
      Glumac tek = glumci;
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
  
  class Glumac {
    String imePrezime;
    Glumac veza;
    
    public Glumac(String ip) {
      this.imePrezime = ip;
      this.veza = null;
    }
    
    public String toString() {
      return imePrezime;
    }
  }
  
  private Film prviFilm;
  
  public SpisakFilmova() {
    this.prviFilm = null;
  }
  
  public void dodajFilm(String naslov) {
    if (!postojiFilm(naslov)) {
      Film novi = new Film(naslov);
      novi.veza = prviFilm;
      prviFilm = novi;
    }
  }
  
  private boolean postojiFilm(String naslov) {
    return nadjiFilm(naslov) != null;
  }
  
  private Film nadjiFilm(String naslov) {
    Film tek = prviFilm;
    while (tek != null) {
      if (tek.naslov.equals(naslov))
        return tek;
      tek = tek.veza;
    }
    return tek;
  }
  
  public void dodajGlumca(String f, String g) {
    Film tek = nadjiFilm(f);
    if (tek != null) {
      Glumac novi = new Glumac(g);
      novi.veza = tek.glumci;
      tek.glumci = novi;
    }
  }
  
  public boolean izbrisiFilmove(String g) {
    boolean ok = false;
    while (prviFilm != null && glumiUFilmu(prviFilm, g)) {
      prviFilm = prviFilm.veza;
      ok = true;
    }
    if (prviFilm != null) {
      Film preth = prviFilm;
      while (preth.veza != null) {
        if (glumiUFilmu(preth.veza, g)) {
          preth.veza = preth.veza.veza;
          ok = true;
        }
        else
          preth = preth.veza;
      }
    }
    return ok;
  }
  
  private boolean glumiUFilmu(Film f, String g) {
    Glumac tek = f.glumci;
    while (tek != null) {
      if (tek.imePrezime.equals(g))
        return true;
      tek = tek.veza;
    }
    return false;
  }
  
  
  
  public String toString() {
    String rez = "Spisak filmova:";
    Film tek = prviFilm;
    while (tek != null) {
      rez += " " + tek;
      tek = tek.veza;
    }
    return rez;
  }
  
}
