package Model;

import com.company.ArgumentNullException;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -3835479274051760608L;
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String address;

    public User() {
    }

    public User(String name, String surname, String patronymic, String phoneNumber, String address) throws ArgumentNullException {
        if (name.trim().isEmpty()) {
            throw new ArgumentNullException("Имя человека не может быть пустым");
        }
        if (surname.trim().isEmpty()) {
            throw new ArgumentNullException("Имя человека не может быть пустым");
        }
        if (patronymic.trim().isEmpty()) {
            throw new ArgumentNullException("Имя человека не может быть пустым");
        }
        if (phoneNumber.trim().isEmpty()) {
            throw new ArgumentNullException("Номер телефона  не может быть пустым");
        }
        if (address.trim().isEmpty()) {
            throw new ArgumentNullException("Адрес телефона  не может быть пустым");
        }
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return'\n'+ "User {" + '\n'+
                "Имя='" + name + '\''+ '\n' +
                "Фамилия='" + surname + '\''+'\n' +
                "Отчество='" + patronymic + '\''+ '\n' +
                "Номер телефона='" + phoneNumber + '\'' + '\n' +
                "Адрес='" + address + '\'' + '\n' +
                '}';
    }


}


