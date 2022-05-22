import { Component, OnInit } from '@angular/core';
import { CardSmallComponentComponent } from '../card-small-component/card-small-component.component';
import {Card} from './Card';
import { JWTTokenService } from '../jwttoken-service.service';
import { AppCookieService } from '../app-cookie-service.service';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css'],
  providers: [CardSmallComponentComponent,JWTTokenService,AppCookieService]

})
export class HomeComponentComponent implements OnInit {

  isLogged= false
  name = ""
  mail = "" 

  constructor(
    private appCookieService: AppCookieService,
    private jwtService: JWTTokenService) { }


  cards: Card[] = [];

  ngOnInit(): void {
    this.jwtService.setToken(this.appCookieService.get("token"))//TODO changer "token" en autre chose
    this.isLogged = this.jwtService.getEmailId()!=null
    this.name = this.jwtService.getUser();
    this.mail = this.jwtService.getEmailId();

    this.cards.push(new Card('assets/1.png', {backgroundColor: '#44baf2', color: 'white'}, 'Créez un sondage', 'Définissez plusieurs créneaux pour votre réunion.'));
    this.cards.push(new Card('assets/2.png', {backgroundColor: '#fc506d', color: 'white'}, 'Envoyez vos invitations', 'Les participants aux sondages pourront voter pour les dates qui leur conviennent le mieux !'));
    this.cards.push(new Card('assets/3.png', {backgroundColor: '#8f3ee8', color: 'white'}, 'Faites votre choix', 'Vous pourrez obtenir en direct les résultats du sondage afin de choisir au mieux la meilleure proposition.'));


  }

  onUnLog(){
    this.appCookieService.set("token","");
    this.jwtService.setToken("")
    window.location.reload()
  }

}
