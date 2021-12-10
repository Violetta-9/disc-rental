
package Model;

import Tools.ArgumentNullException;
import Tools.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.time.LocalDate;


public class Rental implements Serializable {
    private static final long serialVersionUID = -3614373723188035054L;
    private int id;
    private String personPhoneNumber;
    private int discId;
    private int userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfIssue;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expectedReturnDate;
    private boolean whetherReturned = false;

    public Rental() {

    }

    public Rental(String personPhoneNumber, int discId, LocalDate dateOfIssue, LocalDate expectedReturnDate,int userId) throws ArgumentNullException {
        if (personPhoneNumber.trim().isEmpty()) {
            throw new ArgumentNullException("Имя человека не может быть пустым");
        }
        if (discId<0) {
            throw new ArgumentNullException("ДискID не может быть меньше нуля ");
        }

        if (userId<0) {
            throw new ArgumentNullException("ПользовательID  не может быть меньше нуля");
        }
        if (dateOfIssue==null) {
            throw new ArgumentNullException("Дата выдачи не может быть равна нулю");
        }
        if (expectedReturnDate==null) {
            throw new ArgumentNullException("Дата сдачи не может быть равна нулю");
        }
        this.personPhoneNumber = personPhoneNumber;
        this.discId = discId;
        this.dateOfIssue = dateOfIssue;
        this.expectedReturnDate = expectedReturnDate;
        this.whetherReturned = false;
        this.userId=userId;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWhetherReturned(boolean whetherReturned) {
        this.whetherReturned = whetherReturned;
    }

    public boolean isWhetherReturned() {
        return whetherReturned;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public int getId() {
        return id;
    }

    public void setDiscId(int discId) {
        this.discId = discId;
    }

    public int getDiscId() {
        return discId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "\nRental{" +'\n'+
                "id=" + id +'\n'+
                "Номер телефона='" + personPhoneNumber + '\'' + '\n' +
                "ДискId=" + discId +'\n'+
                "ПользовательId=" + userId +'\n'+
                "Дата выдачи='" + dateOfIssue + '\''+'\n'+
                "Предполагаемая дата сдачи='" + expectedReturnDate + '\'' +'\n'+
                "Сдан ли='" + whetherReturned + '\''+'\n'+
                '}';
    }
}
