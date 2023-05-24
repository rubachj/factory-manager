import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  public emailPlaceholder: String = 'e-mail...';
  public passwordPlaceholder: String = 'hasło...';
  public loginValueButton: String = 'Zaloguj się';
}
