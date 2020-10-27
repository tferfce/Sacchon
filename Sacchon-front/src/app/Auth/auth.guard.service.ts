import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { User } from '../model/user.model';
import { StorageService } from '../storage.service';
import { AuthServiceService } from './auth-service.service';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class AuthGuard implements CanActivate{

    constructor(private storage:StorageService,private authService:AuthServiceService,private router:Router){}
    user:User=this.storage.getScopeUser();
   
    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> 
    {
      return this.authService.login(this.user).pipe(map((response) => {
      
          if (response.role) {
            this.router.navigate(['/patientData']);
            console.log(response.role);
              return true;
          }
          this.router.navigate(['/login']);
         // console.log(this.user);
          return false
      }), catchError((error) => {
          this.router.navigate(['/login']);
          //console.log(this.user);
          return of(false);
      }));
  }
}

