package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;

import java.util.Scanner;

public class DataReader {
    private final Scanner scr = new Scanner(System.in);
    private final ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void close() {
        scr.close();
    }

    public int getInt() {
        try {
            return scr.nextInt();
        } finally {
            scr.nextLine();
        }
    }

    public String getString() {
        return scr.nextLine();
    }

    public Book ReadAndCreateBook() {
        System.out.println("Podaj tytuł utworu: ");
        String title = scr.nextLine();
        System.out.println("Podaj autora utworu: ");
        String author = scr.nextLine();
        System.out.println("Podaj rok publikacji: (rrrr)");
        int releaseDate = getInt();
        System.out.println("Podaj ilość stron: ");
        int numberOfPages = getInt();
        System.out.println("Podaj wydawnictwo: ");
        String publisher = scr.nextLine();
        System.out.println("Podaj numer ISBN: ");
        String isbn = scr.nextLine();
        return new Book(title, author, releaseDate, numberOfPages, publisher, isbn);
    }

    public Magazine ReadAndCreateMagazine() {
        System.out.println("Podaj tytuł magazynu/gazety/czasopisma: ");
        String title = scr.nextLine();
        System.out.println("Podaj wydawnictwo: ");
        String publisher = scr.nextLine();
        System.out.println("Podaj rok publikacji: (rrrr) ");
        int releaseDate = scr.nextInt();
        System.out.println("Podaj miesiąc publikacji: (mm) ");
        int month = scr.nextInt();
        System.out.println("Podaj dzień publikacji: (dd) ");
        int day = scr.nextInt();
        scr.nextLine();
        System.out.println("Podaj język publikacji: ");
        String language = scr.nextLine();
        return new Magazine(title, publisher, language, releaseDate, month, day);
    }

    public LibraryUser createLibraryUser() {
        System.out.println("Podaj imię: ");
        String firstName = scr.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scr.nextLine();
        System.out.println("Podaj PESEL: ");
        String pesel = scr.nextLine();
        return new LibraryUser(firstName, lastName, pesel);
    }

}
