package com.seigmannen.bedrockminer.mixin;

import com.seigmannen.bedrockminer.BedrockMining;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla returns 0 progress for bedrock (it is unbreakable).
 * When a netherite pickaxe is used, we return our own progress instead.
 * This code runs on both client and server, so they agree on when the block breaks.
 */
@Mixin(BlockBehaviour.class)
public class BedrockDestroyProgressMixin {
	@Inject(method = "getDestroyProgress", at = @At("HEAD"), cancellable = true)
	private void bedrockMiner$mineBedrock(BlockState state, Player player, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
		if (BedrockMining.canMine(state, player)) {
			cir.setReturnValue(BedrockMining.getProgressPerTick(player));
		}
	}
}
