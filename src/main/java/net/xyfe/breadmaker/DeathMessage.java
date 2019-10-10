package net.xyfe.breadmaker;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathMessage extends AutoListener {
  public DeathMessage(JavaPlugin plugin) {
    super(plugin);
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    String msg = event.getDeathMessage();
    Location loc = event.getEntity().getLocation();
    msg += " @ " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    event.setDeathMessage(msg);
  }
}