package com.ipl.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.ipl.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
   
	  Match match = new Match();
	  
	  match.setId(Long.parseLong(matchInput.getId()));
	  match.setCity(matchInput.getCity());
	  
	 // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	  
	  match.setDate(LocalDate.parse(matchInput.getDate()));
	  match.setPlayerOfMatch(matchInput.getPlayer_of_match());
	  match.setVenue(matchInput.getVenue());
	  
	  //
	  String firstInningsTeam, secondInninngsTeam;
	  
	  if("bat".equals(matchInput.getToss_decision())) {
		  firstInningsTeam = matchInput.getToss_winner(); 
		  secondInninngsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
	  }else {
		  secondInninngsTeam = matchInput.getToss_winner();
		  firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
	  }
	  
	  match.setTeam1(firstInningsTeam);
	  match.setTeam2(secondInninngsTeam);
	  match.setTossWinner(matchInput.getToss_winner());
	  match.setMatchWinner(matchInput.getWinner());
	  match.setTossDecision(matchInput.getToss_decision());
	  match.setResult(matchInput.getResult());
	  match.setResultMargin(matchInput.getResult_margin());
	  match.setUmpire1(matchInput.getUmpire1());
	  match.setUmpire2(matchInput.getUmpire2());
	  return match;
  }

}