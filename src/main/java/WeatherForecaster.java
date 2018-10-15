import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONObject;

/**
 * Created by Victor on 04.10.2018.
 */
public class WeatherForecaster {


    public String forecast(City city) throws UnirestException {
        String lon = city.coordinates.lon;
        String lat = city.coordinates.lat;

        String url = "http://api.apixu.com/v1/current.json?key=f6da3a783d34446f8f4120423180410&q=".concat(lon).concat(",").concat(lat);
        HttpResponse<JsonNode> request = Unirest.post(url).asJson();
        JSONObject myObj = request.getBody().getObject();

        String msg = myObj.toString();
        return msg;

    }

}
