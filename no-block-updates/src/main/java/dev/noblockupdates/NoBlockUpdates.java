package dev.noblockupdates;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public class NoBlockUpdates implements ModInitializer {
	public static final String MOD_ID = "noblockupdates";

	/**
	 * /gamerule noblockupdates:disable_block_updates true|false
	 * Off by default. Saved per world.
	 */
	public static final GameRule<Boolean> DISABLE_BLOCK_UPDATES = GameRuleBuilder.forBoolean(false)
			.category(GameRuleCategory.MISC)
			.buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "disable_block_updates"));

	@Override
	public void onInitialize() {
		// The game rule is registered by the static field above.
	}

	/** True when this is a server world and the game rule is turned on. */
	public static boolean isActive(Level level) {
		return level instanceof ServerLevel serverLevel
				&& serverLevel.getGameRules().get(DISABLE_BLOCK_UPDATES);
	}
}
