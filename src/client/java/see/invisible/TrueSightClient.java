package see.invisible;

import net.fabricmc.api.ClientModInitializer;

public class TrueSightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This code runs on the client side only
        TrueSight.LOGGER.info("TrueSight mod initialized - Client side");
        TrueSight.LOGGER.info("Invisible players will now be rendered as fully visible!");
    }
}
