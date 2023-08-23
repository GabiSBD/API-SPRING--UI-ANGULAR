import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  protected cliente: Cliente = new Cliente();
  protected titulo: string = "Crear Cliente";

  constructor(private clientService: ClienteService, private router: Router){}

  ngOnInit(): void {
    
  }
  public create(): void{
    this.clientService.createClient(this.cliente).subscribe(
        response => this.router.navigate(['/clientes'])
    );
  }
}
