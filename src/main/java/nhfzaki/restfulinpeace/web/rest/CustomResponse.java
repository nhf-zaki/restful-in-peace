package nhfzaki.restfulinpeace.web.rest;

/**
 * @author nhf-zaki on 12/19/18
 */
public class CustomResponse {

    private String status;
    private String message;

    public CustomResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
