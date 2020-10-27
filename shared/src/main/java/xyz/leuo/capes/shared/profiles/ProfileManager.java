package xyz.leuo.capes.shared.profiles;

import xyz.leuo.capes.shared.mojang.MojangPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    public static ProfileManager instance;

    private Map<UUID, Profile> profiles;

    public ProfileManager() {
        instance = this;

        this.profiles = new HashMap<>();
    }

    public void findProfile(MojangPlayer mojangPlayer) {

    }

    public void updateProfile(Profile profile) {
        if(this.profiles.containsValue(profile)) {
            
        } else {
            this.profiles.put(profile.getUuid(), profile);
        }
    }
}
