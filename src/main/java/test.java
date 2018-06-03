import bean.Request;

public class test {
    public static void main(String[] args) {
        Request request=new Request();
        request.setText("sadsa");
        request.setReqType(1);
        request.setCity("sad");
        System.out.println(request.getJson());
    }
}


