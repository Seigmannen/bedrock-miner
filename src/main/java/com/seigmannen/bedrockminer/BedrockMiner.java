package com.seigmannen.bedrockminer;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BedrockMiner implements ModInitializer {
	public static final String MOD_ID = "bedrock-miner";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Bedrock has no loot table, so we drop the block ourselves after it is broken.
		// Survival players can only break bedrock through our mixin, so no tool check is needed here
		// (the pickaxe may also have just broken from the last hit).
		PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {
			if (state.is(Blocks.BEDROCK) && !player.preventsBlockDrops()) {
				Block.popResource(level, pos, new ItemStack(Blocks.BEDROCK));
			}

			// Vanilla already takes 1 durability for mining a block, so we take the rest.
			// hurtAndBreak rolls Unbreaking for every point and does nothing in creative.
			ItemStack pickaxe = player.getMainHandItem();
			if (state.is(Blocks.BEDROCK) && pickaxe.is(Items.NETHERITE_PICKAXE)) {
				pickaxe.hurtAndBreak(BedrockMining.DURABILITY_PER_BEDROCK - 1, player, EquipmentSlot.MAINHAND);
			}
		});

		LOGGER.info("Bedrock Miner loaded");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
