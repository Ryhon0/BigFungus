package xyz.ryhon.bigfungus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.world.gen.feature.HugeFungusFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import xyz.ryhon.bigfungus.BigFungus;

@Mixin(HugeFungusFeature.class)
public class HugeFungusFeatureMixin {
	@ModifyVariable(method = "generate", at = @At("STORE"), ordinal = 0)
	private boolean generateIsHuge(boolean bl) {
		if (BigFungus.forceBigFungus)
			return true;
		return bl;
	}

	@ModifyVariable(method = "generate", at = @At("STORE"), ordinal = 0)
	private HugeFungusFeatureConfig generateGetConfig(HugeFungusFeatureConfig cfg) {
		BigFungus.fungusConfig = cfg;
		cfg.planted = false;
		return cfg;
	}
}
