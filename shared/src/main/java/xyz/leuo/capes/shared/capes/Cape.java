package xyz.leuo.capes.shared.capes;

import lombok.Data;

import java.util.UUID;

@Data
public class Cape {
    private final UUID uuid;
    private String name, displayName, url;
}
