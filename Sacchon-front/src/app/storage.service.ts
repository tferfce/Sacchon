import { Injectable } from '@angular/core';
import { Patient } from './model/patient.model';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
public scope:Patient
public scopeUser:User
public isAuth:Boolean
  constructor() { }

  public getScope():Patient{
    return this.scope;
  }

  public setScope(scope:Patient):void{
    this.scope=scope;
  }

  public getScopeUser():User{
     //this.scopeUser;
     return JSON.parse(localStorage.getItem('user'))
  }

  public setScopeUser(scope:User):void{
    this.scopeUser=scope;
    localStorage.setItem('user', JSON.stringify(scope));
  }

  // public setIdScope(scopeId:number):void{
  //   this.scope.id=scopeId;
  // }

  public deleteUser():void{
    localStorage.removeItem('user');
  }
  public setisAuth(isAuth:boolean):void{
    this.isAuth=isAuth;
    localStorage.setItem('isAuth', JSON.stringify(isAuth));
  }
  public getIsAuth():boolean{
    //this.scopeUser;
    return JSON.parse(localStorage.getItem('isAuth'))
 }
 


}
