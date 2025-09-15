import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.pattern(/^[A-Z].*[!@#$%^&*(),.?":{}|<>]+.*$/)
        ]
      ]
    });
  }

  // easy getter for form fields
  get f() {
    return this.loginForm.controls;
  }

  handleLogin() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    const { email, password } = this.loginForm.value;

    this.authService.login(email, password).subscribe({
      next: (res: { token: string }) => {
        // Save JWT token
        localStorage.setItem('token', res.token);
        // Navigate to home page
        this.router.navigate(['/home']);
      },
      error: (err: any) => {
        this.errorMessage = err.error?.message || 'Invalid email or password';
      }
    });
  }
}
