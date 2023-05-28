import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from '../service/http.service';
import { AuthenticateRequestDto } from '../core/model/auth/AuthenticateRequestDto';
import { AuthenticationResponseDto } from '../core/model/auth/AuthenticationResponseDto';
import { token } from '../core/constants/LocalStorageName';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private authUrl: string = '/auth';
  private successLogin: string = 'Witaj w serwisie Factory Manager.';
  private failedLogin: string = 'Podano nieprawidłowy login lub hasło!';

  public emailPlaceholder: String = 'e-mail...';
  public passwordPlaceholder: String = 'hasło...';
  public loginValueButton: String = 'Zaloguj się';

  public loginForm: FormGroup;

  constructor(private http: HttpService, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null),
      password: new FormControl(null),
    });
  }

  public onSubmit(): void {
    const { http, successLogin, failedLogin, toastr, authUrl } = this;

    http
      .post<AuthenticationResponseDto>(
        authUrl,
        this.prepareAuthenticateRequestDto()
      )
      .subscribe({
        next: (s) => {
          toastr.success(successLogin);
          localStorage.setItem(token, s.token);
        },
        error: (er) => toastr.error(failedLogin),
      });
  }

  private prepareAuthenticateRequestDto(): AuthenticateRequestDto {
    const { email, password } = this.loginForm.value;
    return {
      email: email,
      password: password,
    };
  }
}
