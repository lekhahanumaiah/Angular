import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Router } from '@angular/router';

interface CartItemDTO {
  cartId: number;
  productId: number;
  productName: string;
  productPrice: number;
  productImageUrl: string;
  quantity: number;
  userId: number;
}

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItemDTO[] = [];
  totalAmount: number = 0;
  userId: number = 1; // Replace with real user ID if available

  constructor(private cartService: CartService, private router: Router) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartService.getCartItems(this.userId).subscribe(items => {
      this.cartItems = items || [];
      this.calculateTotal();
    });
  }

  calculateTotal(): void {
    this.totalAmount = this.cartItems.reduce((sum, item) => {
      return sum + (item.productPrice * item.quantity);
    }, 0);
  }

  getTotalItems(): number {
    return this.cartItems.reduce((sum, item) => sum + item.quantity, 0);
  }

  removeItem(item: CartItemDTO): void {
    this.cartService.removeFromCart(item.cartId, this.userId).subscribe(() => {
      this.cartItems = this.cartItems.filter(ci => ci.cartId !== item.cartId);
      this.calculateTotal();
      this.cartService.refreshCartCount(this.userId);
    });
  }

  proceedToCheckout(): void {
    this.router.navigate(['/place-order']);
  }
}
