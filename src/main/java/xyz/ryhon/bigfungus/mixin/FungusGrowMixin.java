package xyz.ryhon.bigfungus.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import xyz.ryhon.bigfungus.BigFungus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FungusBlock.class)
public class FungusGrowMixin {
	@Inject(at = @At("HEAD"), method = "grow", cancellable = true)
	private void growHEAD(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo info) {
		BigFungus.fungusConfig = null;
		FungusBlock fb = (FungusBlock) (Object) this;

		if (isCross(world, pos)) {
			BigFungus.forceBigFungus = true;
			return;
		}

		for (BlockPos p : new BlockPos[] {
				pos.add(1, 0, 0),
				pos.add(-1, 0, 0),
				pos.add(0, 0, 1),
				pos.add(0, 0, -1)
		}) {
			if (isCross(world, p)) {
				fb.grow(world, random, p, world.getBlockState(p));
				info.cancel();
				return;
			}
		}
	}

	boolean isCross(ServerWorld world, BlockPos pos) {
		Block b = world.getBlockState(pos).getBlock();
		return world.getBlockState(pos.add(1, 0, 0)).getBlock() == b &&
				world.getBlockState(pos.add(-1, 0, 0)).getBlock() == b &&
				world.getBlockState(pos.add(0, 0, 1)).getBlock() == b &&
				world.getBlockState(pos.add(0, 0, -1)).getBlock() == b;
	}

	@Inject(at = @At("TAIL"), method = "grow")
	private void growTAIL(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo info) {
		BigFungus.forceBigFungus = false;
		if (BigFungus.fungusConfig != null)
			BigFungus.fungusConfig.planted = true;
	}
}