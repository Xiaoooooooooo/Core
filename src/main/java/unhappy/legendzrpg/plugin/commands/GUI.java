package unhappy.legendzrpg.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.ui.TestUI;

public class GUI implements CommandExecutor {
    private Main plugin;
    public GUI(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("test").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("cool.cool")) {
            p.openInventory(TestUI.GUI(p));
        }
        return false;
    }
}
