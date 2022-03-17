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
  lastWord:string = null;
  constructor() { }

  ngOnInit() {
    this.contador = 0;
    //this.lastWord = llamarAlBack()
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
