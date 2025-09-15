import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { OrderRequest } from '../model/order.model';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent {
  orderForm: FormGroup;
  paymentMode: string = 'CASH_ON_DELIVERY';  // Default payment mode

  constructor(private fb: FormBuilder, private orderService: OrderService) {
    this.orderForm = this.fb.group({
      fullName: ['', Validators.required],
      customerEmail: ['', [Validators.required, Validators.email]],
      customerAddress: ['', Validators.required],
      items: this.fb.array([this.createItem()])
    });
  }

  get items(): FormArray {
    return this.orderForm.get('items') as FormArray;
  }

  createItem(): FormGroup {
    return this.fb.group({
      productName: ['', Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
      price: [0, [Validators.required, Validators.min(0)]]
    });
  }

  addItem(): void {
    this.items.push(this.createItem());
  }

  removeItem(index: number): void {
    if (this.items.length > 1) {
      this.items.removeAt(index);
    }
  }

  get totalAmount(): number {
    return this.items.controls.reduce((sum, group) => {
      const price = group.get('price')?.value || 0;
      const quantity = group.get('quantity')?.value || 0;
      return sum + (price * quantity);
    }, 0);
  }

  onSubmit(): void {
    if (this.orderForm.valid) {
      const order: OrderRequest = {
        ...this.orderForm.value,
        totalAmount: this.totalAmount,
        paymentMode: this.paymentMode
      };

      this.orderService.placeOrder(order).subscribe({
        next: () => {
          alert('Order placed successfully!');
          this.orderForm.reset();
          while (this.items.length > 1) this.items.removeAt(0);
        },
        error: () => alert('Error placing order')
      });
    } else {
      alert('Form is invalid!');
    }
  }
}
