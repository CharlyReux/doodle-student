# Remote meetings planning

This project is used in a course on the *ops* part at the [University of Rennes](https://www.univ-rennes1.fr/), France. It is a kind of doodle clone developed in so-called "native cloud" technologies in order to allow students to work on a continuous deployment chain in a containerized environment. Among the feature, the application automatically initializes a pad for the meeting and a chat room for the meeting participants.

- The [back](https://github.com/barais/doodlestudent/tree/main/api) is developed using the [quarkus.io](https://quarkus.io/) framework. 
- The [front](https://github.com/barais/doodlestudent/tree/main/front) is developed in [angular](https://angular.io/) using the [primeng](https://www.primefaces.org/primeng/)  angular UI component library and the [fullcalendar](https://fullcalendar.io/) graphical component.

A demo of the application is available [here](https://doodle.diverse-team.fr/).

Three videos (in french) are available. They present:
- the [main application feature](https://drive.google.com/file/d/1GQbdgq2CHcddTlcoHqM5Zc8Dw5o_eeLg/preview), 
- its [architecture](https://drive.google.com/file/d/1l5UAsU5_q-oshwEW6edZ4UvQjN3-tzwi/preview) 
- and a [short code review](https://drive.google.com/file/d/1jxYNfJdtd4r_pDbOthra360ei8Z17tX_/preview) .

For french native speaker that wants to follow the course. The course web page is available [here](https://hackmd.diverse-team.fr/s/SJqu5DjSD).

## TODO

### Front
- Make the Login and Register Section "how to make a form in angular"
- Change the function calls to retrieve user by id and not by slug (poll-service.service)
- Automatically fill the fields of the poll in which the user is participating when he is logged in
### Back
- Make the Dashboard Service(check in front/poll-service.service for the enpoints)
- probably necessary to make a class dashboardPoll to send answers to the front
- Idea for dashboard:
>> Duplicate the User data and the Poll data with onetomany relationships between the two<br>
>> the dashboard will have a user entity which will have two list of polls(one for admin and one for user)<br>
>> When the front calls "createPoll" the api gateway sends data to the poll and to the dashboard to add the poll to the Admin list (and add the user and the poll if it dosen't already exist)<br>
>> Same with the user when the function updateChoice4user is called by the front