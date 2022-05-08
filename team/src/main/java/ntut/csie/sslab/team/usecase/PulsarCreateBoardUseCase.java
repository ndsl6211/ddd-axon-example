package ntut.csie.sslab.team.usecase;

import ntut.csie.sslab.team.entity.model.team.BoardId;
import ntut.csie.sslab.team.entity.model.team.Team;

import java.util.UUID;

public class PulsarCreateBoardUseCase {

    private TeamRepository repo;
    private PulsarEventBroker eventBroker;

    public PulsarCreateBoardUseCase(TeamRepository repo, PulsarEventBroker eventBroker) {
        this.repo = repo;
        this.eventBroker = eventBroker;
    }

    public void execute(PulsarCreateBoardUseCaseInput input, PulsarCreateBoardUseCaseOutput output) {
        Team team = repo.getById(input.getTeamId()).get();
        team.commitBoard(new BoardId(UUID.randomUUID()), input.getBoardName(), input.getUserId());

        repo.save(team);
        eventBroker.produceMessage("board", team.getDomainEvents().get(0));
        team.clearDomainEvents();

        output.setTeamId(team.getId());
    }
}
