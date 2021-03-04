class z1v1 {
  
  public static void kreirajFajl() {
    Svetovid.out("fajl1.txt").println("prvi");
    Svetovid.out("fajl1.txt").println("drugi");
    Svetovid.out("fajl1.txt").println();
    Svetovid.out("fajl1.txt").println("cetvrti");
    Svetovid.out("fajl1.txt").close();
  }
  
  public static void ispisiFajl(String imeFajla) {
    if (Svetovid.testIn(imeFajla)) {
      while (Svetovid.in(imeFajla).hasMore()) {
        String red = Svetovid.in(imeFajla).readLine();
        System.out.println(red);
      }
      Svetovid.in(imeFajla).close();
    }
    
  }
  
  public static void sumaProsek(String imeFajla) {
    if (Svetovid.testIn(imeFajla)) {
      double sum = 0;
      int count = 0;
      while(Svetovid.in(imeFajla).hasMore()) {
        double broj = Svetovid.in(imeFajla).readDouble();
        sum += broj;
        count++;
      }
      System.out.println("Suma svih brojeva je: " + sum);
      System.out.println("Prosek svih brojeva je: " + sum/count);
    }
  }
  
  public static void pronadjiIme(String imeFajla, String ime) {
    if (Svetovid.testIn(imeFajla)) {
      boolean ok = false;
      while(Svetovid.in(imeFajla).hasMore()) {
        String red = Svetovid.in(imeFajla).readLine();
        if (red.equals(ime))
          ok = true;
      }
      if (ok)
        System.out.println("Ime se nalazi u fajlu");
      else {
        System.out.println("Ime se ne nalazi u fajlu");
        Svetovid.append(imeFajla).println(ime);
      }
      Svetovid.in(imeFajla).close();
      Svetovid.out(imeFajla).close();
    }
  }
  
  
  public static void main(String[] args) {
    kreirajFajl();
    String imeFajla = Svetovid.in.readLine("Unesite ime fajla koji se ispisuje: ");
    ispisiFajl(imeFajla);
    sumaProsek("brojevi.txt");
    String ime = Svetovid.in.readLine("Unesite ime: ");
    pronadjiIme("imena.txt", ime);
  }
  
}