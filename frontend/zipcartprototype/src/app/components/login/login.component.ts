import { provideHttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginResponse } from 'src/app/classes/DTOs/LoginResponseDTO';
import { Datasharing } from 'src/app/services/datasharing/datasharing';
import { ToastServices } from 'src/app/services/toastService/toast-services';
import { IonicModule } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { UserService } from 'src/app/services/springServices/userServices/user-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  standalone: true,
  imports: [IonicModule],
})
export class LoginComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  firstUser: string = '';
  logIn: LoginResponse = {
    userId: '',
    firstName: '',
    email: '',
  };
  constructor(
    private userService: UserService,
    private dataSharing: Datasharing,
    private toast: ToastServices,
  ) {}
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  ngOnInit() {
    this.getAllUsers();
  }

  // temporary implementaion for login with hardcoded user Id

  getAllUsers() {
    this.userService
      .loadAllUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (users) => {
          if (users.length > 0) {
            const firstUser = users[0];
            this.firstUser = firstUser.userId;
            this.loginUser(this.firstUser);
          }
        },
        error: (err) => console.error(err),
      });
  }
  loginUser(userId: string) {
    this.userService
      .logInUser(userId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (result: LoginResponse) => {
          this.logIn = result;
          this.toast.showSuccess(this.logIn.firstName.toString());
          this.shareLogInResponse();
        },
        error: (err) => {
          const message = err?.error?.message || 'Unable to login';
          this.toast.showError(message);
        },
      });
  }

  // sharing login response object
  shareLogInResponse() {
    if (this.logIn) {
      this.dataSharing.exchangeLoginResponse(this.logIn);
    }
  }
}
