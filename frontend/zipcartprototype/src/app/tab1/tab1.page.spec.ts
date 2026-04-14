import { StartShopping } from 'src/app/classes/DTOs/StartShoppingDTO';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tab1Page } from './tab1.page';
import { StartShoppingResponse } from '../classes/DTOs/StartShoppingResponse';

describe('Tab1Page', () => {
  let component: Tab1Page;
  let fixture: ComponentFixture<Tab1Page>;
  let startShopping: StartShopping;
  let response: StartShoppingResponse;

  beforeEach(async () => {
    fixture = TestBed.createComponent(Tab1Page);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
