package joshie.harvestmoon.core.handlers.api;

import joshie.harvestmoon.api.HMApi;
import joshie.harvestmoon.core.commands.CommandManager;

public class HMApiHandler {
    public static void init() {
        HMApi.CROPS = new CropRegistry();
        HMApi.COOKING = new CookingRegistry();
        HMApi.COMMANDS = CommandManager.INSTANCE;
    }
}
