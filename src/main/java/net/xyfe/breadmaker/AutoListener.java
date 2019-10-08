package net.xyfe.breadmaker;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoListener implements Listener {
  public AutoListener(JavaPlugin plugin) {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
}