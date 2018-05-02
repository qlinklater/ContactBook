package comqlinklater.httpsgithub.contactbook;

/**
 * Created by q.linklater on 4/4/2018.
 */

public class Contact {

    private String name;
    private String phone;
    private String email;

    public Contact () {
        String name = "";
        String phone = "";
        String email = "";
    }

    public Contact( String nameInput, String phoneInput, String emailInput ) {
        name = nameInput;
        phone = phoneInput;
        email = emailInput;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}

