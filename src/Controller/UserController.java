package Controller;

import Model.User;
import com.company.ArgumentNullException;
import com.company.SerializableDateSaver;
import java.util.ArrayList;
import java.util.LinkedHashSet;


public class UserController extends SerializableDateSaver {


    private ArrayList<User> users;
    private User currentUser;
    private boolean isNewUser = false;


    public UserController() {

    }

    public UserController(String surname, String phoneNumber) throws ArgumentNullException {
        if (surname.trim().isEmpty()) {
            throw new ArgumentNullException("Фамилия человека не может быть пустым");
        }
        if (phoneNumber.trim().isEmpty()) {
            throw new ArgumentNullException("Номер телефона  не может быть пустым");
        }
        var userFromFile = this.search(surname, phoneNumber);
        if (userFromFile != null) {
            currentUser = userFromFile;

        } else {
            currentUser = new User();
            isNewUser = true;

        }


    }

    public void setCurrentUser(User currentUser) {
        currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setUsers(ArrayList<User> users) {
        users = users;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void save() {
        save(users, User.class);

    }

    private ArrayList<User> getUsersData() {
        return loadUser();
    }

    public void setUser(User user) {
        int id;

        LinkedHashSet<User> temp = new LinkedHashSet<>(users);

        if (users.isEmpty()) {
            id = 1;
        } else {
            if (user.getId() == 0) {
                id = users.get(users.size() - 1).getId() + 1;
            } else {
                id = user.getId();
            }
        }
        user.setId(id);
        temp.add(user);

        users = new ArrayList<>(temp);
        save();
    }

    public void setNewUserData(String name, String surname, String patronymic, String phoneNumber, String address) throws ArgumentNullException {
        if (surname.trim().isEmpty() || name.trim().isEmpty()) {
            throw new ArgumentNullException("Инициалы человека не могут быть пустыми");
        }
        if (phoneNumber.trim().isEmpty()) {
            throw new ArgumentNullException("Номер телефона  не может быть пустым");
        }
        if (address.trim().isEmpty()) {
            throw new ArgumentNullException("Адрес не может быть пустым");
        }
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setPatronymic(patronymic);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setAddress(address);
        setUser(currentUser);
        save();
        this.isNewUser = false;


    }

    public void deleteUser() {
        users.remove(currentUser);
        currentUser = null;
        save();
    }

    public void editUser(String name, String surname, String phoneNumber, String address) throws ArgumentNullException{
        if (surname.trim().isEmpty() || name.trim().isEmpty()) {
            throw new ArgumentNullException("Инициалы человека не могут быть пустыми");
        }
        if (phoneNumber.trim().isEmpty()) {
            throw new ArgumentNullException("Номер телефона  не может быть пустым");
        }
        if (address.trim().isEmpty()) {
            throw new ArgumentNullException("Адрес не может быть пустым");
        }
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setAddress(address);
        save();
    }

    public User search(String surname, String phoneNumber) {
        users = getUsersData();
        var userFromFile = users.stream().filter(u -> u.getSurname().equals(surname) && u.getPhoneNumber().equals(phoneNumber)).findFirst();
        if (userFromFile.isPresent()) {
            return userFromFile.get();
        } else {

            return null;
        }
    }
}