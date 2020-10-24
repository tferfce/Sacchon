import { Injectable } from '@angular/core';
import { Patient } from './model/patient.model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
public scope:Patient
  constructor() { }

  public getScope():Patient{
    return this.scope;
  }

  public setScope(scope:Patient):void{
    this.scope=scope;
  }

  public setIdScope(scopeId:number):void{
    this.scope.id=scopeId;
  }
}
