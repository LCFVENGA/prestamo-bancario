export interface Loan {
  id: number;
  userId: number;
  amount: number;
  termMonths: number;
  status: 'PENDING' | 'APPROVED' | 'REJECTED';
  requestDate: string;
}
