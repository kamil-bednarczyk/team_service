package sa.common.web;

import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.common.core.commands.AddMemberToTeamCommand;
import sa.common.core.commands.RemoveMemberFromTeamCommand;
import sa.common.model.MemberStatusDto;
import sa.common.model.Status;
import sa.common.repository.TeamRepository;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private CommandGateway commandGateway;

    @PutMapping
    public void addMemberToTeam(@RequestBody @Valid MemberStatusDto dto) {
        log.info(dto.toString());
        if (dto.getStatus().equals(Status.ADD)) {
            commandGateway.send(new AddMemberToTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
        } else if(dto.getStatus().equals(Status.REMOVE)) {
            commandGateway.send(new RemoveMemberFromTeamCommand(dto.getTeamId(), dto.getMemberId(), dto.getMemberName()));
        }
    }
}