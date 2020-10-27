import { Injectable } from '@angular/core';
import { Patient } from './model/patient.model';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
public scope:Patient
public scopeUser:User
  constructor() { }

  public getScope():Patient{
    return this.scope;
  }

  public setScope(scope:Patient):void{
    this.scope=scope;
  }

  public getScopeUser():User{
    return this.scopeUser;
  }

  public setScopeUser(scope:User):void{
    this.scopeUser=scope;
  }

  public setIdScope(scopeId:number):void{
    this.scope.id=scopeId;
  }


}
