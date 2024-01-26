package org.example;

import org.example.dao.ElementoDao;
import org.example.dao.PrestitoDao;
import org.example.dao.UtenteDao;
import org.example.entities.*;

import java.time.LocalDate;

public class Biblioteca {
    public static void main(String[] args) {
        ElementoDao elementoD = new ElementoDao();
        PrestitoDao prestitoD = new PrestitoDao();
        UtenteDao utenteD = new UtenteDao();


//        Utente u1 = new Utente("Paolo", "Rossi", LocalDate.of(2000,9,25));
//        utenteD.save(u1);
//
//        Libro l1= new Libro("Il signore degli Anelli", 2019, 700,
//                "Tolkien", Genere.FANTASIA);
//        elementoD.save(l1);
//
//
//        Rivista r1= new Rivista("Meccanica", 2023, 200, Periodicita.MENSILE);
//        elementoD.save(r1);
//
//        Prestito p1= new Prestito(l1, u1, LocalDate.of(2023,11,5), LocalDate.of(2024,2,5),
//                LocalDate.of(2024,1,7));
//        prestitoD.save(p1);


//        Utente u2 = new Utente("Maria", "Bianchi", LocalDate.of(1995, 7, 15));
//        utenteD.save(u2);
//
//
//        Libro l2 = new Libro("Harry Potter e la Pietra Filosofale", 1997, 320,
//                "J.K. Rowling", Genere.FANTASIA);
//        elementoD.save(l2);
//
//
//        Rivista r2 = new Rivista("National Geographic", 2023, 150, Periodicita.MENSILE);
//        elementoD.save(r2);
//
//
//        Prestito p2 = new Prestito(r2, u2, LocalDate.of(2023, 12, 10), LocalDate.of(2024, 1, 10),
//                LocalDate.of(2024, 1, 8));
//        prestitoD.save(p2);
//
//
//        Utente u3 = new Utente("Giuseppe", "Verdi", LocalDate.of(1980, 3, 8));
//        utenteD.save(u3);
//
//
//        Libro l3 = new Libro("1984", 1949, 328,
//                "George Orwell", Genere.FANTASIA);
//        elementoD.save(l3);
//
//
//        Rivista r3 = new Rivista("Time", 2023, 120, Periodicita.SETTIMANALE);
//        elementoD.save(r3);

//        Elemento l3 = (Rivista) elementoD.getById(9);
//        Utente u3 = utenteD.getById(7);
//
//        Prestito p3 = new Prestito(l3, u3, LocalDate.of(2023, 10, 15), LocalDate.of(2023, 11, 15),
//                null);
//        prestitoD.save(p3);


//        Libro l4 = new Libro("Cronache di Narnia", 1950, 756,
//                "C.S. Lewis", Genere.FANTASIA);
//        elementoD.save(l4);

          elementoD.getByAutore("Tolkien").stream().forEach(System.out::println);
          Libro l4 = (Libro) elementoD.getById(2);
          System.out.println(l4);

          elementoD.getByAnnoPubblicazione(1950).stream().forEach(System.out::println);
          elementoD.getByAnnoPubblicazione(2023).stream().forEach(System.out::println);

          elementoD.getByTitolo("signo").stream().forEach(System.out::println);

          prestitoD.getPrestitiByNumeroTessera(7).stream().forEach((System.out::println));

          prestitoD.getPrestitiScadutiNonRestituiti().stream().forEach(System.out::println);
    }
}
