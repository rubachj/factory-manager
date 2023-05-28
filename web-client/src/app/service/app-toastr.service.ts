import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ToastrService } from 'ngx-toastr';
import { MessageKind } from '../core/enumerations/MessageKind';

@Injectable({
  providedIn: 'root',
})
export class AppToastrService {
  constructor(
    private translate: TranslateService,
    private toastr: ToastrService
  ) {}

  public showMessage(message: string, messageKind: MessageKind): void {
    const { translate } = this;
    translate.get(message).subscribe({
      next: (translateResult) =>
        this.createMessage(translateResult, messageKind),
    });
  }

  private createMessage(
    translateResult: string,
    messageKind: MessageKind
  ): void {
    const { toastr } = this;
    const { SUCCESS, ERROR, INFO } = MessageKind;
    switch (messageKind) {
      case SUCCESS:
        toastr.success(translateResult);
        break;
      case ERROR:
        toastr.error(translateResult);
        break;
      case INFO:
        toastr.info(translateResult);
        break;
    }
  }
}
