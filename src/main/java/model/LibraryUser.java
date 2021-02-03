package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryUser extends User {

    private List<Publication> publicationHistory = new ArrayList<>();
    private List<Publication> borrowedPublication = new ArrayList<>();

    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrowedPublication() {
        return borrowedPublication;
    }

    public LibraryUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    public void addPublicationToHistory(Publication publication) {
        publicationHistory.add(publication);
    }

    public void borrowPublication(Publication publication) {
        borrowedPublication.add(publication);
    }

    private boolean returnPublication(Publication publication) {
        boolean result = false;
        if (borrowedPublication.remove(publication)) {
            result = true;
            addPublicationToHistory(publication);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryUser)) return false;
        if (!super.equals(o)) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(publicationHistory, that.publicationHistory) &&
                Objects.equals(borrowedPublication, that.borrowedPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationHistory, borrowedPublication);
    }

    @Override
    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getPesel();
    }
}
