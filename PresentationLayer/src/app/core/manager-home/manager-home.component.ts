import { Component, OnInit, ViewChild, AfterViewInit, QueryList, ViewChildren } from '@angular/core';
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
  @ViewChildren(PieChartComponent) pieCharts :QueryList<PieChartComponent> ;
  private needsRefresh : boolean = false;
  private creditLinePieChart : PieChartComponent;
  private disputePieChart : PieChartComponent;

  constructor(private userService : UserService, private statService : StatServiceService) 
  { 
    
  }

  ngOnInit() {
    this.employee = this.userService.getEmployee();

    this.refreshPieCharts();
  }

  ngAfterViewInit() {
    this.creditLinePieChart = this.pieCharts.first;
    this.disputePieChart = this.pieCharts.last;

    if (this.needsRefresh)
    {
      const payload = this.statService.getStatPayloadCache();
      this.createPieChart(payload);
      this.needsRefresh = false;
    }
  }

  onRefresh() {
    this.statService.clearCache();
    this.refreshPieCharts();
  }

  refreshPieCharts() {
    const payload = this.statService.getStatPayloadCache();

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

  createPieChart(payload : StatPayload)
  {
    this.creditLinePieChart.resetChart();
    this.creditLinePieChart.addDataSet(payload.approvedCreditLineRequests,
      'Approved Requests', '#38ea61', '#2dff5e');
    this.creditLinePieChart.addDataSet(payload.rejectedCreditLineRequests,
      'Rejected Requests', '#B22222', '#DC143C');
    this.creditLinePieChart.addDataSet(payload.rejectedCreditLineRequestsAuto,
      'Auto Rejected Requests', '#CD5C5C', '#F08080');
      
    this.disputePieChart.resetChart();
    this.disputePieChart.addDataSet(payload.rejectedDisputes,
      'Rejected Disputes', '#38ea61', '#2dff5e');
    this.disputePieChart.addDataSet(payload.approvedDisputes,
      'Approved Disputes', '#B22222', '#DC143C');
  }
}
