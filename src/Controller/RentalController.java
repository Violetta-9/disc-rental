package Controller;

import Model.Disc;
import Model.Rental;
import Model.User;
import Tools.ArgumentNullException;
import Tools.SerializableDateSaver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RentalController extends SerializableDateSaver {


    public List<Rental> rentals = new ArrayList<>();
    private Rental currentRental;

    public RentalController() {
    }

    public RentalController(User user, Disc disc) throws ArgumentNullException {

            rentals = getRentalData();

            var isUserHere = rentals.stream().filter(i -> i.getUserId()==user.getId()).collect(Collectors.toList());
            var rentalWhithDisc = isUserHere.stream().filter(i -> i.isWhetherReturned()==false).findFirst();

            var termCheck = rentalWhithDisc.stream().filter(i -> !LocalDate.now().isBefore(i.getExpectedReturnDate()) && !LocalDate.now().isAfter(i.getDateOfIssue())).collect(Collectors.toList());

            if (!rentalWhithDisc.isEmpty() || !termCheck.isEmpty()) {


                System.out.println("У вас есть просроченный диск");


            }
            if (rentalWhithDisc.isEmpty() && termCheck.isEmpty()) {
                LocalDate dateOfIsue = LocalDate.now();
                LocalDate returnDate = dateOfIsue.plusDays(31);
                currentRental = new Rental(user.getPhoneNumber(), disc.getId(), dateOfIsue, returnDate,user.getId());
                disc.setReady(false);
                setRental(currentRental);
            }



    }

    public void setCurrentRental(Rental currentRental) {
        this.currentRental = currentRental;
    }

    public Rental getCurrentRental() {
        return currentRental;
    }


    public void returnDisk(Disc disc) {
       rentals = getRentalData();
        var rentalIsThere = rentals.stream().filter(u -> u.getDiscId() == disc.getId()).filter(u -> u.isWhetherReturned() == false).findFirst();
        if (rentalIsThere.isPresent()) {
            currentRental = rentalIsThere.get();
            currentRental.setWhetherReturned(true);
            disc.setReady(true);
            save();

        }
    }

    public void setRental(Rental rental) {
        int id;
        LinkedHashSet<Rental> temp = new LinkedHashSet<Rental>(rentals);

        if (rentals.isEmpty()) {
            id = 1;
        } else {
            if (rental.getId() == 0) {
                id = rentals.get(rentals.size() - 1).getId() + 1;
            } else {
                id = rental.getId();
            }
        }
        rental.setId(id);
        temp.add(rental);

        rentals = new ArrayList<Rental>(temp);
        save();
    }


    public void save() {
        save(rentals, Rental.class);

    }

    public ArrayList<Rental> getRentalData() {
        return loadRental();
    }

}
