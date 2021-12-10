package Controller;

import Model.Disc;
import Tools.ArgumentNullException;
import Tools.SerializableDateSaver;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DiscController extends SerializableDateSaver {
    private ArrayList<Disc> discs = new ArrayList<>();


    private Disc currentDisc;
    private boolean isNewDisc = false;

    public DiscController() {

    }

    public DiscController(String name, String genre, String releaseYear, String producer) throws ArgumentNullException {
        if (name.trim().isEmpty()) {
            throw new ArgumentNullException("Название диска не может быть пустым");
        }
        if (genre.trim().isEmpty()) {
            throw new ArgumentNullException("Жанр диска не может быть пустым");
        }
        if (releaseYear.trim().isEmpty()) {
            throw new ArgumentNullException("Год не может быть пустым");
        }
        if (producer.trim().isEmpty()) {
            throw new ArgumentNullException("Имя продюсера не может быть пустым");
        }
        var discFromFile = this.search(name, genre, releaseYear, producer);

        if (discFromFile != null) {
            currentDisc = discFromFile;


        } else {
            currentDisc = new Disc();
            isNewDisc = true;

        }

    }

    public void setDiscs(ArrayList<Disc> discs) {
        this.discs = discs;
    }

    public void setCurrentDisc(Disc currentDisc) {
        this.currentDisc = currentDisc;
    }

    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    public Disc getCurrentDisc() {
        return currentDisc;
    }

    public void setNewDisc(boolean newDisc) {
        isNewDisc = newDisc;
    }

    public boolean isNewDisc() {
        return isNewDisc;
    }

    public void setNewDiscData(String name, String genre, String year, String producer) throws  ArgumentNullException {
        if (name.trim().isEmpty()) {
            throw new ArgumentNullException("Название диска не может быть пустым");
        }
        if (genre.trim().isEmpty()) {
            throw new ArgumentNullException("Жанр диска не может быть пустым");
        }
        if (year.trim().isEmpty()) {
            throw new ArgumentNullException("Год не может быть пустым");
        }
        if (producer.trim().isEmpty()) {
            throw new ArgumentNullException("Имя продюсера не может быть пустым");
        }
        currentDisc.setName(name);
        currentDisc.setGenre(genre);
        currentDisc.setReleaseYear(year);
        currentDisc.setProducer(producer);
        currentDisc.setReady(true);
        this.isNewDisc = false;
        setDisc(currentDisc);
    }

    public void setDisc(Disc disc) {
        int id;

        LinkedHashSet<Disc> temp = new LinkedHashSet<>(discs);

        if (discs.isEmpty()) {
            id = 1;
        } else {
            if (disc.getId() == 0) {
                id = discs.get(discs.size() - 1).getId() + 1;
            } else {
                id = disc.getId();
            }
        }
        disc.setId(id);
        temp.add(disc);

        discs = new ArrayList<>(temp);
        save();
    }

    public void deleteDisc() {
        discs.remove(currentDisc);
        currentDisc = null;
        save();
    }

    public void save() {
        save(discs, Disc.class);

    }

    public ArrayList<Disc> getDiscData() {
        return loadDisc();
    }

    public Disc search(String name, String genre, String releaseYear, String producer) {

        discs = getDiscData();
        var discFromFile = discs.stream().filter(u -> u.getName().equals(name)
                && u.getGenre().equals(genre)
                && u.getReleaseYear().equals(releaseYear)
                && u.getProducer().equals(producer)).findFirst();
        if (discFromFile.isPresent()) {
            return discFromFile.get();
        } else {

            return null;
        }

    }
}
