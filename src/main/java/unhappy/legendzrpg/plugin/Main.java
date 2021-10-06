package unhappy.legendzrpg.plugin;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import unhappy.legendzrpg.plugin.listeners.*;
import unhappy.legendzrpg.plugin.ui.TestUI;
import unhappy.legendzrpg.plugin.commands.Fly;
import unhappy.legendzrpg.plugin.commands.GUI;
import unhappy.legendzrpg.plugin.utils.Utils;

@Getter
public class Main extends JavaPlugin implements Listener {
    @Getter
    public static Main instance;

    private MongoClient mongoClient = MongoClients.create("mongodb+srv://Admin:Weakpass123@spigotcluster.skyow.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    private MongoDatabase database = mongoClient.getDatabase("mongodb");
    private MongoCollection<Document> collection = database.getCollection("data");

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.broadcastMessage("ay");
        connect();
        Bukkit.broadcastMessage("ay2");
        saveDefaultConfig();
        //Commands
        new GUI(this);
        new Fly(this);
        //Listeners
        new PlayerDeathListener(this);
        new InventoryClickListener(this);
        new EntityDeathListener(this);
        new BlockBreakListener(this);
        new JoinListener(this);
        TestUI.initialize();

        Bukkit.broadcastMessage(Utils.chat("&bSuccessfully Reloaded!"));
    }

    @Override
    public void onDisable() {
    }

    public void connect() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://Admin:Weakpass123@spigotcluster.skyow.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("mongodb");
        MongoCollection<Document> collection = database.getCollection("data");
        Bukkit.broadcastMessage("Loaded MongoDB");
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Bukkit.broadcastMessage("Saved " + player.getName() + " data!");
    }
}
