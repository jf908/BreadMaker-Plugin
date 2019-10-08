package net.xyfe.breadmaker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MagicMirror extends AutoListener {
  private JavaPlugin plugin;
  private Set<UUID> teleporting = new HashSet<>();

  public MagicMirror(JavaPlugin plugin) {
    super(plugin);
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if(event.getItem() != null
      && event.getItem().getType().equals(Material.WATCH)
      && !teleporting.contains(event.getPlayer().getUniqueId())) {

      Player player = event.getPlayer();
        
      teleporting.add(player.getUniqueId());

      World world = player.getWorld();
      if(!world.getEnvironment().equals(Environment.NORMAL)) return;
      Location loc = world.getHighestBlockAt(world.getSpawnLocation()).getLocation()
        .add(0.5, 0, 0.5)
        .setDirection(player.getLocation().getDirection());
      world.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 0.2f, 2f);

      new BukkitRunnable() {
        @Override
        public void run() {
          if(player.isDead()) return;
          if(!player.getWorld().equals(world)) return;
          player.teleport(loc);
          world.playSound(loc, Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1f, 1.8f);
          world.spawnParticle(Particle.SPELL_MOB, loc, 32, 0, 1, 0);

          teleporting.remove(player.getUniqueId());
        }
      }.runTaskLater(plugin, 20);
    }
  }
}