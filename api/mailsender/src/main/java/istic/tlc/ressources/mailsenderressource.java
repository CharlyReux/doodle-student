package istic.tlc.ressources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import istic.tlc.domain.Choice;
import istic.tlc.domain.Poll;
import istic.tlc.domain.User;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.MapTimeZoneCache;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/api/mailsender")
@RestController
public class mailsenderressource {

	@Inject
	Mailer mailer;

	@ConfigProperty(name = "quarkus.organizermail")
	String organizermail= "test@test.fr";

	@Operation(summary="sendAMail")
    @PostMapping("/sendMail")
	public Boolean sendASimpleEmail(@RequestBody Poll p)  {
		// Create a default MimeMessage object.
		System.setProperty("net.fortuna.ical4j.timezone.cache.impl", MapTimeZoneCache.class.getName());

	
		List<String> attendees = new ArrayList<String>(extractParticipant(p));
		
		String ics = this.getICS1(p.getSelectedChoice().getstartDate(), p.getSelectedChoice().getendDate(), p.getTitle(), attendees, organizermail);
		Mail m = new Mail();
		m.addAttachment("meeting.ics", ics.getBytes(), "text/calendar");
	
		m.setFrom(organizermail);
		m.setTo(attendees);
		m.setCc(Arrays.asList(organizermail));
		m.setFrom(organizermail);
		m.setSubject("Réunion c" + p.getTitle() + " [créneau confirmé] ");
		m.setHtml("La date définitive pour la réunion : \""+ p.getTitle() + "\" a été validée par l\'organisateur. <BR>" + 
				"Un salon a été créé de discussion pour cette réunion est accessible à cette adresse <a [href]=\" " +p.getTlkURL() + "\" target=\"_blank\">" + p.getTlkURL() + "</a>.<BR>\n" + 
				"Un pad a été créé pour cette réunion <a [href]=\""+ p.getPadURL() + "\" target=\"_blank\">\""+ p.getPadURL() + "\"</a>.</span><BR>\n");
		
		mailer.send(m);
		return true;
	}

	
	public String getICS1(Date start, Date end, String libelle, List<String> attendees, String organizer) {

		// Create a TimeZone
		TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
		TimeZone timezone = registry.getTimeZone("Europe/Paris");
		VTimeZone tz = timezone.getVTimeZone();

		// Create the event
		DateTime startd = new DateTime(start);
		DateTime endd = new DateTime(end);
		VEvent meeting = new VEvent(startd, endd, libelle);
		// add timezone info..
		meeting.getProperties().add(tz.getTimeZoneId());

		// generate unique identifier..
		UidGenerator ug = new RandomUidGenerator();
		Uid uid = ug.generateUid();
		meeting.getProperties().add(uid);


		// add attendees..
		for (String attendee : attendees) {
			Attendee p1 = new Attendee(URI.create("mailto:"+attendee));
			p1.getParameters().add(Role.REQ_PARTICIPANT);
//			dev1.getParameters().add(new Cn("Developer 1"));
			meeting.getProperties().add(p1);			
		}
		Organizer p1 = new Organizer(URI.create("mailto:"+organizer));
		meeting.getProperties().add(p1);	


		// Create a calendar
		net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
		icsCalendar.getProperties().add(Version.VERSION_2_0);
		icsCalendar.getProperties().add(new ProdId("Zimbra-Calendar-Provider"));
		icsCalendar.getProperties().add(CalScale.GREGORIAN);
		icsCalendar.getProperties().add(Method.REQUEST);
		icsCalendar.getComponents().add(tz);

		// Add the event and print
		icsCalendar.getComponents().add(meeting);
		return icsCalendar.toString();
	}

	private Set<String> extractParticipant(Poll p){
		Set<String> participant = new HashSet<String>();
		List<Choice> choix = p.getPollChoices();
		for(Choice c : choix){
			for(User u:c.getUsers()){
				participant.add(u.getMail());
			}
		}
		return participant;
	}


}
