package org.devcloud.testing;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.devcloud.testing.commands.Debug;
import org.devcloud.testing.commands.Feed;
import org.devcloud.testing.commands.Heal;

import java.util.logging.Logger;

/**
 * The main plugin class.
 */
public final class Testing extends JavaPlugin {

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("Hello Bananas! This is a test plugin!");
        CommandMap commandMap = getServer().getCommandMap();

        // Register commands
        commandMap.register("heal", new Heal());
        commandMap.register("feed", new Feed());
        commandMap.register("debug", new Debug());
    }

    @Override
    public void onDisable() {
        logger.info("The Banana is a lie!");
    }
}
