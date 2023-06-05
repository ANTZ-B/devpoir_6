

public class City {
    private String city_name;
    private double temperature;
    private Long humidity;
    private double windSpeed;
    private String date;
    private String icon;

    public City() {
    }

    public City(String city_name, double temperature, Long humidity, double windSpeed, String date, String icon) {
        this.city_name = city_name;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.date = date;
        this.icon = icon;
    }

    public String getIcon() {
        return "https://openweathermap.org/img/wn/" + this.icon +"@2x.png";
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Méthodes pour formater les attributs Temperature, humidity, windSpeed
    public String getFormattedTemperature() {
        return String.format("%.1f °C", temperature);
    }

    public String getFormattedHumidity() {
        return String.format("%d %%", humidity);
    }

    public String getFormattedWindSpeed() {
        return String.format("%.1f m/s", windSpeed);
    }
}
