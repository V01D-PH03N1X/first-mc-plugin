package org.devcloud.testing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Feed extends Command {
    public Feed()
    {
        super("feed", "Feed a player", "/feed <player>", List.of("feed", "f"));
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String label, @NotNull String[] args)
    {
        if (!commandSender.hasPermission("testing.feed"))
        {
            commandSender.sendMessage("You do not have permission to use this command!");
            return false;
        }
        if (commandSender instanceof Player player)
        {
            if (args.length == 0)
            {
                player.setFoodLevel(20);
                player.sendMessage("You have been fed!");
            } else if (args.length == 1)
            {
                Player target = player.getServer().getPlayer(args[0]);
                if (target != null) {
                    target.setFoodLevel(20);
                    target.sendMessage("You have been fed!");
                    player.sendMessage("You have fed " + target.getName());
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
