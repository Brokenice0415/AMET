package org.mpisws.hitmc.server.event;

import org.mpisws.hitmc.api.MessageType;
import org.mpisws.hitmc.server.executor.LeaderToFollowerMessageExecutor;

import java.io.IOException;

public class LeaderToFollowerMessageEvent extends AbstractEvent{
    private final int sendingSubnodeId;
    private final int receivingNodeId;
    private final String payload;
    private final int type;
    private final long zxid;

    public LeaderToFollowerMessageEvent(final int id,
                                        final int sendingSubnodeId,
                                        final int receivingNodeId,
                                        final int type,
                                        final long zxid,
                                        final String payload,
                                        final LeaderToFollowerMessageExecutor messageExecutor) {
        super(id, messageExecutor);
        this.sendingSubnodeId = sendingSubnodeId;
        this.receivingNodeId = receivingNodeId;
        this.payload = payload;
        this.type = type;
        this.zxid = zxid;
    }

    public int getSendingSubnodeId() {
        return sendingSubnodeId;
    }

    public int getReceivingNodeId() {
        return receivingNodeId;
    }

    public int getType() {
        return type;
    }

    public long getZxid() {
        return zxid;
    }

    @Override
    public boolean execute() throws IOException {
        return getEventExecutor().execute(this);
    }

    @Override
    public String toString() {
        String action = "LeaderToFollowerMessageEvent";
        switch (type) {
            case MessageType.DIFF:
            case MessageType.TRUNC:
            case MessageType.SNAP:
                action = "LeaderSyncFollower";
                break;
            case MessageType.UPTODATE:
                action = "LeaderProcessACKLD";
                break;
            case MessageType.COMMIT:
                action = "LeaderProcessACK";
                break;
        }
        return action + "{" +
                "id=" + getId() +
                ", receivingNodeId=" + receivingNodeId +
                ", predecessors=" + getDirectPredecessorsString() +
                ", type=" + type +
                ", " + payload +
                getLabelString() +
                '}';
    }
}