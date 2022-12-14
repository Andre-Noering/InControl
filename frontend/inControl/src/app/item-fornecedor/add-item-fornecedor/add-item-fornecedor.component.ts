import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fornecedor, Item, ItemFornecedor, Loja } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FornecedorService } from 'src/app/services/fornecedor.service';
import { ItemService } from 'src/app/services/item.service';
import { ItemFornecedorService } from 'src/app/services/itens-fornecedor.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-add-item-fornecedor',
  templateUrl: './add-item-fornecedor.component.html',
  styleUrls: ['./add-item-fornecedor.component.css']
})
export class AddItemFornecedorComponent implements OnInit {
  @Input() itemFornecedor: ItemFornecedor|null = null;
  @Output() editado= new EventEmitter<ItemFornecedor>();

  escolhendoItem : boolean = false;
  itemEscolhido: boolean = false;
  fornecedor: Fornecedor | null = null;
  escolhendoFornecedor : boolean = false;
  fornecedorEscolhido: boolean = false;
  item: Item | null = null;
  loja:Loja|null = null;
  fornecedores:Fornecedor[] = [];
  itens: Item[] = []
  formItemFornecedor = this.formBuilder.group({
    id:[0],
    valorCompra:[0, Validators.required],
    idFornecedor:[0, Validators.required],
    idItem:[0,Validators.required],
    nome_item:['',Validators.required],
    nome_fornecedor:['',Validators.required],
  });

  constructor(private http: HttpClient,
    private formBuilder : FormBuilder,
    private fornecedorService:FornecedorService,
    private itemService : ItemService,
    private itemFornService: ItemFornecedorService,
    private authenticationService: AuthenticationService,
    private lojaService: LojaService,
    private route: ActivatedRoute,
    private router:Router) {
   } 
  ngOnInit(): void {
    if(this.itemFornecedor!=null){
      this.formItemFornecedor.patchValue(this.itemFornecedor);
      this.itemEscolhido = true;
      this.fornecedorEscolhido = true;
    }
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      console.log(this.loja);
      this.itemService.getAll(this.loja!.razao_social).pipe().subscribe(itens => this.itens=itens);
      this.fornecedorService.getAll(this.loja!.razao_social).pipe().subscribe(fornecedores => this.fornecedores = fornecedores);
      }));
      
  }
    addItemForn(){
      if(this.itemFornecedor==null){
      this.itemFornService.add(this.formItemFornecedor.value as ItemFornecedor);
        this.router.navigate([`/lojas/${this.loja!.razao_social}/itensFornecedor`]);
      } else {
        this.itemFornService.edit(this.formItemFornecedor.value as ItemFornecedor);
        this.editado.emit(this.formItemFornecedor.value as ItemFornecedor);
      }
      };

      setItem(item:Item){
        this.item=item;
        this.formItemFornecedor.get('idItem')?.patchValue(item.id);
        if(this.itemFornecedor!=null){
          this.itemFornecedor!.nome_item = item.nome;
          this.formItemFornecedor.get('nome_item')?.patchValue(this.itemFornecedor.nome_item);
          this.formItemFornecedor.get('idItem')?.patchValue(item.id);
        }
        this.itemEscolhido=true
      }
      setFornecedor(fornecedor:Fornecedor){
        this.fornecedor=fornecedor;
        this.formItemFornecedor.get('idFornecedor')?.patchValue(fornecedor.id);
        if(this.itemFornecedor!=null){
          this.itemFornecedor!.nome_fornecedor = fornecedor.razao_social;
          this.formItemFornecedor.get('nome_fornecedor')?.patchValue(this.itemFornecedor.nome_fornecedor);
          this.formItemFornecedor.get('idFornecedor')?.patchValue(fornecedor.id);

        }
        this.fornecedorEscolhido=true
      }
    }

