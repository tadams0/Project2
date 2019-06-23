import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Employee } from 'src/app/shared/models/employee';
import { UserService } from '../login/login.service';
import { StatServiceService } from 'src/app/shared/services/stat-service.service';
import { PieChartComponent } from '../pie-chart/pie-chart.component';
import { StatPayload } from 'src/app/shared/models/statpayload';

@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit, AfterViewInit {
  public employee :Employee;
  @ViewChild(PieChartComponent, {static: false}) creditLinePieChart: PieChartComponent;
  private needsRefresh : boolean = false;

  constructor(private userService : UserService, private statService : StatServiceService) 
  { 
    
  }

  ngOnInit() {
    this.employee = this.userService.getEmployee();
    const payload = this.statService.getStatPayloadCache();
    console.log("payload:");
    console.log(payload);
    if (!payload)
    {
      this.statService.getStatPayload().subscribe((response)=>{
        this.statService.cacheStatPayload(response);
        this.createPieChart(response);
      });
    }
    else
    {
      this.needsRefresh = true;
    }
  }
  ngAfterViewInit() {
    if (this.needsRefresh)
    {
      const payload = this.statService.getStatPayloadCache();
      this.createPieChart(payload);
      this.needsRefresh = false;
    }
  }

  createPieChart(payload : StatPayload)
  {
    this.creditLinePieChart.resetChart();
    this.creditLinePieChart.addDataSet(payload.approvedCreditLineRequests,
      'Approved Requests', '#38ea61', '#2dff5e');
    this.creditLinePieChart.addDataSet(payload.rejectedCreditLineRequests,
      'Rejected Requests', '#B22222', '#DC143C');
    this.creditLinePieChart.addDataSet(payload.rejectedCreditLineRequestsAuto,
      'Auto Rejected Requests', '#CD5C5C', '#F08080');
  }
}
