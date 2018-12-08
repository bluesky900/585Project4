package FriendsRequest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FriendsRequest {
    private final StringProperty username = new SimpleStringProperty();

    public FriendsRequest(String username) {
        this.username.set(username);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}
