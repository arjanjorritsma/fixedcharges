export class Category {
  id: number;
  description: string;
  dayOfDebit: number;

  constructor(id: number, description: string, dayOfDebit: number) {
    this.id = id;
    this.description = description;
    this.dayOfDebit = dayOfDebit;
  }
}
