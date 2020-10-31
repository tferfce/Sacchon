import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthServiceService } from 'src/app/Auth/auth-service.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  authSubscription:Subscription;
  isAuth=false;
  constructor(private authService:AuthServiceService) { }

  ngOnInit(): void {
    this.authSubscription= this.authService.authChange.subscribe(authStatus =>{
      this.isAuth=authStatus;
    });
  }

  ngOnDestroy(){
    this.authSubscription.unsubscribe();
  }

  onLogout(){
    this.authService.logout();
  }

}
