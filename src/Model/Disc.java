package Model;


import com.company.ArgumentNullException;

import java.io.Serializable;
import java.util.Calendar;

public class Disc implements Serializable {

    private static final long serialVersionUID = 7725005602851474536L;
    private int id;
    private String name;
    private String genre;
    private String releaseYear;
    private String producer;
    private boolean isReady;

    public Disc() {

    }

    public Disc(String name, String genre, String releaseYear, String producer) throws ArgumentNullException {
        if (name.trim().isEmpty()) {
            throw new ArgumentNullException("Имя человека не может быть пустым");
        }
        if (genre.trim().isEmpty()) {
            throw new ArgumentNullException("Жанр не может быть пустым");
        }
        if (releaseYear.trim().isEmpty()) {
            throw new ArgumentNullException("Год выпуска не может быть пустым");
        }
        if (producer.trim().isEmpty()) {
            throw new ArgumentNullException("Имя продюсера  не может быть пустым");
        }

        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.producer = producer;
        isReady=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }
    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isReady() {
        return isReady;
    }

    @Override
    public String toString() {
        return "\nDisc{" + '\n'+
                "id=" + id + '\n'+
                "есть ли в наличии='"+isReady + '\''+'\n'+
                "Название='" + name + '\''+ '\n' +
                "Жанр='" + genre + '\''+ '\n' +
                "Год выпуска='" + releaseYear + '\'' + '\n' +
                "Продюсер='" + producer + '\'' + '\n' +
                '}';
    }
}