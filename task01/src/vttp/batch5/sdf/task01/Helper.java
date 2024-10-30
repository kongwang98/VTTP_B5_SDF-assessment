package vttp.batch5.sdf.task01;

public class Helper {
    public static final String[] WEATHER = { "Clear, Few clouds, Partly cloudy, Partly cloudy", "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist", "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds", "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog" 
};

public static String toWeather(int weather) {
    
    switch (weather) {
        case 1:
        case 2:
        case 3:
        case 4:
            return WEATHER[weather - 1];
        default:
            return "weird weather";
    }
}

}
