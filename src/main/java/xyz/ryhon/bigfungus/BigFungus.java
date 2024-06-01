package xyz.ryhon.bigfungus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigFungus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("big-fungus");

	public static boolean forceBigFungus = false;
	public static HugeFungusFeatureConfig fungusConfig = null;

	@Override
	public void onInitialize() {
	}
}