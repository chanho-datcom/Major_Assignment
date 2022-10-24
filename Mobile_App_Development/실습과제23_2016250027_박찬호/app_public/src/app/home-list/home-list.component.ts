import { Component, OnInit } from '@angular/core';
import { GeolocationService } from '../geolocation.service';
import {Loc8rDataService} from '../loc8r-data.service';
import {Location} from '../location';

@Component({
  selector: 'app-home-list',
  templateUrl: `./home-list.component.html`,
  styleUrls: ['./home-list.component.css']
  
})
export class HomeListComponent implements OnInit {

  constructor(private Loc8rDataService: Loc8rDataService,
              private geolocationService: GeolocationService) { }

  public locations: Location[];

  public message: string;

  ngOnInit() {
    this.getPosition();
  }

  private getPosition(): void{
    this.message = 'Getting your location';
    this.geolocationService.getPosition(
      this.getLocations.bind(this), // bind는 대상함수의 this에 전달함
      this.showError.bind(this),
      this.noGeo.bind(this)
    )
  }

  private getLocations(position: any): void{
    this.message = 'Searching for nearby places';
    const lat: number = position.coords.latitude;
    const lng: number = position.coords.longitude;
    this.Loc8rDataService
      .getLocations(lat, lng)
      .then(foundLocations =>{
        this.message = foundLocations.length > 0 ? '': "NO locations found"
        this.locations = foundLocations
      })
  }

  private showError(error: any): void {
    this.message = error.message
  };

  private noGeo(): void{
    this.message = "Geolocation not supported by this browser"
  }

}