package net.clanimg.noVoidFall.command;

import net.clanimg.noVoidFall.spawn.SpawnPointManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class NoVoidFallCommand implements CommandExecutor {
    private static final String SET_SPAWN_PERMISSION = "novoidfall.command.setspawn";
    private final SpawnPointManager spawnPointManager;

    public NoVoidFallCommand(SpawnPointManager spawnPointManager) {
        this.spawnPointManager = spawnPointManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("set-spawn")) {
            sender.sendMessage("§cUsage: /novoidfall set-spawn");
            return true;
        }

        if (!sender.hasPermission(SET_SPAWN_PERMISSION)) {
            sender.sendMessage("§cDafür hast du keine Berechtigung.");
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cNur Spieler können den Spawn setzen.");
            return true;
        }

        spawnPointManager.setSpawn(player.getLocation());
        sender.sendMessage("§aNoVoidFall-Spawn wurde gesetzt.");
        return true;
    }
}
