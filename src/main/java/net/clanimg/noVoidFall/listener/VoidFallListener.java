package net.clanimg.noVoidFall.listener;

import net.clanimg.noVoidFall.spawn.SpawnPointManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Optional;

public final class VoidFallListener implements Listener {
    private static final double VOID_TELEPORT_BUFFER = 8.0D;
    private final SpawnPointManager spawnPointManager;

    public VoidFallListener(SpawnPointManager spawnPointManager) {
        this.spawnPointManager = spawnPointManager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        Location to = event.getTo();
        if (to == null || to.getY() > to.getWorld().getMinHeight() + VOID_TELEPORT_BUFFER) {
            return;
        }

        teleportToSpawn(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onVoidDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (event.getCause() != EntityDamageEvent.DamageCause.VOID) {
            return;
        }

        if (teleportToSpawn(player)) {
            event.setCancelled(true);
        }
    }

    private boolean teleportToSpawn(Player player) {
        Optional<Location> spawn = spawnPointManager.getSpawn();
        if (spawn.isEmpty()) {
            return false;
        }

        player.setFallDistance(0.0F);
        return player.teleport(spawn.get());
    }
}
