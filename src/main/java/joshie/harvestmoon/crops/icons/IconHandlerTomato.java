package joshie.harvestmoon.crops.icons;

import joshie.harvestmoon.api.crops.ICropIconHandler;
import joshie.harvestmoon.core.lib.HMModInfo;
import joshie.harvestmoon.init.HMCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IconHandlerTomato implements ICropIconHandler {
    private IIcon[] stageIcons;

    @SideOnly(Side.CLIENT)
    public IIcon getIconForStage(int stage) {
        if (stage <= 2) return stageIcons[0];
        else if (stage <= 4) return stageIcons[1];
        else if (stage <= 6) return stageIcons[2];
        else if (stage <= 9) return stageIcons[3];
        else return stageIcons[4];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        stageIcons = new IIcon[5];
        for (int i = 0; i < stageIcons.length; i++) {
            stageIcons[i] = register.registerIcon(HMModInfo.CROPPATH + HMCrops.tomato.getUnlocalizedName() + "_" + (i + 1));
        }
    }
}