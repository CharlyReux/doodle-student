package fr.istic.tlc.ressources;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
import fr.istic.tlc.domain.Dashboard;
import fr.istic.tlc.domain.PollCopy;

import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/api/dashBoard")
public class dashboardRessource{	

    @Autowired
    dashBoardRepository dashBoardRepository;

/*     @PostMapping("/addPollAdmin/{idUser}")
	@Transactional
    @Operation(summary = "Adds a poll corresponding to the user in admin poll",description = "retirieves the user if exists then add the data, else creates the user and adds the data")
	public ResponseEntity<Dashboard> addPollToAdmin(@Valid @RequestBody PollCopy poll,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idUser") Long idUser) {
		Dashboard dU = dashBoardRepository.findByIDUser(idUser);

        if(dU==null){
            dU = new Dashboard();
            dU.setIdUser(idUser);

            List<PollCopy> adminPoll = new ArrayList<PollCopy>();
            adminPoll.add(poll);

            dU.setAdminPolls(adminPoll);
            //dU.setUserPolls(new ArrayList<PollCopy>());
        }else{
            dU.addInAdmin(poll);
        }
        this.dashBoardRepository.persistAndFlush(dU);
		return new ResponseEntity<>(dU, HttpStatus.CREATED);
	}
 */

    @PostMapping("/Polls")
    @Transactional
    public ResponseEntity<Dashboard> createDashboard(@Valid @RequestBody Dashboard du){
		this.dashBoardRepository.persist(du);
        return new ResponseEntity<>(du, HttpStatus.CREATED);
    }

/*     @PostMapping("/addPollUser/{idUser}")
	@Transactional
    @Operation(summary = "Adds a poll corresponding to the user in User poll",description = "retirieves the user if exists then add the data, else creates the user and adds the data")
	public ResponseEntity<Dashboard> addPollToUser(@Valid @RequestBody PollCopy poll,@Parameter(example = "1",in = ParameterIn.PATH) @PathVariable("idUser") Long idUser) {
		Dashboard dU = dashBoardRepository.findByIDUser(idUser);

        if(dU==null){
            dU = new Dashboard();
            dU.setIdUser(idUser);

            List<PollCopy> userPoll = new ArrayList<PollCopy>();
            userPoll.add(poll);

            dU.setUserPolls(userPoll);
            dU.setAdminPolls(new ArrayList<PollCopy>());
        }else{
            dU.addInUser(poll);
        }
        this.dashBoardRepository.persistAndFlush(dU);
		return new ResponseEntity<>(dU, HttpStatus.CREATED);
	} */

}