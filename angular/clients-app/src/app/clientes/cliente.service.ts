import { Injectable } from '@angular/core';
import { Cliente } from './cliente';
import { Observable,of,throwError } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import { map, catchError } from 'rxjs';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint: string = 'http://localhost:8080/api/clients';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private router: Router) { }

  getClientes(): Observable<Cliente[]>{
    //con la map realizamos una conversion explicita de la respuesta al tipo deseado en el frontend
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as Cliente[])
    )
  }
  createCliente(cliente: Cliente): Observable<any>{
    return this.http.post<any>(this.urlEndPoint, cliente, {headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire( e.error.message,e.error.error, 'error');

        return throwError(e);
      })
    )
  }
  getCliente(id: any): Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        //message hace referencia a la key del map de resultados que envia el servidor en respuesta mediante ResponseEntity
        console.error(e.error.message);
        Swal.fire('Error al editar',e.error.message,'error');
        this.router.navigate(['/clientes']);

        return throwError(e);
      })
    )
  }
  
  /*podria dejarse como el metdo createCliente pero vamos a hacer un casting explicito para mostrar otro ejemplo como comentaba en el metodo getClientes
  personalmente prefiero utilizar los mensajes del back como en createCliente al implementarlo despues en los respectivos componentes pero depediendo la situacion
  si hay que utiizar dentro del frontend el client recibido como clase Cliente sera necesario hacer este casting de objetos y prescincdir de los mensajes del servidor
  fuera del ambito de este metodo y sus capturas de errores */
  update(cliente: Cliente): Observable<Cliente>{
    return this.http.put(`${this.urlEndPoint}/${cliente.id}`,cliente, {headers: this.httpHeaders}).pipe(
      map((response: any) => response.client as Cliente),
      catchError(e => {
        console.error(e.error.message);
        Swal.fire( e.error.message,e.error.error, 'error');

        return throwError(e);
      })
    )
  }
  delete(id: any):Observable<any>{
    return this.http.delete<any>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire( e.error.message,e.error.error, 'error');

        return throwError(e);
      })
    )
  }
}
