import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpService } from '../service/http.service';
import { AuthenticateRequestDto } from '../core/model/auth/AuthenticateRequestDto';
import { AuthenticationResponseDto } from '../core/model/auth/AuthenticationResponseDto';
import { token } from '../core/constants/LocalStorageName';
import { AppToastrService } from '../service/app-toastr.service';
import { MessageKind } from '../core/enumerations/MessageKind';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private authUrl: string = '/auth';

  public loginForm: FormGroup;

  constructor(private http: HttpService, private appToastr: AppToastrService) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null),
      password: new FormControl(null),
    });
  }

  public onSubmit(): void {
    const { http, appToastr, authUrl } = this;
    const { SUCCESS, ERROR } = MessageKind;
    http
      .post<AuthenticationResponseDto>(
        authUrl,
        this.prepareAuthenticateRequestDto()
      )
      .subscribe({
        next: (s) => {
          appToastr.showMessage('login.message.success', SUCCESS);
          localStorage.setItem(token, s.token);
        },
        error: () => appToastr.showMessage('login.message.failed', ERROR),
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
