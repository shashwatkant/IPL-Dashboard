package com.ipl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.model.Team;
import com.ipl.repository.MatchRepository;
import com.ipl.repository.TeamRepository;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
//	public TeamController(TeamRepository teamRepository) {
//		this.teamRepository = teamRepository;
//	}
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
	
		Team team= this.teamRepository.findByTeamName(teamName);
		team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
		return team;
		
	}
}
