import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from '../service/http.service';
import { AuthenticateRequestDto } from '../core/model/auth/AuthenticateRequestDto';
import { AuthenticationResponseDto } from '../core/model/auth/AuthenticationResponseDto';
import { token } from '../core/constants/LocalStorageName';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private authUrl: string = '/auth';

  public emailPlaceholder: String = 'e-mail...';
  public passwordPlaceholder: String = 'hasło...';
  public loginValueButton: String = 'Zaloguj się';

  public loginForm: FormGroup;

  constructor(private http: HttpService) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null),
      password: new FormControl(null),
    });
  }

  public onSubmit(): void {
    this.http
      .post<AuthenticationResponseDto>(
        this.authUrl,
        this.prepareAuthenticateRequestDto()
      )
      .subscribe((s) => {
        localStorage.setItem(token, s.token);
      });
  }

  private prepareAuthenticateRequestDto(): AuthenticateRequestDto {
    return {
      email: this.loginForm.value['email'],
      password: this.loginForm.value['password'],
    };
  }
}
