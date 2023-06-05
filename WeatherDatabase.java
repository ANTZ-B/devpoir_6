


import java.sql.*;

public class WeatherDatabase {
	
	 private static final String DB_URL = "jdbc:sqlite:weather.db";

	public static void saveCityWeather(City city) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String query = "INSERT INTO city_weather(city_name, temperature, humidity, windSpeed, date, icon) " +
					"VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, city.getCity_name());
			statement.setDouble(2, city.getTemperature());
			statement.setLong(3, city.getHumidity());
			statement.setDouble(4, city.getWindSpeed());
			statement.setString(5, city.getDate());
			statement.setString(6, city.getIcon());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static City getCityWeatherFromDB(String cityName) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String query = "SELECT * FROM city_weather WHERE city_name = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, cityName);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String city_name = resultSet.getString("city_name");
				double temperature = resultSet.getDouble("temperature");
				Long humidity = resultSet.getLong("humidity");
				double windSpeed = resultSet.getDouble("windSpeed");
				String date = resultSet.getString("date");
				String icon = resultSet.getString("icon");


				return new City(city_name, temperature, humidity, windSpeed, date, icon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void createDatabase() {
		String sql = "CREATE TABLE IF NOT EXISTS city_weather (\n"
				+ " id integer PRIMARY KEY,\n"
				+ " city_name text NOT NULL,\n"
				+ " temperature real,\n"
				+ "humidity int,\n"
				+ "windSpeed real,\n"
				+ "date text,\n"
				+ "icon text"
				+ ");";
		try{
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}


