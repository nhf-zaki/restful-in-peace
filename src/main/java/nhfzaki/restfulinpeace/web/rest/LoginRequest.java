package nhfzaki.restfulinpeace.web.rest;

import javax.validation.constraints.NotBlank;

/**
 * @author nhf-zaki on 12/19/18
 */
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
