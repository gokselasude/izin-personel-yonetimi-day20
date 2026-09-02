import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLeaveRequests } from './admin-leave-requests';

describe('AdminLeaveRequests', () => {
  let component: AdminLeaveRequests;
  let fixture: ComponentFixture<AdminLeaveRequests>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminLeaveRequests],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminLeaveRequests);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
