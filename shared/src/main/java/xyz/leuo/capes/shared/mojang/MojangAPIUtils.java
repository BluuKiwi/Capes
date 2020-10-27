package xyz.leuo.capes.shared.mojang;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MojangAPIUtils {

    private static JsonParser jsonParser = new JsonParser();

    public static MojangPlayer getPlayer(String name) {
        UUID uuid;
        String s;
        MojangPlayer player = null;
        try {
            InputStream input = new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

                StringBuilder stringBuilder = new StringBuilder();
                int cp;
                while ((cp = reader.read()) != -1) {
                    stringBuilder.append((char) cp);
                }

                String jsonString = stringBuilder.toString();
                JsonObject json = jsonParser.parse(jsonString).getAsJsonObject();
                if(json != null) {
                    uuid = UUID.fromString(json.get("id").getAsString().replaceFirst (
                            "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                            "$1-$2-$3-$4-$5"
                    ));
                    s = json.get("name").getAsString();
                    player = new MojangPlayer(uuid, s);
                }
            } finally {
                input.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return player;
    }
}
