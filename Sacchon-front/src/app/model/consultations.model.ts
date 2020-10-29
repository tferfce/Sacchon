import { Doctor } from './doctor.model';
import { Patient } from './patient.model';

export interface Consultation {
    consult:string;
    doctorId: number;
    patientId: number;
}
