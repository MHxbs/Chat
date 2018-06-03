package bean;

public class Request {
    private int reqType;
    private String text;
    private String imageUrl;
    private String city;
    private String province;
    private String Street;
    private String userId;
    private String apiKey;

    public String getJson(){
         String json="{\n" +
                "\t\"reqType\":"+reqType+",\n" +
                "    \"perception\": {\n" +
                "        \"inputText\": {\n" +
                "            \"text\": \""+text+"\"\n" +
                "        },\n" +
                "        \"inputImage\": {\n" +
                "            \"url\": \""+imageUrl+"\"\n" +
                "        },\n" +
                "        \"selfInfo\": {\n" +
                "            \"location\": {\n" +
                "                \"city\": \""+city+"\",\n" +
                "                \"province\": \""+province+"\",\n" +
                "                \"street\": \""+Street+"\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"userInfo\": {\n" +
                "        \"apiKey\": \""+apiKey+"\",\n" +
                "        \"userId\": \""+userId+"\"\n" +
                "    }\n" +
                "}";
        return json;
    }




    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


}


