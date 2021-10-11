
/**
 * This is the main class for page objects
 */
public class Base {


    protected String appidKey;
    protected double longitude = 74.001;
    protected double latitude = 40.002;


    protected String appidKeyData = "916b13b695a5378efd126b1299fd70b3";

    public void setAppidKey(String key) {
        this.appidKey = key;
    }

    public String getAppidKey() {
        return appidKey;
    }


}
