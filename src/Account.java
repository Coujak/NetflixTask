
import java.time.LocalDate;

public class Account {

    private String username;
    private String password;
    private LocalDate dateOfCreation;
    private LocalDate endOfSubscription;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getEndOfSubscription() {
        return endOfSubscription;
    }

    public void setEndOfSubscription(LocalDate endOfSubscription) {
        this.endOfSubscription = endOfSubscription;
    }
}

