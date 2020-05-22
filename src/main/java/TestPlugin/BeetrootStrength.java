package TestPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BeetrootStrength implements Listener
{
	@EventHandler
	public void onBedEnter(final PlayerBedEnterEvent event) {

		
		Player player = (Player) event.getPlayer();

		player.sendMessage("You were sent a message");
	}
}