package model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable, Comparable<Publication>, CsvConvertible {

    private String title;
    private String publisher;
    private int releaseDate;

    Publication(String title, String publisher, int releaseDate) {
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(int year) {
        this.releaseDate = year;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Tytuł: " + title + "\n" +
                "Wydawnictwo: " + publisher + "\n" +
                "Rok powstania: " + releaseDate + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return releaseDate == that.releaseDate &&
                Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publisher, releaseDate);
    }

    @Override
    public int compareTo(Publication p) {
        return title.compareToIgnoreCase(p.title);
    }
}