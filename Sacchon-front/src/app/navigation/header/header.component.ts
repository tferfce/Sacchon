import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthServiceService } from 'src/app/Auth/auth-service.service';
import { StorageService } from 'src/app/storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  authSubscription:Subscription;
  isAuth=this.storageService.getIsAuth();
  constructor(private authService:AuthServiceService,private storageService:StorageService) { }

  ngOnInit(): void {
    
    this.authSubscription= this.authService.authChange.subscribe(authStatus =>{
     
      this.storageService.setisAuth(authStatus);
        this.isAuth=this.storageService.getIsAuth();
    });
  }

  ngOnDestroy(){
    this.authSubscription.unsubscribe();
  }

  onLogout(){
    this.authService.logout();
  }

}
