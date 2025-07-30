// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable } from 'rxjs';

// const AUTH_API = 'http://localhost:8080/api/auth/';

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   constructor(private http: HttpClient) { }

//   login(username: string, password: string): Observable<any> {
//     return this.http.post(AUTH_API + 'signin', {
//       username,
//       password
//     }, httpOptions);
//   }

//   register(username: string, email: string, password: string): Observable<any> {
//     return this.http.post(AUTH_API + 'signup', {
//       username,
//       email,
//       password
//     }, httpOptions);
//   }
// }


// auth.service.ts (mock version)
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor() { }

  login(username: string, password: string): Observable<any> {
    if (username === 'test' && password === 'test123') {
      return of({ message: 'Login successful!' });
    } else {
      return throwError(() => ({ error: { message: 'Invalid credentials' } }));
    }
  }

  register(username: string, email: string, password: string): Observable<any> {
    // Simulate successful registration
    return of({ message: 'User registered successfully!' });
  }
}
