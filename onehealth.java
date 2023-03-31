import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        Material item = event.getItem().getType();

        if (item == Material.ROTTEN_FLESH) {
            // 如果是腐肉，则不增加心
            return;
        }

        // 增加0.5颗心
        double currentHealth = player.getHealth();
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (currentHealth < maxHealth) {
            double newHealth = Math.min(currentHealth + 1.0, maxHealth);
            player.setHealth(newHealth);
        }
    }
}
