package xyz.leuo.capes.shared.capes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CapeManager {

    public static CapeManager instance;

    private Map<UUID, Cape> capes;

    public CapeManager() {
        instance = this;
        this.capes = new HashMap<>();
    }

    public Cape findCape(UUID uuid) {
        return this.capes.get(uuid);
    }
}
