import { Component } from '@angular/core';
import { ApiService } from './services/api.service';
import { Item } from './models/item.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'shop scraper WIP';
  inputValue: string = '';
  selectedStore: string = '';
  itemsList: Item[] = [];

  constructor(private apiService: ApiService) {
  }

  // submitText(inputValue: string) {
  //   console.log(inputValue);
  //   this.inputValue = inputValue;

  //   this.apiService.lookUp(inputValue).subscribe((response: Item[]) => {
  //     if (Array.isArray(response)) {
  //       this.itemsList = response;
  //     } else {
  //       // Handle the case when the response is a single string
  //       // You can modify this part based on your specific requirement
  //       this.itemsList = [response];
  //     }
  //   });

  // }

  submitText(inputValue: string, selectedStore: string) {
    console.log(inputValue);
    this.inputValue = inputValue;

    console.log(selectedStore);
    this.selectedStore = selectedStore;

    this.apiService.lookUpWithStore(inputValue, selectedStore).subscribe((response: Item[]) => {
      if (Array.isArray(response)) {
        this.itemsList = response;
      } else {
        // Handle the case when the response is a single string
        // You can modify this part based on your specific requirement
        this.itemsList = [response];
      }
    });
  }

  onCheckboxChange(event: Event, store: string) {
    const checkbox = event.target as HTMLInputElement;
    this.selectedStore = checkbox.checked ? store : '';
  }


}
