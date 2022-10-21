import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loja } from 'src/app/app.module';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-item-loja',
  templateUrl: './item-loja.component.html',
  styleUrls: ['./item-loja.component.css']
})
export class ItemLojaComponent implements OnInit {
  loja: Loja | null=null;
  @Output() lojaNull = new EventEmitter<null>();
  @Output() itens = new EventEmitter<boolean>();
  @Output() funcionarios = new EventEmitter<boolean>();
  
  constructor(private lojaService: LojaService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      });
    })
  }
  sair(){
    this.lojaNull.emit(null);
  }
  setItens(valor:boolean){
    this.itens.emit(valor);
  }
  setFuncionarios(valor:boolean){
    this.funcionarios.emit(valor);
  }
 
}
