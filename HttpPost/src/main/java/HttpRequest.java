import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    public static String doGet(String url){
        String result = "";

        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

            Map<String,List<String>> map = connection.getHeaderFields();
            for (String key:map.keySet()){
                System.out.println(key+"--->"+map.get(key));
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line="";
            while ((line = bufferedReader.readLine())!=null) {
                result += line;
            }
        }catch (Exception e){
            System.out.println("HttpRequest doGet exception.");
        }
        return  result;
    }

    public static String doPost(String url,String param){

    String result="";

        try {
            URL  urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.setDoOutput(true);
            connection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.print(param);
            printWriter.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine())!=null){
                result += line;
            }

        } catch (Exception e) {
            System.out.println("http post exception.");
            e.printStackTrace();
        }
        return result;
    }


    public  static  void main(String arg[]){
        /*
        String url = "http://192.168.1.142:5566/iob_device?SessionID=N4pO4ZzhRQOvISMgBB5VBjowOmFkbWluOjE=";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonrpc","2.0");
        jsonObject.put("id","1");
        jsonObject.put("method","QueryWhitePhone");

        JSONObject param = new JSONObject();
        param.put("PhoneNo","911");

        jsonObject.put("params",param);

        String res = HttpRequest.doPost(url,jsonObject.toString());
        System.out.println(res);
*/
        String url = "http://www.baidu.com";
        String res = HttpRequest.doGet(url);
        System.out.println(res);
    }
}
