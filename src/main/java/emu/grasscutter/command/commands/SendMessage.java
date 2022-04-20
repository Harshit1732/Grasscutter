package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.GenshinPlayer;

import java.util.List;

@Command(label = "say", usage = "say <player> <message>", description = "Sends a message to a player as the server",
        aliases = {"sendservmsg", "sendservermessage", "sendmessage"}, permission = "server.sendmessage")
public class SendMessage implements CommandHandler {

    @Override
    public void execute(GenshinPlayer sender, List<String> args) {
        if (args.size() < 2) {
            CommandHandler.sendMessage(null, "Usage: sendmessage <player> <message>");
            return;
        }

        try {
            int target = Integer.parseInt(args.get(0));
            String message = String.join(" ", args.subList(1, args.size()));

            GenshinPlayer targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
            if (targetPlayer == null) {
                CommandHandler.sendMessage(sender, "Player not found.");
                return;
            }

            CommandHandler.sendMessage(targetPlayer, message);
            CommandHandler.sendMessage(sender, "Message sent.");
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "Invalid player ID.");
        }
    }
}