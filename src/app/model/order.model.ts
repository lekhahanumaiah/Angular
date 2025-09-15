export interface OrderItem {
  productName: string;
  quantity: number;
  price: number;
}

export interface OrderRequest {
  fullName: string;
  customerEmail: string;
  customerAddress: string;
  totalAmount: number;
  items: OrderItem[];
}
