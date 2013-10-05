package me.ultrafirefx.headsupdisplay;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HudFX extends JavaPlugin implements Listener {
	public static HudFX plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	HudFX u = new HudFX();
	/*
	 * The string entered into the config to be replaced with normal chat
	 */
	String customChat = "$chat$";
	/*
	 * The default server name
	 */
	String defaultServerName = "A Bukkit Server";
	/*
	 * Name of the plugin, for simplicity's sake
	 */
	String pluginName;
	/*
	 * Version of the plugin
	 */
	String pluginVersion;
	/*
	 * Authors of the plugin
	 */
	List<String> pluginAuthors;
	/*
	 * Player sender variable
	 */
	Player p;
	/*
	 * List of player messages, used for $chat$
	 */
	String[] msgList;
	/*
	 * PluginDescriptionFile to get settings from plugin.yml
	 */
	PluginDescriptionFile pdfFile;

	/*
	 * Called when the plugin is disabled
	 */
	@Override
	public void onDisable() {
		this.saveDefaultConfig();
		/*
		 * Prints to the console that the plugin is disabled
		 */
		pdfFile = this.getDescription();
		this.log.info(pdfFile.getName() + " is now disabled!");
	}

	/*
	 * Called when the plugin is enabled
	 */
	@Override
	public void onEnable() {
		/*
		 * Starts the frontend Metrics
		 */
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) { // Failed to submit the stats :-(
			System.out.println("Error Submitting stats!");
		}
		/*
		 * Sets pluginName, pluginVersion and pluginAuthors as the corrosponding
		 * setting in the plugin.yml
		 */
		pluginName = pdfFile.getName();
		pluginVersion = pdfFile.getVersion();
		pluginAuthors = pdfFile.getAuthors();
	}

	/*
	 * Listens for the PlayerChatEvent and sets p as who and adds message to the
	 * end of msgList
	 */
	void AsyncPlayerChatEvent(boolean async, Player who, String message,
			Set<Player> players) {
		who = p;
		int length = msgList.length;
		msgList[++length] = message;
	}

	/*
	 * onCommand method called when a player types a command
	 */
	// TODO Add the permission nodes, hud.start, hud.stop and hud.reload
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		/*
		 * Turns the sender object into the player object as a Player instance
		 */
		Player player = (Player) sender;
		/*
		 * Start of if branch
		 */
		if (commandLabel.equalsIgnoreCase("hud")) {
			if (args.length == 0) {
				return false;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")
						|| args[0].equalsIgnoreCase("h")
						|| args[0].equalsIgnoreCase("?")) {
					/*
					 * If args[0] equalsIngnoreCase, "start" or "s"
					 */
				} else if (args[0].equalsIgnoreCase("start")
						|| args[0].equalsIgnoreCase("s")) {
					// TODO Add start method
					/*
					 * If args[0] equalsIngnoreCase, "stop" or "st"
					 */
				} else if (args[0].equalsIgnoreCase("stop")
						|| args[0].equalsIgnoreCase("st")) {
					// TODO Add stop method
					/*
					 * If args[0] equalsIngnoreCase, "reload"
					 */
				} else if (args[0].equalsIgnoreCase("reload")) {
					/*
					 * call onDisable() then onEnable()
					 */
					onDisable();
					onEnable();
					return true;
					/*
					 * If args[0] equalsIngnoreCase, "credits"
					 */
				} else if (args[0].equalsIgnoreCase("credits")) {
					/*
					 * Sends the player a message in aqua saying "HeadsUpDisplay
					 * vPluginVersion was made by PluginAuthors!"
					 */
					player.sendMessage(ChatColor.AQUA + "HeadsUpDisplay v"
							+ pluginVersion + " was made by " + pluginAuthors
							+ "!");
					/*
					 * If none of the above methods were called
					 */
				} else {
					/*
					 * sends the player the message "Incorrect command!" and
					 * returns false
					 */
					player.sendMessage("Incorrect command!");
					return false;
				}
			}
		}
		return false;
	}
}
