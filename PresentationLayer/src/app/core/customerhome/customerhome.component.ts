import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/login/login.service';

@Component({
  selector: 'app-customerhome',
  templateUrl: './customerhome.component.html',
  styleUrls: ['./customerhome.component.css']
})
export class CustomerhomeComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

}
