// src/app/components/product-detail/product-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product: any;
  quantity: number = 1;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.productService.getProductById(+id).subscribe({
        next: (data) => this.product = data,
        error: (err) => console.error('Error loading product', err)
      });
    }
  }

  addToCart(): void {
  if (!this.product) return;

  const userId = 1; // 🔹 Replace with logged-in user later
  this.cartService.addToCart(userId, this.product.id, this.quantity).subscribe({
    next: () => {
      alert('Product added to cart successfully!');
    },
    error: (err) => {
      alert('Failed to add to cart');
      console.error('Error adding to cart:', err);
    }
  });
}


  goToCart() {
    this.router.navigate(['/cart']);
  }
}
