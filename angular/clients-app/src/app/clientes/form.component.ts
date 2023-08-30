import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router,ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  protected cliente: Cliente = new Cliente();
  protected titulo: string = "Crear Cliente";

  constructor(private clientService: ClienteService, private router: Router,private activate: ActivatedRoute){}

  ngOnInit(): void {
    this.loadCliente();
  }
  public create(): void{
    this.clientService.createCliente(this.cliente).subscribe(
        response =>{ 
          this.router.navigate(['/clientes']);
          Swal.fire(`${response.message}`, `Cliente ${response.client.name} ${response.client.lastName} creado con exito`, 'success');
      }
    );
  }
  public loadCliente(): void{
    this.activate.params.subscribe(params=>{
      let id = params['id'];
      if(id) this.clientService.getCliente(id).subscribe(cliente=> this.cliente = cliente);
    })
  }
  update(): void{
    this.clientService.update(this.cliente).subscribe(response => {
      this.router.navigate(['/clientes']);
      Swal.fire('Cliente Actualizado', 'cliente actualizado con éxito', 'success')
    })
  }
}
