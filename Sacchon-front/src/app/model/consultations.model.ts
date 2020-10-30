export interface Consultation {
    id:number;
    consultation:string;
    consult?:string;
    date:number;
    doctor?: {id: number};
    patient?: {id: number};
}
