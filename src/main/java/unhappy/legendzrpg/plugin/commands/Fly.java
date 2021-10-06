package unhappy.legendzrpg.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import unhappy.legendzrpg.plugin.Main;
import unhappy.legendzrpg.plugin.utils.Utils;

public class Fly implements CommandExecutor {
    private final Main plugin;
    public Fly(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (sender.hasPermission("has.fly")) {
            if (p.getAllowFlight() == true) {
                p.setAllowFlight(false);
                p.sendMessage(Utils.chat("&8[&c-&8] &bFly"));
            } else {
                p.setAllowFlight(true);
                p.sendMessage(Utils.chat("&8[&a+&8] &bFly"));
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("no_permission"));
        }
        return false;
    }
}
