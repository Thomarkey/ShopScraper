import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Item } from '../models/item.model';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private backendApiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }


  lookUp(searchTerm: string): Observable<Item[]> {
    const url = `${this.backendApiUrl}/${searchTerm}`;
    return this.http.get<Item[]>(url);
  }

}
