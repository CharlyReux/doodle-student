package fr.istic.tlc.dao;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class commentRepository implements PanacheRepository<comment>{
    
	public void deleteAllCommentsFromPoll(long pollID){
		delete("pollID", pollID);
	}
    public List<comment> findBypollID(long pollID){
		return find("pollID", pollID).list();	
	}

}

