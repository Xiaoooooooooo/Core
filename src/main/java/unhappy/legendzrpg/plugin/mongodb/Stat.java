package unhappy.legendzrpg.plugin.mongodb;

import org.bson.Document;
import org.bukkit.Bukkit;
import unhappy.legendzrpg.plugin.Main;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Stat {

    private Main plugin;

    public void increaseAmount(int amount, String uuid) {
        Document document = plugin.getCollection().find(eq("uuid", uuid)).first();
        int i = (int) document.get("points") + amount;
        plugin.getCollection().updateOne(
                eq("uuid", uuid),
                combine(set("points", i), currentDate("lastModified")));
    }

    private void decreaseAmount(int amount, String uuid) {
        Document document = plugin.getCollection().find(eq("uuid", uuid)).first();
        int i = (int) document.get("points") - amount;
        plugin.getCollection().updateOne(
                eq("uuid", uuid),
                combine(set("points", i), currentDate("lastModified")));
    }

    public int getAmount(String uuid) {
        Document document = plugin.getCollection().find(eq("uuid", uuid)).first();
        int i = (int) document.get("points");
        return i;
    }

    public void setAmount(int amount, String uuid) {
        plugin.getCollection().updateOne(
                eq("uuid", uuid),
                combine(set("points", amount), currentDate("lastModified")));
    }

}
