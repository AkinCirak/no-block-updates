package dev.noblockupdates.mixin;

import dev.noblockupdates.NoBlockUpdates;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Every block change goes through Level.setBlock(pos, state, flags, recursionLeft).
 * When the rule is on we rewrite the flags:
 *  - add UPDATE_KNOWN_SHAPE (16): skips neighbour shape updates, which is what makes
 *    flowers, torches, sand, etc. pop off when the block they sit on changes
 *  - remove UPDATE_NEIGHBORS (1): skips classic neighbour updates (redstone, observers...)
 * UPDATE_CLIENTS (2) is left alone so players still see the change.
 */
@Mixin(Level.class)
public abstract class LevelMixin {
	@ModifyVariable(
			method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z",
			at = @At("HEAD"),
			ordinal = 0,
			argsOnly = true
	)
	private int noblockupdates$stripUpdateFlags(int flags) {
		if (NoBlockUpdates.isActive((Level) (Object) this)) {
			return (flags | Block.UPDATE_KNOWN_SHAPE) & ~Block.UPDATE_NEIGHBORS;
		}
		return flags;
	}
}
