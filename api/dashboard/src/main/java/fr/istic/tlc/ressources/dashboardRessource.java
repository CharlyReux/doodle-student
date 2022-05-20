package fr.istic.tlc.ressources;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.tlc.dao.dashBoardRepository;
import fr.istic.tlc.dao.pollCopyRepository;
import fr.istic.tlc.domain.dashboard;
import fr.istic.tlc.domain.pollCopy;

import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/api/dashBoard")
public class dashboardRessource{	

    @Autowired
    pollCopyRepository pollCopyRepository;

    @Autowired
    dashBoardRepository dashBoardRepository;

    @PostMapping("/addPollAdmin/{idUser}")
	@Transactional
    @Operation(summary = "Adds a poll corresponding to the user in admin poll",description = "retirieves the user if exists then add the data, else creates the user and adds the data")
	public ResponseEntity<dashboard> addPollToAdmin(@Valid @RequestBody pollCopy poll,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idUser") Long idUser) {
		dashboard dU = dashBoardRepository.findByIDUser(idUser);

        if(dU!=null){
            this.dashBoardRepository.delete(dU);
        }

        dashboard TMPdU = new dashboard();
        TMPdU.setIdUser(idUser);

        List<pollCopy> adminPoll = new ArrayList<pollCopy>();
        adminPoll.add(poll);
        if(dU!=null){
            adminPoll.addAll(dU.getadPolls());
        }
        TMPdU.setadPolls(adminPoll);

        List<pollCopy> usPoll = new ArrayList<pollCopy>();
        if(dU!=null){
            usPoll.addAll(dU.getUserPolls());
        }
        TMPdU.setUserPolls(usPoll);


        this.dashBoardRepository.persistAndFlush(TMPdU);

		return new ResponseEntity<>(TMPdU, HttpStatus.CREATED);
	}


    @GetMapping("/allDash")
    @Operation(summary = "retrieves all the dashboards")
    public ResponseEntity<List<dashboard>> getAllDashBoard() {
        return new ResponseEntity<>(this.dashBoardRepository.findAll().list(),HttpStatus.OK);
    }

    @GetMapping("/allPolls")
    @Operation(summary = "retrieves all the dashboards")
    public ResponseEntity<List<pollCopy>> getAllPolls() {
        return new ResponseEntity<>(this.pollCopyRepository.findAll().list(),HttpStatus.OK);
    }

    @PostMapping("/addPollUser/{idUser}")
	@Transactional
    @Operation(summary = "Adds a poll corresponding to the user in User poll",description = "retirieves the user if exists then add the data, else creates the user and adds the data")
	public ResponseEntity<dashboard> addPollToUser(@Valid @RequestBody pollCopy poll,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idUser") Long idUser) {
		dashboard dU = dashBoardRepository.findByIDUser(idUser);

        if(dU!=null){
            this.dashBoardRepository.delete(dU);
        }

        dashboard TMPdU = new dashboard();
        TMPdU.setIdUser(idUser);

        List<pollCopy> usPoll = new ArrayList<pollCopy>();
        usPoll.add(poll);
        if(dU!=null){
            usPoll.addAll(dU.getUserPolls());
        }
        TMPdU.setUserPolls(usPoll);

        List<pollCopy> adPoll = new ArrayList<pollCopy>();
        if(dU!=null){
            adPoll.addAll(dU.getadPolls());
        }
        TMPdU.setadPolls(adPoll);


        this.dashBoardRepository.persistAndFlush(TMPdU);

		return new ResponseEntity<>(TMPdU, HttpStatus.CREATED);
	}

}