package net.clanimg.noVoidFall;

import net.clanimg.noVoidFall.command.NoVoidFallCommand;
import net.clanimg.noVoidFall.listener.VoidFallListener;
import net.clanimg.noVoidFall.spawn.SpawnPointManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoVoidFall extends JavaPlugin {
    private SpawnPointManager spawnPointManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.spawnPointManager = new SpawnPointManager(this);
        getServer().getPluginManager().registerEvents(new VoidFallListener(spawnPointManager), this);

        PluginCommand command = getCommand("novoidfall");
        if (command == null) {
            getLogger().severe("Command 'novoidfall' is not defined in plugin.yml.");
            return;
        }
        command.setExecutor(new NoVoidFallCommand(spawnPointManager));
    }

    @Override
    public void onDisable() {
        // No shutdown action needed.
    }
}
