package unhappy.legendzrpg.plugin.listeners;

import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.mongodb.Stat;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

@Getter
public class BlockBreakListener implements Listener {
    @Getter
    private static Main plugin;

    public BlockBreakListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Document document = plugin.getCollection().find(eq("uuid", player.getUniqueId().toString())).first();
        int i = (int) document.get("points") + 1;
        plugin.getCollection().updateOne(
                eq("uuid", player.getUniqueId().toString()),
                combine(set("points", i), currentDate("lastModified")));
    }
}
