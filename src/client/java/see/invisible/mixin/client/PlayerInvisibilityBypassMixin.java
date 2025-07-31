package see.invisible.mixin.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Force invisible players to be treated as visible on the client by
 * overriding generic invisibility checks on Entity.
 */
@Mixin(Entity.class)
public abstract class PlayerInvisibilityBypassMixin {

    // boolean Entity.isInvisible()
    @Inject(method = "isInvisible()Z", at = @At("HEAD"), cancellable = true)
    private void trueSight_forceVisible_isInvisible(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        if (self instanceof PlayerEntity && ((LivingEntity) self).hasStatusEffect(StatusEffects.INVISIBILITY)) {
            cir.setReturnValue(false);
        }
    }

    // boolean Entity.isInvisibleTo(PlayerEntity viewer)
    @Inject(method = "isInvisibleTo(Lnet/minecraft/entity/player/PlayerEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void trueSight_forceVisible_isInvisibleTo(PlayerEntity viewer, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;
        if (self instanceof PlayerEntity && ((LivingEntity) self).hasStatusEffect(StatusEffects.INVISIBILITY)) {
            cir.setReturnValue(false);
        }
    }
}