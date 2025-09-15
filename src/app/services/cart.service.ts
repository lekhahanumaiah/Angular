import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap, map } from 'rxjs/operators';

export interface CartItemDTO {
  cartId: number;
  productId: number;
  productName: string;
  productPrice: number;
  productImageUrl: string;
  quantity: number;
  userId: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8080/api/cart';

  private cartCountSubject = new BehaviorSubject<number>(0);
  cartCount$ = this.cartCountSubject.asObservable();

  constructor(private http: HttpClient) {}

  // Get all cart items for a user with mapping to CartItemDTO
  getCartItems(userId: number): Observable<CartItemDTO[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${userId}`).pipe(
      map(items => items.map(item => ({
        cartId: item.cartId || item.id,              // fallback if your backend uses 'id' or 'cartId'
        productId: item.productId,
        productName: item.productName || item.name, // fallback if backend uses 'name'
        productPrice: item.productPrice || item.price,
        productImageUrl: item.productImageUrl || item.imageUrl,
        quantity: item.quantity,
        userId: item.userId
      }))),
      tap(items => this.cartCountSubject.next(items.length))
    );
  }

  addToCart(userId: number, productId: number, quantity: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${userId}/add`, null, {
      params: {
        productId: productId.toString(),
        quantity: quantity.toString()
      }
    }).pipe(
      tap(() => this.refreshCartCount(userId))
    );
  }

  removeFromCart(cartId: number, userId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cartId}`).pipe(
      tap(() => this.refreshCartCount(userId))
    );
  }

  clearCart(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${userId}/clear`).pipe(
      tap(() => this.cartCountSubject.next(0))
    );
  }

  refreshCartCount(userId: number) {
    this.getCartItems(userId).subscribe();
  }
}
