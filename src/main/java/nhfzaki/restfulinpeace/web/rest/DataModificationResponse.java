package nhfzaki.restfulinpeace.web.rest;

/**
 * @author nhf-zaki on 12/19/18
 */
public class DataModificationResponse {

    private String status;

    public DataModificationResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
