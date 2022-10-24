import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Fornecedor, Loja } from 'src/app/app.module';
import { FornecedorService } from 'src/app/services/fornecedor.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-fornecedor-screen',
  templateUrl: './fornecedor-screen.component.html',
  styleUrls: ['./fornecedor-screen.component.css']
})
export class FornecedorScreenComponent implements OnInit {
  @Input() loja:Loja | null = null;
  
  fornecedor: Fornecedor|null = null;
  editando: boolean=false;

  fornecedores: Fornecedor[] = [];
  constructor(
    private fornecedorService: FornecedorService,
    private lojaService: LojaService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      this.fornecedorService.getAll(this.loja!.razao_social).pipe().subscribe(fornecedores => {
        this.fornecedores = fornecedores;
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      });
    },
    erro => {
      if(erro.status == 400) {
        console.log(erro);
      }
    }));
   }

  ngOnInit(): void {
    if(this.loja!=null){
    this.fornecedorService.getAll(this.loja!.razao_social).pipe().subscribe(fornecedores => {
      this.fornecedores = fornecedores;
    })
  };
}
delete(fornecedor:Fornecedor){
  this.fornecedorService.delete(this.loja?.razao_social!,fornecedor.id);
  this.fornecedores = this.fornecedores.filter(forn => forn!=fornecedor);
}
edit(fornecedor:Fornecedor){
  this.editando=true;
  this.fornecedor=fornecedor;
}
editado(fornecedor:Fornecedor){
  this.fornecedor= fornecedor;
  this.editando = false;
  this.fornecedores = this.fornecedores.map(fornecedorL=> {
    if(fornecedorL.id==fornecedor.id){
      return fornecedor;
    }
    return fornecedorL;
  })
}
}
