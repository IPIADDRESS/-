import {Component} from "@angular/core";
import {Observable, of} from 'rxjs';
import {AsyncPipe} from "@angular/common";

interface Person {
  id:number;
  name: string;
  phone: string
}

@Component({
   selector: 'app-test',
   standalone: true,
   template: `
      @for (p of <error descr="Type Person must have a [Symbol.iterator]() method that returns an iterator.">person</error>; track p.id) {
          {{ p.<weak_warning descr="Unresolved variable phone">phone</weak_warning> + p.<weak_warning descr="Unresolved variable foo">foo</weak_warning> }}
      }
      @for (p of persons; track p.id) {
          {{ p.phone + p.<error descr="Unresolved variable foo">foo</error> }}
      }
      @for (p of personsAny; track p.id) {
          {{ p.<weak_warning descr="Unresolved variable phone">phone</weak_warning> + p.<weak_warning descr="Unresolved variable foo">foo</weak_warning> }}
      }
      @for (p of personsOptional; track p.id) {
          {{ p.phone + p.<error descr="Unresolved variable foo">foo</error> }}
      }
      @for (p of (persons$ | async); track p.id) {
          {{ p.phone + p.<error descr="Unresolved variable foo">foo</error> }}
      }
    `,
   imports: [AsyncPipe]
 })
export class TestComponent {
  person!: Person
  persons!: Person[]
  personsAny: any
  personsOptional?: Person[]
  persons$: Observable<Person[]> = of(this.persons);
}
