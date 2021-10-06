package unhappy.legendzrpg.plugin.listeners;

import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

@Getter
public class JoinListener implements Listener {
    @Getter
    private static Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            Document document = new Document("uuid", player.getUniqueId().toString())
                    .append("name", player.getName().toLowerCase())
                    .append("realName", player.getName())
                    .append("points", 0);
            plugin.getCollection().insertOne(document);
        }
        String joinMessage = Utils.chat("&a&lJOIN &7[&a+&7] " + " &7" + player.getName());
        if (!player.hasPlayedBefore()) {
            joinMessage = Utils.chat("&a&lWELCOME&r &7[&a+&7] " + " &7" + player.getName());
        }
        event.setJoinMessage(joinMessage);
    }
}







