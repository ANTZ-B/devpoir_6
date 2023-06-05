import java.io.IOException;

public class devoir_6 {
	
	 public static void main(String[] args) throws IOException {
	        WeatherAppGUI appGUI = new WeatherAppGUI();
			WeatherDatabase.createDatabase();

	        if (InternetConnectionChecker.isConnected()) {
	            // Obtenir les informations météorologiques de la ville où se trouve l'utilisateur
	            City userCityWeather = WeatherAPI.getCityWeather(WeatherAPI.getUserCity());
	            appGUI.displayCityWeather(userCityWeather);

	            // Stocker les informations météorologiques de la ville utilisateur dans la base de données
	            WeatherDatabase.saveCityWeather(userCityWeather);
	        } else {
	            // Obtenir les informations météorologiques de la ville utilisateur à partir de la base de données
	            City userCityWeather = WeatherDatabase.getCityWeatherFromDB(WeatherAPI.getUserCity());
	            appGUI.displayCityWeather(userCityWeather);
	        }
	 }
}
