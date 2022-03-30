import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title:string               = 'Palabras encadenadas';
  contador:number;
  lastWord:string            = null;
  insertedWord:string        = null;
  url:string                 = "http://localhost:8081/palabras";
  urlBuscar:string           = "http://localhost:8081/palabras/buscar?buscarPalabra=";
  existePalabra:boolean      = false;
  palabraNoExiste:boolean    = false;
  silabasNoCoinciden:boolean = false;
  puntoGanado:boolean        = false;
  palabraVacia:boolean       = false;
  silabaPrimeraPalabra:String;
  silabaUltimaPalabra:String;
  error;
  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.contador        = 0;
    this.palabraNoExiste = false;
    this.puntoGanado     = false;
    this.palabraVacia    = false;
    this.insertedWord = "";
    this.http.get(this.url, {responseType:'text'}).subscribe(data => {
      this.lastWord = data;
   },error => console.log(error));
    
  }

  incrementarContador():void {
    this.contador++;
  }

    async getPalabra( url:string):Promise<Boolean>{
      return await new Promise((resolve, rejected) => {
        this.http.get(url, {responseType:'text'}).subscribe(data => {
            resolve(data == "true");
        },error => rejected(false)); 
      })
    }

    async getSilabaPalabra( url:string):Promise<String>{
      return await new Promise((resolve) => {
        this.http.get(url, {responseType:'text'}).subscribe(data => {
            resolve(data);
        }); 
      })
    }

   async siguienteTurno():Promise<void> {
    let palabraExiste:Boolean = false;
    
    
    console.log("Encadena la siguiente palabra: " + this.lastWord);
    console.log("Inserte su palabra:");

    //Leer palabra desde el frontend

    //Peticion get para saber si está
    palabraExiste = await this.getPalabra(this.urlBuscar+this.insertedWord);
    if(this.insertedWord == ""){
      this.palabraVacia = true;
      await this.delay(3);
      this.palabraVacia = false;
    }else{
      if(palabraExiste){
        //Obtengo la ultima silaba de la palabra de referencia
        this.silabaPrimeraPalabra = await this.getSilabaPalabra(this.url+"/silabaPrimeraPalabra");
  
        //Obtengo la primera silaba de la palabra insertada
        this.silabaUltimaPalabra = await this.getSilabaPalabra(this.url+"/silabaUltimaPalabra");
        if(this.silabaPrimeraPalabra == this.silabaUltimaPalabra){
          this.lastWord = this.insertedWord;
          this.contador++;
          this.insertedWord = "";
          this.puntoGanado = true;
          await this.delay(3);
          this.puntoGanado = false;
          
  
        }else{
          //Las silabas no coinciden
          this.silabasNoCoinciden = true;
          await this.delay(4.5);
          this.silabasNoCoinciden = false;
          //console.log("La sílaba "+this.silabaPrimeraPalabra+" de "+this.lastWord+ " no coincide con la sílaba "+this.silabaUltimaPalabra+" de la palabra "+this.insertedWord+".");
        }
      }else{
        this.palabraNoExiste = true;
        await this.delay(3);
        this.palabraNoExiste = false;
        console.log(this.insertedWord);
        console.log("La palabra no existe, inserte otra palabra.");
      }
    }
    
       
      
  }

  delay(n:number){
    return new Promise(function(resolve){
        setTimeout(resolve,n*1000);
    });
}

}
