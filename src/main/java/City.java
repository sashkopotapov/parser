/**
 * Created by Victor on 03.10.2018.
 */
//import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Executors;


@Getter
@Setter
@ToString
public class City {
    public String name;
    public String url;
    private String administrativeArea;
    private String numberOfCitizens;
    private String yearOfFound;
    public Coordinates coordinates;
    private String area;

    private static final int INFO_SIZE = 6;




    public static City parse(Element city) throws IOException {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").get(0);
            Element adminArea = info.get(2).select("a").get(0);
            Element population = info.get(3);
            Element foundation = info.get(4).select("a").get(0);
            Element area = info.get(5);

//            System.out.println(foundation);
            City myCity = new City();

            myCity.setName(anchor.attr("title"));
            myCity.setUrl(String.format("https://uk.wikipedia.org%s", anchor.attr("href")));
            myCity.setAdministrativeArea(adminArea.attr("title"));
            myCity.setNumberOfCitizens(population.text());
            myCity.setYearOfFound(foundation.attr("title"));
            myCity.setArea(area.text());
            Coordinates coord = Coordinates.getCoordinates(myCity);
//            System.out.println(coord.lon);
            myCity.setCoordinates(coord);

            return myCity;
        }
        return null;
    }

}
//<td><a href="/wiki/%D0%A4%D0%B0%D0%B9%D0%BB:Avdiivka_gerb.png" class="image"><img alt="Avdiivka gerb.png" src="//upload.wikimedia.org/wikipedia/commons/thumb/3/34/Avdiivka_gerb.png/50px-Avdiivka_gerb.png" width="50" height="47" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/3/34/Avdiivka_gerb.png/75px-Avdiivka_gerb.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/3/34/Avdiivka_gerb.png/100px-Avdiivka_gerb.png 2x" data-file-width="378" data-file-height="356"></a> </td>
//<td><a href="/wiki/%D0%90%D0%B2%D0%B4%D1%96%D1%97%D0%B2%D0%BA%D0%B0" title="Авдіївка">Авдіївка</a> </td>
//<td><a href="/wiki/%D0%94%D0%BE%D0%BD%D0%B5%D1%86%D1%8C%D0%BA%D0%B0_%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C" title="Донецька область">Донецька область</a> </td>
//<td align="right" nowrap>37 210 </td>
//<td><a href="/wiki/1778" title="1778">1778</a> </td>
//<td>29.00 </td>
