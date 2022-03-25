import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title:string = 'Palabras encadenadas';
  contador:number;
  nombreInsertado: string = null;
  lastWord = null;
  url:string = "http://localhost:8081/palabras/";
  error;
  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.contador = 0;
    
    
    this.http.get(this.url, {responseType:'text'}).subscribe(data => {
      this.lastWord = data;
      console.log(data);
      console.log("how pretty Alejandro is");
   },error => console.log(error));
  }

  incrementarContador():void {
    this.contador++;
  }

  buscarPalabra():void {
    /*if(){
      this.lastWord = this.nombreInsertado;
    }*/
  }

  rendirse():void {

  }

}
