import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CartComponent } from './cart.component';
import { CartService } from '../services/cart.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let mockCartService: jasmine.SpyObj<CartService>;

  const mockCartItems = [
    { productId: 1, name: 'Product A', price: 100, quantity: 2, imageUrl: 'image-a.jpg' },
    { productId: 2, name: 'Product B', price: 150, quantity: 1, imageUrl: 'image-b.jpg' }
  ];

  beforeEach(async () => {
    const cartServiceSpy = jasmine.createSpyObj('CartService', ['getCartItems', 'removeItem', 'updateItemQuantity', 'refreshCartCount']);

    await TestBed.configureTestingModule({
      declarations: [CartComponent],
      imports: [HttpClientTestingModule],
      providers: [{ provide: CartService, useValue: cartServiceSpy }]
    }).compileComponents();

    mockCartService = TestBed.inject(CartService) as jasmine.SpyObj<CartService>;
    mockCartService.getCartItems.and.returnValue(of(mockCartItems));
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should load cart items on init', () => {
    expect(component.cartItems.length).toBe(2);
    expect(component.totalAmount).toBe(350); // 100*2 + 150*1
  });

  it('should calculate total items correctly', () => {
    const total = component.getTotalItems();
    expect(total).toBe(3); // 2 + 1
  });

  it('should remove item from cart', () => {
    mockCartService.removeItem.and.returnValue(of({}));

    const itemToRemove = mockCartItems[0];
    component.removeItem(itemToRemove);

    expect(mockCartService.removeItem).toHaveBeenCalledWith(itemToRemove.productId);
  });

  it('should update quantity correctly', () => {
    mockCartService.updateItemQuantity.and.returnValue(of({}));
    const itemToUpdate = mockCartItems[1];
    component.updateQuantity(itemToUpdate, 3);

    expect(mockCartService.updateItemQuantity).toHaveBeenCalledWith(itemToUpdate.productId, 3);
  });
});
