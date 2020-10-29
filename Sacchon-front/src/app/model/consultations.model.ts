import { Doctor } from './doctor.model';
import { Patient } from './patient.model';

export interface Consultation {
    consult:string;
    date:number;
    doctorId: number;
    patientId: number;
}
