import java.util.ArrayList;
import java.util.LinkedList;

class Zadatak {
  public static void main(String[] arg) {
    ArrayList<String> imena = new ArrayList<>();
    // dodavanje imena u al.
    // Umesto klasicnog dodavanja, proveravamo da li uneto ime vec postoji, ukoliko postoji, trazi ponovni unos.
    for(int i =0; i < 4; i++){
      String ime;
      do {
        ime = Svetovid.in.readLine("Unesite ime: ");
      } while (imena.contains(ime));
      imena.add(ime);
    }
    System.out.println(imena);
    
    // Dodati još neko ime na mesto 2.
    String ime;
    do {
      ime = Svetovid.in.readLine("Unesite novo ime koje se dodaje na 2. mesto: ");
    } while (imena.contains(ime));
    imena.add(1, ime);
    System.out.println(imena);
    
    // Promeniti ime na poziciji 4 i ispisati šta je bilo na toj poziciji.
    String ime2;
    do {
      ime2 = Svetovid.in.readLine("Unesite ime koje ce zameniti vec postojano ime na 4. poziciji: ");
    } while (imena.contains(ime2));
    String s = imena.set(4, ime2);
    System.out.println(imena);
    System.out.println("Na poziciji 4. bilo je sledece ime: " + s);
    
    // Ispisati na ekran elemente na parnim pozicijama.
    for (int i = 0; i < imena.size(); i++) {
      if (i % 2 == 0)
        Svetovid.out.print(imena.get(i) + " ");
    }
    Svetovid.out.println();
    
    // Ispisati elemente koji pocinju na slovo "S".
    System.out.println("Imena koja pocinju na slovo S: ");
    for(int i = 0; i < imena.size(); i++){
      String sImena = imena.get(i);
      char c = sImena.charAt(0);
      if(c == 'S'){ //nije postavljeno "|| c == 's'" iz razloga sto se radi o imenima
        System.out.print(sImena + " ");
      }
    }
    System.out.println();
    
    // Izbaciti element sa pozicije 3.
    String izbacen = imena.remove(3);
    System.out.println("Izbacen element: " + izbacen);
    System.out.println(imena);
    
    // Izbaciti element koji je jednak unetom.
    String unos = Svetovid.in.readLine("Unesite ime koje zelite da uklonite iz ArrayList: ");
    if(imena.remove(unos)){
      System.out.println("Uspesno ste uklonili ime " + unos + " iz ArrayList.");
    }
    else{
      System.out.println("U ArrayList se ne nalazi ime " + unos + ".");
    }
    System.out.println(imena);
    
    // drugi deo zadatka
    LinkedList<String> imena2 = new LinkedList<String>();
    ucitajImenaIzFajla("test.txt", imena2);
    Svetovid.out.println(imena2);
    dodajImenaIzArrayList(imena, imena2); // MALO NAPREDNIJE DODAVANJE PREKO METODA
    //imena2.addAll(imena); DODAVANJE U JEDNOJ LINIJI KODA
    Svetovid.out.println(imena2);
    stampajUFajl("test.txt", imena2);
  }
  
  private static void ucitajImenaIzFajla(String f, LinkedList<String> ll){
    if(Svetovid.testIn(f)){
      while(Svetovid.in(f).hasMore()){
        String s = Svetovid.in(f).readToken();
        ll.add(s);
      }
    }
    Svetovid.in(f).close();
  }
  
  // ovo je odradjeno ovako, kako ne bi doslo do dupliranja imena. Moglo je preko metode addAll(Collection<? extends E> c),
  // desavalo mi se da pri dodavanju elemenata iz al u ll dobijem dva imena marka i sl. iz tog razloga sam se odlucio za ovakav pristup
  private static void dodajImenaIzArrayList(ArrayList<String> al, LinkedList<String> ll){
    if(al.isEmpty()){
      return;
    }
    else{
      for(int i = 0; i < al.size(); i++) {
        if (!ll.contains(al.get(i)))
          ll.add(al.get(i));
      }
    }
  }
  
  public static void stampajUFajl(String f, LinkedList<String> ll){
    for(int i = 0; i < ll.size(); i++){
      Svetovid.out(f).println(ll.get(i));
    }
    Svetovid.out(f).close();
  }
  
  
  
  
}





