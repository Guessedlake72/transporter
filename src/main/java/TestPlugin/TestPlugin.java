package TestPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin implements Listener {
	@Override
    public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		Bukkit.getPluginManager().registerEvents(this, this);

    }
    
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Uses equalsIgnoreCase() over equals() to accept "ignite" and "IgNiTe."
        if (cmd.getName().equalsIgnoreCase("ignite")) {
            // Make sure that the player specified exactly one argument (the name of the player to ignite).
            if (args.length != 1) {
                // When onCommand() returns false, the help message associated with that command is displayed.
                return false;
            }

            // Make sure the sender is a player.
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can set other players on fire.");
                sender.sendMessage("This is an arbitrary requirement for demonstration purposes only.");
                return true;
            }

            // Get the player who should be set on fire. Remember that indecies start with 0, not 1.
            Player target = Bukkit.getServer().getPlayer(args[0]);

            // Make sure the player is online.
            if (target == null) {
                sender.sendMessage(args[0] + " is not currently online.");
                return true;
            }

            // Sets the player on fire for 1,000 ticks (there are ~20 ticks in second, so 50 seconds total).
            target.setFireTicks(1000);
            return true;
        }
        return false;
    }

    
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event)
    {
    	
    	if(event.isCancelled()) return;
    	Player player = event.getPlayer();
    	if(event.getItem().getType() == Material.APPLE)
    	{	
    		Location loc = event.getPlayer().getLocation();
    		loc.setY(loc.getY() - 1);
    		Block b = loc.getBlock();
    		while(b.getType()!=Material.AIR)
    				{		
    					loc.setY(loc.getY() - 1);
    					b = loc.getBlock();
    					 if(b.getType()==Material.BEDROCK)
    					 {
    						 player.sendMessage("only bedrock");
    						 return;
    					 }
    				}
    		player.sendMessage("there is a vein");
    		while(b.getType()==Material.AIR)
			{		
				loc.setY(loc.getY() - 1);
				b = loc.getBlock();
				 if(b.getType()!=Material.AIR)
				 {
					 loc.setY(loc.getY() + 1);
					 player.teleport(loc);
					 return;
				 }
			}
    		
    	}
    	if(event.getItem().getType() == Material.BREAD)
    	{	
    		Location loc = event.getPlayer().getLocation();
    		loc.setY(loc.getY());
    		Block b = loc.getBlock();
    		int count = 0;
    		while(b.getType()==Material.AIR && count<200)
    				{		
    					loc.setY(loc.getY() + 1);
    					b = loc.getBlock();
    					count++;
    					if(count == 199)
    					{return;}
    				}
    		count = 0;
    		while(b.getType()!=Material.AIR && count<200)
			{		
				loc.setY(loc.getY()+1);
				b = loc.getBlock();
				count++;
				if(count == 199)
				{return;}
			}
    		player.sendMessage("going up");
    		player.teleport(loc);
    		
    	}
    	
    }
     
	
}