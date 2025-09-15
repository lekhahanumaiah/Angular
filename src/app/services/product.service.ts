import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {}

 
getProducts(search?: string, category?: string, minPrice?: number, maxPrice?: number): Observable<Product[]> {
  let params = new HttpParams();
  if (search) params = params.set('search', search);
  if (category) params = params.set('category', category);
  if (minPrice != null) params = params.set('minPrice', minPrice.toString());
  if (maxPrice != null) params = params.set('maxPrice', maxPrice.toString());

  return this.http.get<Product[]>(this.baseUrl, { params });
}
getProductById(id: number): Observable<any> {
  return this.http.get(`${this.baseUrl}/${id}`);
}
}