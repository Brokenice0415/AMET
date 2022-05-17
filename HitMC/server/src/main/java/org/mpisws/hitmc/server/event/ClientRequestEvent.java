package org.mpisws.hitmc.server.event;

import org.mpisws.hitmc.api.state.ClientRequestType;
import org.mpisws.hitmc.server.executor.ClientRequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ClientRequestEvent extends AbstractEvent{
    private static final Logger LOG = LoggerFactory.getLogger(ClientRequestEvent.class);

    private final ClientRequestType type;
    private final int clientId;
    private String data = null;
    private String result = null;

    public ClientRequestEvent(final int id, final int clientId, ClientRequestType type, ClientRequestExecutor eventExecutor) {
        super(id, eventExecutor);
        this.type = type;
        this.clientId = clientId;
        if (ClientRequestType.SET_DATA.equals(type)){
            this.data = String.valueOf(id);
        }
    }

    public ClientRequestType getType() {
        return type;
    }

    public int getClientId() {
        return clientId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean execute() throws IOException {
        return getEventExecutor().execute(this);
    }

    @Override
    public String toString() {
        return "ClientRequestEvent{" +
                "type=" + type +
                ", clientId=" + clientId +
                ", data='" + data + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
