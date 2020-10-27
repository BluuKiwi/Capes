package xyz.leuo.capes.shared.profiles;

import lombok.Data;
import xyz.leuo.capes.shared.capes.Cape;

import java.util.*;

public @Data class Profile {

    private final UUID uuid;
    private String name, customCapeURL;
    private Date lastFetch, lastUpdate;
    private boolean customCape;
    private Cape appliedCape;
    private List<Cape> ownedCapes;
}
