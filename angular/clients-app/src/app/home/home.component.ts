import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title = 'Bienvenido a Angular-Spring API';
  practica: String = ' practicas con Angular 16.2.0 y Springboot 3.1.2';
  
}
