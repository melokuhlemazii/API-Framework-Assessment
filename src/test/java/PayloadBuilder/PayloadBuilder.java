package PayloadBuilder;

import org.json.simple.JSONObject;

public class PayloadBuilder {
    public static JSONObject loginUserPayload(String email, String password){
        JSONObject loginUser = new JSONObject();
        loginUser.put("email", email);
        loginUser.put("password", password);

        return loginUser;
    }
}
