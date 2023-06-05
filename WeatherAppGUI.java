

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class WeatherAppGUI extends JFrame {
    private JButton redButton;
    private JLabel cityLabel;
    private JLabel tempLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedLabel;
    private JLabel dateLabel;
    private JLabel weatherIconLabel;
    private JTextField serchTextField;
    private JButton serchButton;

    public WeatherAppGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Weather App");
        setSize(400, 300);

        cityLabel = new JLabel();
        tempLabel = new JLabel();
        humidityLabel = new JLabel();
        windSpeedLabel = new JLabel();
        dateLabel = new JLabel();
        weatherIconLabel = new JLabel();
        redButton = new JButton();
        serchTextField = new JTextField();
        serchButton = new JButton("Search");

        setLayout(new GridLayout(7, 1));

        add(redButton);
        add(cityLabel);
        add(tempLabel);
        add(humidityLabel);
        add(windSpeedLabel);
        add(dateLabel);
        add(weatherIconLabel);
        add(serchTextField);
        add(serchButton);

        if (InternetConnectionChecker.isConnected()){
            redButton.setBackground(Color.GREEN);
        }else {
            redButton.setBackground(Color.red);
        }

        serchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (InternetConnectionChecker.isConnected()) {
                    // Obtenir les informations météorologiques de la ville où se trouve l'utilisateur
                    City userCityWeather = null;
                    try {
                        userCityWeather = WeatherAPI.getCityWeather(serchTextField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    displayCityWeather(userCityWeather);

                    // Stocker les informations météorologiques de la ville utilisateur dans la base de données
                    WeatherDatabase.saveCityWeather(userCityWeather);
                }
            }
        });

        setVisible(true);
    }

    public void displayCityWeather(City city) {
        cityLabel.setText("City: " + city.getCity_name());
        tempLabel.setText("Temperature: " + city.getFormattedTemperature());
        humidityLabel.setText("Humidity: " + city.getFormattedHumidity());
        windSpeedLabel.setText("Wind Speed: " + city.getFormattedWindSpeed());
        dateLabel.setText("Date: " + city.getDate());

        Image image = null;
        try {
            URL url = new URL(city.getIcon());
            image = ImageIO.read(url);
            weatherIconLabel.setIcon(new ImageIcon(image));
        }
        catch (IOException e) {
        }
    }
}