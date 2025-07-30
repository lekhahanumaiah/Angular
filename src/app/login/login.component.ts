import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.pattern(/^[A-Z][\w!@#$%^&*()\-_=+{}[\]:;"'<>,.?/\\|`~]*[!@#$%^&*()\-_=+{}[\]:;"'<>,.?/\\|`~]+[\w!@#$%^&*()\-_=+{}[\]:;"'<>,.?/\\|`~]*$/)
        ]
      ]
    });
  }

  handleLogin() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      // Implement actual login logic here
      this.router.navigate(['/home']);
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

  get f() {
    return this.loginForm.controls;
  }
}
