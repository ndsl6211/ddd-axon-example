package ntut.csie.sslab.team.entity.model.team.event;

import com.google.gson.JsonObject;
import ntut.csie.sslab.ddd.model.common.DateProvider;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.team.entity.model.team.BoardId;
import ntut.csie.sslab.team.entity.model.team.TeamId;


public class BoardCommittedToTeam extends DomainEvent {
    private final TeamId teamId;
    private final BoardId boardId;
    private final String name;
    private final String userId;

    public BoardCommittedToTeam(TeamId teamId, BoardId boardId, String name, String userId) {
        super(DateProvider.now());
        this.teamId = teamId;
        this.boardId = boardId;
        this.name = name;
        this.userId = userId;
    }


    public BoardId boardId() {
        return boardId;
    }

    public TeamId teamId() {
        return teamId;
    }

    public String name() {
        return name;
    }

    public String userId() {
        return userId;
    }

    @Override
    public String toJsonString() {
        String remoteDomainEvent = "{\n" +
                "\"fromBC\": \"TEAM\",\n" +
                "\"eventType\": \"RemoteBoardCreated\",\n" +
                "\"payload\": {\n" +
                    "\"teamId\": \"" + this.teamId.value() + "\",\n" +
                    "\"boardId\": \"" + this.boardId.value() + "\",\n" +
                    "\"boardName\": \"" + this.name + "\",\n" +
                    "\"userId\": \"" + this.userId + "\"\n" +
                "  }\n" +
                "}";

        return remoteDomainEvent;
    }
}
