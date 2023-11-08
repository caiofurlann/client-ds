package caiofurlan.clientdistributedsystems.system.connection.send;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendClientList extends Sender{

    public JsonNode generateClientListdata(String token) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);

        return generateFinalData("listar-usuarios", this.getData());
    }

    public JsonNode send(String token) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.tokenIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateClientListdata(token)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}