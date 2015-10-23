package de.larmic.butterfaces.component.showcase.tree;

import java.util.Date;
import java.util.UUID;

public class NodeData {

    private final UUID uuid = UUID.randomUUID();
    private final Date createDate = new Date();

    public Date getCreateDate() {
        return createDate;
    }

    public UUID getUuid() {
        return uuid;
    }
}
