package xyz.leuo.capes.shared.mojang;

import lombok.Data;

import java.util.UUID;

@Data
public class MojangPlayer {

    private final UUID uuid;
    private final String name;

    public MojangPlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
