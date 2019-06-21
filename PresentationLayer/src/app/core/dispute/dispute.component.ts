import { Component, OnInit, Input } from '@angular/core';
import { Dispute } from 'src/app/shared/models/dispute';
import { DisputeService } from './services/dispute.service';

@Component({
  selector: 'app-dispute',
  templateUrl: './dispute.component.html',
  styleUrls: ['./dispute.component.css']
})
export class DisputeComponent implements OnInit {
  @Input() public dispute : Dispute;

  constructor(private disputeService : DisputeService) { }

  ngOnInit() {
  }
}
