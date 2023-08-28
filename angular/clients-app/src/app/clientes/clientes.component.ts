import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit{
  clientes: Cliente[] = [];
/*
//primera forma de injectar clase service
  
  private clienteService: ClienteService;
  
  constructor(clienteService: ClienteService){
    this.clienteService = clienteService;
  }*/

  //injeccion de clase service
  constructor(private clientService: ClienteService){}


  ngOnInit(): void {
    this.clientService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );
  };

  delete(cliente: Cliente): void{
    Swal.fire({
      title: '¿Estas seguro?',
      text: `¡No será posible revertir este cambio! ¿Desea borrar el cliente ${cliente.name} ${cliente.lastName}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '¡Si, estoy seguro!'
    }).then((result) => {
      if (result.isConfirmed) {

        this.clientService.delete(cliente.id).subscribe(response=> {
          //con filter flitraos la lista todos los registros que cumplan la condicion de la funcion pasan (son filtrados)
          this.clientes = this.clientes.filter(cli=> cli!==cliente);
          Swal.fire(
              '¡Borrado!',
              'El cliente a sido borrado.',
              'success'
            )
        });
      }
    });
    
  }

}
