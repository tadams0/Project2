import { Component, OnInit } from '@angular/core';
import { CreditRequestService } from './services/credit-request.service';

@Component({
  selector: 'app-creditform',
  templateUrl: './creditform.component.html',
  styleUrls: ['./creditform.component.css']
})
export class CreditFormComponent implements OnInit {

  constructor(private creditService : CreditRequestService ) { }

  ngOnInit() {
  }

}
