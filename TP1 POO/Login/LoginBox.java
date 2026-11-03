package Login;

import User.User;

public class LoginBox {

    private String email;
    private String password;

    public LoginBox(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
