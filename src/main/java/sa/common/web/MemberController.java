package sa.common.web;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.commands.RemoveMemberFromTeamCommand;
import sa.common.model.MemberStatusDto;
import sa.common.repository.TeamRepository;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public void addMemberToTeam(MemberStatusDto dto) {
        commandGateway.send(new AddMemberToTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
    }

    @DeleteMapping
    public void removeMemberFromTeam(MemberStatusDto dto) {
        commandGateway.send(new RemoveMemberFromTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
    }
}