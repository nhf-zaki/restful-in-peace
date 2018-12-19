package nhfzaki.restfulinpeace.web.rest;

/**
 * @author nhf-zaki on 12/20/18
 */
public class LoginResponse {

    private String status;
    private String first_name;
    private String email;

    public LoginResponse(String status, String first_name, String email) {
        this.status = status;
        this.first_name = first_name;
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
