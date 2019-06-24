import { Component } from '@angular/core';

@Component({
  selector: 'pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss'],
})
export class PieChartComponent {
  public chartType: string = 'pie';

  public chartDatasets: Array<any> = [
    { data: [300, 50, 100, 40, 120], label: 'My First dataset' }
  ];

  public chartLabels: Array<any> = ['Red', 'Green', 'Yellow', 'Grey', 'Dark Grey'];

  public chartColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774'],
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };

  public resetChart()
  {
    this.chartDatasets = [{
      data: [], label: 'Data'
    }];

    this.chartLabels = [];
    this.chartColors = [];
    this.chartColors.push({
      backgroundColor: [],
      hoverBackgroundColor: [],
      borderWidth: 2,
    });
  }

  public addDataSet(data : number, label : string, 
    backColor : string, hoverColor : string)
  {
    this.chartDatasets[0]["data"].push(data);
    this.chartLabels.push(label);
    this.chartColors[0]["backgroundColor"].push(backColor);
    this.chartColors[0]["hoverBackgroundColor"].push(hoverColor);
  }
  
  public chartClicked(e: any): void 
  { 

  }
  public chartHovered(e: any): void 
  { 

  }
}