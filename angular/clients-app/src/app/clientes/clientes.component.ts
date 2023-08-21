import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';


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
  constructor(private clienteService: ClienteService){}


  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );
  }
;

}
