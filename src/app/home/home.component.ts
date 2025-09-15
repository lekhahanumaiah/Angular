import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductService } from '../services/product.service';
import { CartService } from '../services/cart.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  searchForm!: FormGroup;
  filterForm!: FormGroup;
  products: Product[] = [];
  filteredProducts: Product[] = [];
  categories: string[] = ["Electronics", "Clothing", "Books", "Home", "Other"];
  cartCount: number = 0;
  userId: number = 1; // <-- Example user ID; replace with auth value if available

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private productService: ProductService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    // Initialize forms
    this.searchForm = this.fb.group({ search: [''] });
    this.filterForm = this.fb.group({ category: [''], minPrice: [''], maxPrice: [''] });

    this.loadProducts();

    // React to search and filter changes
    this.searchForm.get('search')?.valueChanges.subscribe(() => this.loadProducts());
    this.filterForm.valueChanges.subscribe(() => this.loadProducts());

    // Subscribe to cart count observable
    this.cartService.cartCount$.subscribe(count => {
      this.cartCount = count;
    });

    // Initialize cart count on load
    this.cartService.refreshCartCount(this.userId);
  }

  loadProducts() {
    const search = this.searchForm.value.search;
    const { category, minPrice, maxPrice } = this.filterForm.value;

    this.productService.getProducts(search, category, minPrice, maxPrice).subscribe(data => {
      this.filteredProducts = data;
    });
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
