
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kickmod.init;

import net.mcreator.kickmod.command.KickCommand;

public class KickmodModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			KickCommand.register(dispatcher, commandBuildContext, environment);
		});
	}
}
