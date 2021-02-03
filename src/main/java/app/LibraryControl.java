package app;

import exception.*;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import model.Book;
import model.Library;
import model.LibraryUser;
import model.Magazine;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Odczyt danych z pliku poprawny" + "\n");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Utworzono nową bazę danych." + "\n");
            library = new Library();
        }
    }

    void controlApp() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case DELETED_BOOKS:
                    deletedBook();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETED_MAGAZINES:
                    deletedMagazine();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUser();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji, wprowadź ponownie: " + "\n");
                    break;
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", podaj ponownie:" + "\n");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:" + "\n");
            }
        }

        return option;
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję: " + "\n");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.ReadAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane" + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnięto limit pojemności, nie można dodać kolejnej książki" + "\n");
        }
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.ReadAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane" + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnięto limit pojemności, nie można dodać kolejnego magazynu" + "\n");
        }
    }

    private void addUser() {
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications().values());
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications().values());
    }

    private void printUser() {
        printer.printUser(library.getUsers().values());
    }

    private void deletedMagazine() {
        try {
            Magazine magazine = dataReader.ReadAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("Usunięto magazyn/czasopismo z bazy danych" + "\n");
            } else
                printer.printLine("Brak wskazanego magazynu/czasopisma" + "\n");
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć magazynu/czasopisma, niepoprawne dane" + "\n");
        }
    }

    private void deletedBook() {
        try {
            Book book = dataReader.ReadAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("Usunięto książkę z bazy danych" + "\n");
            } else
                printer.printLine("Brak wskazanej książki" + "\n");
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane" + "\n");
        }
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Export danych do pliku zakończony powodzeniem" + "\n");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        dataReader.close();
        printer.printLine("Koniec programu" + "\n");
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_BOOK(1, "Dodanie książki"),
        PRINT_BOOKS(2, "Wyświetlenie dostępnych książek"),
        DELETED_BOOKS(3, "Usuń książkę z bazy danych"),
        ADD_MAGAZINE(4, "Dodanie magazynu/gazety"),
        PRINT_MAGAZINES(5, "Wyświetlenie dostępnych magazynów/gazet"),
        DELETED_MAGAZINES(6, "Usuń magazyn/czasopismo z bazy danych"),
        ADD_USER(7, "Dodaj nowego czytelnika"),
        PRINT_USERS(8, "Wyświetl wszystkich czytelników");


        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option + "\n");
            }
        }
    }
}
