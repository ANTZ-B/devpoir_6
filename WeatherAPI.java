

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAPI {
    private static final String API_KEY = "3f69ffb164a3a6a67c6e09f00d378b88";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    
//    public static City getCityWeather(String cityName) {
//        try {
//            String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" +
//                    cityName + "&appid=" + API_KEY;
//
//            URL url = new URL(urlStr);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//
//            JSONObject jsonResponse = new JSONObject(response.toString());
//
//            // Extraire les informations nécessaires de la réponse JSON
//            String city_name = jsonResponse.getString("name");
//            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
//            int humidity = jsonResponse.getJSONObject("main").getInt("humidity");
//            double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed");
//            String date = jsonResponse.getString("dt");
//
//            return new City(city_name, temperature, humidity, windSpeed, date);
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    
    public static City getCityWeather(String cityName) throws IOException {
    	  String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=" +
                  cityName + "&appid=" + API_KEY;


        try (InputStream is = new URL(urlStr).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            Object obj = JSONValue.parse(jsonText);

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject windObj = (JSONObject) jsonObject.get("wind");
            double windSpeed = (double) windObj.get("speed");

            String city_name = (String) jsonObject.get("name");
            String date  = java.time.LocalDate.now().toString();

            JSONObject mainObj = (JSONObject) jsonObject.get("main");
            double temperature = (double) mainObj.get("temp");
            Long humidity  = (Long) mainObj.get("humidity");

            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObj = (JSONObject) weather.get(0);
            String icon = (String) weatherObj.get("icon");

            return new City(city_name, temperature, humidity, windSpeed, date, icon);
        }
    }

    public static String getUserCity() throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        String IPUser = localhost.getHostAddress().trim();

        String url = "https://api.ipgeolocation.io/ipgeo?apiKey=e6f47c1d06a441028e24d120435b952d&ip="+IPUser;
        if (InternetConnectionChecker.isConnected()) {
            try (InputStream is = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String jsonText = readAll(rd);
                Object obj = JSONValue.parse(jsonText);

                JSONObject jsonObject = (JSONObject) obj;

                return (String) jsonObject.get("city");

            } catch (Exception ex) {

            }
        }
        return null;
    }
}
