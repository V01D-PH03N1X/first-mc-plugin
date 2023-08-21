package org.devcloud.testing.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Debug extends Command {
    public Debug()
    {
        super("debug", "Add damage and hunger", "/debug <player>", List.of("debug", "d"));
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args)
    {
        if (!commandSender.hasPermission("testing.debug"))
        {
            commandSender.sendMessage("You do not have permission to use this command!");
            return false;
        }
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if (args.length == 0)
            {
                player.setHealth(10);
                player.setFoodLevel(10);
                player.showTitle(Title.title(Component.text("§4§lDebugged"), Component.text("You have been damaged and hungered!")));
            } else if (args.length == 1)
            {
                Player target = player.getServer().getPlayer(args[0]);
                if (target != null) {
                    target.setHealth(10);
                    target.setFoodLevel(10);
                    target.sendMessage("You have been damaged and hungered!");
                    player.sendMessage("You have been damaged and hungered " + target.getName());
                } else
                {
                    player.sendMessage("Player not found!");
                }
            } else
            {
                player.sendMessage("Too many arguments!");
            }
        } else
        {
            commandSender.sendMessage("You must be a player to use this command!");
        }
        return true;
    }
}
