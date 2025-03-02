/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 */
package net.ccbluex.liquidbounce.features.module.modules.player

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.InventoryUtils
import net.ccbluex.liquidbounce.utils.timer.MSTimer
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.init.Items
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement
import net.minecraft.network.play.client.C09PacketHeldItemChange

@ModuleInfo(name = "Gapple", description = "Eat Gapples.", category = ModuleCategory.PLAYER)
class Gapple : Module() {
    val modeValue = ListValue("Mode", arrayOf("Auto", "Once", "Head"), "Once")
    // Auto Mode
    private val healthValue = FloatValue("Health", 10F, 1F, 20F)
    private val delayValue = IntegerValue("Delay", 150, 0, 1000, "ms")
    private val noAbsorption = BoolValue("NoAbsorption",true)
    private val timer = MSTimer()

    @EventTarget
    fun onUpdate(event: UpdateEvent?) {
        when(modeValue.get().toLowerCase()){
            "once" -> {
                doEat(true)
                state = false
            }
            "auto" -> {
                if (!timer.hasTimePassed(delayValue.get().toLong()))
                    return
                if (mc.thePlayer.health <= healthValue.get()){
                    doEat(true)
                    timer.reset()
                }
            }
            "head" -> {
                if (!timer.hasTimePassed(delayValue.get().toLong()))
                    return
                if (mc.thePlayer.health <= healthValue.get()){
                    val headInHotbar = InventoryUtils.findItem(36, 45, Items.skull)
                    if(headInHotbar != -1) {
                        mc.netHandler.addToSendQueue(C09PacketHeldItemChange(headInHotbar - 36))
                        mc.netHandler.addToSendQueue(C08PacketPlayerBlockPlacement(mc.thePlayer.heldItem))
                        mc.netHandler.addToSendQueue(C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem))
                        timer.reset()
                    }
                }
            }
        }
    }

    private fun doEat(warn: Boolean) {
        if (noAbsorption.get() && !warn) {
            val abAmount = mc.thePlayer.absorptionAmount
            if (abAmount > 0)
                return
        }

        val gappleInHotbar = InventoryUtils.findItem(36, 45, Items.golden_apple)
        if (gappleInHotbar != -1) {
            mc.netHandler.addToSendQueue(C09PacketHeldItemChange(gappleInHotbar - 36))
            mc.netHandler.addToSendQueue(C08PacketPlayerBlockPlacement(mc.thePlayer.heldItem))
            repeat (35) {
                mc.netHandler.addToSendQueue(C03PacketPlayer(mc.thePlayer.onGround))
            }
            mc.netHandler.addToSendQueue(C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem))
        }else if (warn)
            LiquidBounce.hud.addNotification(Notification("No Gapple were found in hotbar.", Notification.Type.ERROR))
    }

    override val tag: String
        get() = modeValue.get()
}
