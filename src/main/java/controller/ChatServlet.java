package controller;

import bean.Request;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/chat")
public class ChatServlet extends HttpServlet {
    private static final String apiKey="e6ba8dc80eb04bbeb5217789fc92d656";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        System.out.println("SAdsa");
        String message=request.getParameter("message");
        System.out.println(message);

        Request requestBean=new Request();
        String url="http://openapi.tuling123.com/openapi/api/v2";
        requestBean.setReqType(0);
        requestBean.setImageUrl("imgurl");
        requestBean.setText(message);
        requestBean.setCity("重庆");
        requestBean.setProvince("南岸区");
        requestBean.setStreet("崇文路");
        requestBean.setApiKey(apiKey);
        requestBean.setUserId("2132");


        CloseableHttpClient client= HttpClients.createDefault();

        HttpPost post=new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Accept-Charset", "UTF-8");
        StringEntity se=new StringEntity(requestBean.getJson(),"UTF-8");
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        System.out.println("123"+se.toString());
        post.setEntity(se);
        CloseableHttpResponse response1=client.execute(post);

        HttpEntity entity=response1.getEntity();

        String json= EntityUtils.toString(entity,"UTF-8");
        System.out.println(requestBean.getJson());
        System.out.println(json);
        JSONObject jsonObject=JSONObject.fromObject(json);
        JSONArray array=jsonObject.getJSONArray("results");
       /* List<String> list=new ArrayList<String>();
        for ( int i=0;i<array.size();i++){
            JSONObject object=array.getJSONObject(i);
            if(object.getString("resultType").equals("text")) {
                JSONObject subobject = (JSONObject) object.get("values");
                String text = subobject.getString("text");
                list.add(text);
            }else if (object.getString("resultType").equals("url")){
                JSONObject subobject = (JSONObject) object.get("values");
                String text = subobject.getString("url");
                list.add(text);
            }
        }*/
        
            JSONObject object = array.getJSONObject(0);
            JSONObject subobject = (JSONObject) object.get("values");
            String text = subobject.getString("text");
            System.out.println(text);
            String resJson="{\"message\": \""+message+"\",\"text\":\""+text+"\"}";
            response.getWriter().print(resJson);

    }
}
