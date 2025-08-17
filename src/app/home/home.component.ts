import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductService } from '../services/product.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  searchForm!: FormGroup;
  products: Product[] = [];
  filteredProducts: Product[] = [];

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    // initialize reactive form
    this.searchForm = this.fb.group({
      search: ['']
    });

    // fetch products from backend
    this.productService.getAllProducts().subscribe((data) => {
      this.products = data;
      this.filteredProducts = data;
    });

    // listen to search changes
    this.searchForm.get('search')?.valueChanges.subscribe((value) => {
      this.filterProducts(value);
    });
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  filterProducts(searchText: string) {
    if (!searchText) {
      this.filteredProducts = this.products;
      return;
    }
    const lower = searchText.toLowerCase();
    this.filteredProducts = this.products.filter((p) =>
      p.name.toLowerCase().includes(lower)
    );
  }
}
