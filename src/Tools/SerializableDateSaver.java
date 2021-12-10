package Tools;

import Model.Disc;
import Model.Rental;
import Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SerializableDateSaver {
    private static String PERSON_FILE = "person.txt";
    private static String DISC_FILE = "disc.txt";
    private static String RENTAL_FILE = "rental.txt";

    public static void setPersonFile(String personFile) {
        PERSON_FILE = personFile;
    }

    public static void setDiscFile(String discFile) {
        DISC_FILE = discFile;
    }

    public static String getPersonFile() {
        return PERSON_FILE;
    }

    public static String getDiscFile() {
        return DISC_FILE;
    }

    public void save(final List<?> object, Object objectClass) {//запись в файл
        var filepath = "";

        if (objectClass.equals(User.class)) {

            filepath = PERSON_FILE;
        } else if (objectClass.equals(Disc.class)) {
            filepath = DISC_FILE;
        } else if (objectClass.equals(Rental.class)) {
            filepath = RENTAL_FILE;
        }

        try  {


            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath), object);

            //oos.writeObject(object);
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }



    }

    public ArrayList<User> loadUser() {
        ArrayList<User> users = new ArrayList<User>();
        if (fileIsEmpty(PERSON_FILE)) {
            return users;
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(PERSON_FILE)) {

                ObjectMapper objectMapper = new ObjectMapper();

                    var result=objectMapper.readValue(fileInputStream,User[].class);

                   users =new ArrayList<User>(Arrays.asList(result)) ;
                  return users;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        }
    }

    public ArrayList<Disc> loadDisc() {
        ArrayList<Disc> discs = new ArrayList<Disc>();
        if (fileIsEmpty(DISC_FILE)) {
            return discs;
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(DISC_FILE)) {
                ObjectMapper objectMapper = new ObjectMapper();

                var result=objectMapper.readValue(fileInputStream,Disc[].class);
                discs =new ArrayList<Disc>(Arrays.asList(result)) ;
                return discs;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return discs;
        }
    }

    public ArrayList<Rental> loadRental() {
        ArrayList<Rental> rentals = new ArrayList<Rental>();
        if (fileIsEmpty(RENTAL_FILE)) {
            return rentals;
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(RENTAL_FILE)) {
                ObjectMapper objectMapper = new ObjectMapper();
                var result=objectMapper.readValue(fileInputStream,Rental[].class);
                rentals =new ArrayList<Rental>(Arrays.asList(result));
                return rentals;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rentals;
        }
    }

    public static boolean fileIsEmpty(String url) {
        File file = new File(url);
        boolean result = true;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            result = br.readLine() == null;
            return result;
        } catch (Exception e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }

        return result;
    }
}
