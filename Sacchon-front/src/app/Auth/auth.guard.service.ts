import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthServiceService } from './auth-service.service';

@Injectable()
export class AuthGuard implements CanActivate{

    constructor(private authService:AuthServiceService,private router:Router){}
    
canActivate(route:ActivatedRouteSnapshot,state:RouterStateSnapshot){
    if( this.authService.isAUth()){
        return true;
    }
    else{
        
        this.router.navigate(['/login']);
       
    }
}
}

