import lombok.ToString;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import javax.xml.bind.Element;
import java.io.IOException;


//TODO Create class that represents coordinates
public class Coordinates {
    public String lon;
    public String lat;
    public static Coordinates getCoordinates(City city) throws IOException {
        Coordinates myCoordinates = new Coordinates();
        String url = city.url;
        Document doc = Jsoup.connect(url).get();
        Elements coord = doc.getElementsByClass("geo");
        if(coord.isEmpty()){
            return null;
        };
        myCoordinates.lon = coord.text().split(" ")[0];
        myCoordinates.lat = coord.text().split(" ")[1];


        return myCoordinates;
    }
}
