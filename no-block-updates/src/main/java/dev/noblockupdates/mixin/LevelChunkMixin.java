package dev.noblockupdates.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.noblockupdates.NoBlockUpdates;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Some blocks (redstone components, rails...) push their own updates to neighbours
 * when they are removed. Skip those too while the rule is on.
 */
@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {
	@WrapWithCondition(
			method = "setBlockState",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;affectNeighborsAfterRemoval(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Z)V"
			)
	)
	private boolean noblockupdates$skipRemovalUpdates(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
		return !NoBlockUpdates.isActive(level);
	}
}
