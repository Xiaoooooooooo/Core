package unhappy.legendzrpg.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.ui.TestUI;


public class InventoryClickListener implements Listener {

    private Main plugin;

    public InventoryClickListener(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();
        if (title.equals(TestUI.inventory_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (title.equals(TestUI.inventory_name)) {
                TestUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
            }
        }
    }
    @EventHandler
    public void closeInv(InventoryCloseEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = (Player) e.getPlayer();
                p.updateInventory();
            }
        }.runTaskLater(this.plugin, 1);
    }
}
