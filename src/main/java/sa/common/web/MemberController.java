package sa.common.web;

import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.commands.RemoveMemberFromTeamCommand;
import sa.common.model.MemberStatusDto;
import sa.common.repository.TeamRepository;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public void addMemberToTeam(@RequestBody @Valid MemberStatusDto dto) {
        commandGateway.send(new AddMemberToTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
    }

    @DeleteMapping
    public void removeMemberFromTeam(@RequestBody @Valid MemberStatusDto dto) {
        commandGateway.send(new RemoveMemberFromTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
    }
}