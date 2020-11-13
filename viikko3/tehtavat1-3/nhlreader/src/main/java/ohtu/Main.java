package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        String nationality = "FIN";
        System.out.println("Players from " + nationality + "\n");

        List<Player> playersFin = new ArrayList();

        for (Player player : players) {
            if (player.getNationality().equals(nationality)) {
                playersFin.add(player);
            }
        }

        playersFin.stream()
                .sorted((a, b) -> b.getPoints() - a.getPoints())
                .forEach(p -> System.out.println(p));
    }

}
