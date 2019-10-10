package net.xyfe.breadmaker;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class BreadMaker extends JavaPlugin {
  @Override
  public void onEnable() {
    new MagicMirror(this);
    new DeathMessage(this); 
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }
}