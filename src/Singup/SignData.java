package Singup;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignData {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty Age;

    public SignData(String firstname, String lastname, String email, String username, String pass , String age){

        this.firstName = new SimpleStringProperty(firstname);
        this.lastName =new SimpleStringProperty(lastname);
        this.email =new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password=new SimpleStringProperty(pass);
        this.Age = new SimpleStringProperty(age);

    }
    public String getFirstname (){ return (String)this.firstName.get();}
    public String getLastname (){return (String)this.lastName.get();}
    public String getEmail (){return (String)this.email.get();}
    public String getUsername (){return (String)this.username.get();}
    public String getPassword (){return (String)this.password.get();}
    public String getAge (){return (String)this.Age.get();}


    public void setFirstName(String value)
    {
        this.firstName.set(value);
    }
    public void setLastName(String value)
    {
        this.lastName.set(value);
    }
    public void setEmail(String value)
    {
        this.email.set(value);
    }
    public void setUsername (String value){this.username.set(value);}
    public void setPassword(String value){this.password.set(value);}
    public void setAge(String value) { this.Age.set(value); }


    public StringProperty firstNameProperty()
    {
        return this.firstName;
    }
    public StringProperty lastNameProperty()
    {
        return this.lastName;
    }
    public StringProperty emailProperty()
    {
        return this.email;
    }
    public StringProperty userProperty()
    {
        return this.username;
    }
    public StringProperty PassProperty(){return this.password;}
    public StringProperty ageProperty()
    {
        return this.Age;
    }

}
