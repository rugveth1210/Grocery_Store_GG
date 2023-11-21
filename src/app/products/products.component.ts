import { Component, OnInit } from '@angular/core';
import { ProdutsService } from '../services/produts.service';
import { IProducts } from '../models/products';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  productsList:any
  

  constructor(private products:ProdutsService) { }

  ngOnInit(): void {
    this.productsList = this.products.getProducts() 
  
  }

}
