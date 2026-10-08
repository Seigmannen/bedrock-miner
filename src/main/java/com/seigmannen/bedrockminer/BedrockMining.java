package com.seigmannen.bedrockminer;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * All the rules for mining bedrock live here.
 */
public final class BedrockMining {
	public static final int BASE_SECONDS = 120;
	public static final int SECONDS_PER_EFFICIENCY_LEVEL = 15;
	public static final int SECONDS_PER_HASTE_LEVEL = 20;
	public static final int MIN_SECONDS = 5;
	/** Total durability a pickaxe loses per bedrock block (vanilla already takes 1 of these). */
	public static final int DURABILITY_PER_BEDROCK = 10;

	private BedrockMining() {
	}

	/** True when this player is allowed to mine this block with our rules. */
	public static boolean canMine(BlockState state, Player player) {
		return state.is(Blocks.BEDROCK) && player.getMainHandItem().is(Items.NETHERITE_PICKAXE);
	}

	/** How many seconds it takes to mine bedrock with the player's current pickaxe and effects. */
	public static int getMiningSeconds(Player player) {
		ItemStack pickaxe = player.getMainHandItem();

		Holder<Enchantment> efficiency = player.level().registryAccess().getOrThrow(Enchantments.EFFICIENCY);
		int efficiencyLevel = EnchantmentHelper.getItemEnchantmentLevel(efficiency, pickaxe);

		// The amplifier starts at 0, so Haste I has amplifier 0.
		MobEffectInstance haste = player.getEffect(MobEffects.HASTE);
		int hasteLevel = haste == null ? 0 : haste.getAmplifier() + 1;

		int seconds = BASE_SECONDS
				- efficiencyLevel * SECONDS_PER_EFFICIENCY_LEVEL
				- hasteLevel * SECONDS_PER_HASTE_LEVEL;
		return Math.max(seconds, MIN_SECONDS);
	}

	/**
	 * Minecraft adds this much to the break progress every tick; the block breaks at 1.0.
	 * There are 20 ticks per second.
	 */
	public static float getProgressPerTick(Player player) {
		return 1.0F / (getMiningSeconds(player) * 20);
	}
}
