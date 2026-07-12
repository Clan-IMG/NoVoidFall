package net.clanimg.noVoidFall.spawn;

import net.clanimg.noVoidFall.NoVoidFall;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Optional;

public final class SpawnPointManager {
    private final NoVoidFall plugin;

    public SpawnPointManager(NoVoidFall plugin) {
        this.plugin = plugin;
    }

    public void setSpawn(Location location) {
        plugin.getConfig().set("spawn.world", location.getWorld().getName());
        plugin.getConfig().set("spawn.x", location.getX());
        plugin.getConfig().set("spawn.y", location.getY());
        plugin.getConfig().set("spawn.z", location.getZ());
        plugin.getConfig().set("spawn.yaw", location.getYaw());
        plugin.getConfig().set("spawn.pitch", location.getPitch());
        plugin.saveConfig();
    }

    public Optional<Location> getSpawn() {
        ConfigurationSection spawnSection = plugin.getConfig().getConfigurationSection("spawn");
        if (spawnSection == null) {
            return Optional.empty();
        }

        String worldName = spawnSection.getString("world");
        if (worldName == null || worldName.isBlank()) {
            return Optional.empty();
        }

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return Optional.empty();
        }

        double x = spawnSection.getDouble("x");
        double y = spawnSection.getDouble("y");
        double z = spawnSection.getDouble("z");
        float yaw = (float) spawnSection.getDouble("yaw");
        float pitch = (float) spawnSection.getDouble("pitch");
        return Optional.of(new Location(world, x, y, z, yaw, pitch));
    }
}
