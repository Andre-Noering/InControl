import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Item, Loja } from 'src/app/app.module';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit, OnChanges {
  @Input() loja:Loja | null = null;

  formItem = this.formBuilder.group({
    nome:['', Validators.required],
    valor:[0, Validators.required],
    qtdeEstoque:[0, Validators.required],
    qtdeAlertaEstoque:[0, Validators.required],
    idLoja:[0, Validators.required]
  });

  constructor(
    private formBuilder:FormBuilder,
    private itemService:ItemService
  ) { 
    
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.loja = changes['loja'].currentValue;
    if(this.loja!=null){
      this.formItem.get('idLoja')?.patchValue(this.loja!.id);
    }
  }

  ngOnInit(): void {
    if(this.loja!=null){
      this.formItem.get('idLoja')?.patchValue(this.loja!.id);
    }
  }
  addItem(){
    this.itemService.add(this.formItem.value as Item);
  }
}
