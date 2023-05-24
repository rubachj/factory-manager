import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public emailPlaceholder: String = 'e-mail...';
  public passwordPlaceholder: String = 'hasło...';
  public loginValueButton: String = 'Zaloguj się';

  public loginForm: FormGroup;

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null),
      password: new FormControl(null),
    });
  }

  public onSubmit(): void {
    console.log(this.loginForm);
  }
}
