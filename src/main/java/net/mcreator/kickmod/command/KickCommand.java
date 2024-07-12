package net.mcreator.kickmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.server.MinecraftServer;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class KickCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(literal("kick")
            .requires(source -> source.hasPermissionLevel(0)) // Allow all players to use the command
            .then(argument("player", StringArgumentType.string())
            .executes(context -> {
                String playerName = StringArgumentType.getString(context, "player");
                MinecraftServer server = context.getSource().getServer();
                ServerPlayerEntity playerToKick = server.getPlayerManager().getPlayer(playerName);
                if (playerToKick != null) {
                    playerToKick.networkHandler.disconnect(Text.of("You have been kicked from the server."));
                    context.getSource().sendFeedback(Text.of("Player " + playerName + " has been kicked."), true);
                    return 1; // Command executed successfully
                } else {
                    context.getSource().sendFeedback(Text.of("Player not found."), false);
                    return 0; // Command failed
                }
            })));
    }
}
