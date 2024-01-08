/**
 * 
 */

/* 막대 */

$(document).ready(function(){
   showCharts();
});

function showCharts() {
   getBarChart();
   radarChart();
   spline1();
   bubbleChart();
   spline2();
   spline3();
}



function getBarChart(){
   
   var fromDate = document.getElementById("fromDate").value;
   var toDate = document.getElementById("toDate").value;
   
   anychart.onDocumentReady(function () {
     $.ajax({
       url: 'bar',
       method: 'get',
       data:{
         fromDate: fromDate,
         toDate: toDate
      },
       success: function (data) {
         
         // 차트 컨테이너 비우기
          var container = $('#container1');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Total number of reviews by brand</p>');
          container.append(paragraph);
          
         // 데이터를 처리하고 차트 생성
   
         var chartData = []; // 차트 데이터를 저장할 배열
   
         // 데이터를 순회하며 차트 데이터 형식에 맞게 추가
         for (var i = 0; i < data.length; i++) {
           var item = data[i];
           var color = (item.reviewCnt === getMaxValue(data, "reviewCnt")) ? "rgb(154, 180, 254)" : "#808080";
           var border = (item.reviewCnt === getMaxValue(data, "reviewCnt")) ? "rgb(154, 180, 254)" : "#808080";
           chartData.push({ x: item.companyNn, value: item.reviewCnt, fill: color, stroke:border });
         }
         
        // 최대값을 반환하는 함수
         function getMaxValue(data, prop) {
           var max = Number.NEGATIVE_INFINITY;
           for (var i = 0; i < data.length; i++) {
             var value = data[i][prop];
             if (value > max) {
               max = value;
             }
           }
           return max;
         }
   
         // 차트 생성
         var chart = anychart.column();
   
         // 컬럼 시리즈 생성 및 데이터 설정
         var series = chart.column(chartData);
         
         //투명하게
         chart.background().fill("rgba(0, 0, 0, 0)");
   
         // 차트 제목 설정
         // chart.title("Total number of reviews by brand");
   
         // x축과 y축의 제목 설정
         chart.xAxis().title("Brand");
         chart.yAxis().title("Number of Review");
   
         // x축과 y축 눈금 레이블 크기 조정
         chart.xAxis().labels().fontSize(17).fontWeight("bold"); // x축 눈금 레이블 폰트 크기 조정
         chart.yAxis().labels().fontSize(12).fontWeight("bold"); // y축 눈금 레이블 폰트 크기 조정
   
         // x축과 y축 라벨 크기 조정
         chart.xAxis().title().fontSize(18).fontColor("black"); // x축 라벨 폰트 크기 조정
         chart.yAxis().title().fontSize(18).fontColor("black"); // y축 라벨 폰트 크기 조정
   
         // 컨테이너 ID 설정
         chart.container("container1");
   
         // 차트 그리기
         chart.draw();
       }
     });
   });
}


/* RADAR */
function radarChart(){
   (function () {
     function ac_add_to_head(el) {
       var head = document.getElementsByTagName('head')[0];
       head.insertBefore(el, head.firstChild);
     }
     function ac_add_link(url) {
       var el = document.createElement('link');
       el.rel = 'stylesheet'; el.type = 'text/css'; el.media = 'all'; el.href = url;
       ac_add_to_head(el);
     }
     function ac_add_style(css) {
       var ac_style = document.createElement('style');
       if (ac_style.styleSheet) ac_style.styleSheet.cssText = css;
       else ac_style.appendChild(document.createTextNode(css));
       ac_add_to_head(ac_style);
     }
     ac_add_link('https://cdn.anychart.com/releases/8.11.1/css/anychart-ui.min.css?hcode=a0c21fc77e1449cc86299c5faa067dc4');
     ac_add_style(document.getElementById("ac_style_samples-bct-radar-area-chart").innerHTML);
     ac_add_style(".anychart-embed-samples-bct-radar-area-chart{width:600px;height:450px;}");
   })();
   
   anychart.onDocumentReady(function () {
      var fromDate = document.getElementById("fromDate").value;
      var toDate = document.getElementById("toDate").value;
      
     $.ajax({
       url: 'radar',
       method: 'get',
       data: {
         fromDate: fromDate,
         toDate: toDate
      },
       success: function (data) {
         
         // 차트 컨테이너 비우기
          var container = $('#container2');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Positive Sentiment Rate</p>' +
           '<span id="anychart-embed-samples-bct-radar-area-chart" class="anychart-embed anychart-embed-samples-bct-radar-area-chart"></span>' +
           '<script src="https://cdn.anychart.com/releases/8.11.1/js/anychart-base.min.js?hcode=a0c21fc77e1449cc86299c5faa067dc4"></script>' +
           '<script src="https://cdn.anychart.com/releases/8.11.1/js/anychart-radar.min.js?hcode=a0c21fc77e1449cc86299c5faa067dc4"></script>' +
           '<script src="https://cdn.anychart.com/releases/8.11.1/js/anychart-exports.min.js?hcode=a0c21fc77e1449cc86299c5faa067dc4"></script>' +
           '<script src="https://cdn.anychart.com/releases/8.11.1/js/anychart-ui.min.js?hcode=a0c21fc77e1449cc86299c5faa067dc4"></script>' +
           '<div id="ac_style_samples-bct-radar-area-chart" style="display:none;">' +
           'html, body, #container {' +
           'width: 100%;' +
           'height: 100%;' +
           'margin: 0;' +
           'padding: 0;' +
           '}' +
           '</div>');
          container.append(paragraph);
          
         
         
         // Process the received data and create the chart data array
         var chartData = [];
   
         // 데이터를 순회하며 차트 데이터 형식에 맞게 추가
         for (var i = 0; i < data.length; i++) {
           var item = data[i];
           var color = (item.rate === getMaxValue(data, "rate")) ? "rgba(154, 180, 254, 50)" : "#808080";
           var border = (item.rate === getMaxValue(data, "rate")) ? "rgba(154, 180, 254, 50)" : "#808080";
           chartData.push({ x: item.company, value: item.rate, fill: color, stroke:border });
         }
         
        // 최대값을 반환하는 함수
         function getMaxValue(data, prop) {
           var max = Number.NEGATIVE_INFINITY;
           for (var i = 0; i < data.length; i++) {
             var value = data[i][prop];
             if (value > max) {
               max = value;
             }
           }
           return max;
}
   
         // create a chart
         var chart = anychart.radar();
   
         // create an area series and set the data
         var series = chart.area(chartData);
   
         //투명하게
         chart.background().fill("rgba(0, 0, 0, 0)");
   
         // !!! 제목 !!!
         // chart.title("Positive Sentiment Rate");
   
         //폰트 크기 
         chart.xAxis().labels().fontSize(18).fontWeight('bold');
         chart.yAxis().labels().fontSize(15).fontWeight('bold').fontColor("#0A58CA");
   
         // set the container id
         chart.container("container2");
   
         // initiate drawing the chart
         chart.draw();
       }
     });
   });
}





/* 별점 선 */

function spline1(){


   anychart.onDocumentReady(function () {
      
      var fromDate = document.getElementById("fromDate").value;
      var toDate = document.getElementById("toDate").value;
   
     $.ajax({
       url: 'spline1',
       method: 'get',
       data: {
         fromDate: fromDate,
         toDate: toDate
      },
       success: function (data) {
         
         // 차트 컨테이너 비우기
          var container = $('#container3');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Star-Rating Graph</p>');
          container.append(paragraph);
         
         
         
         // Process the received data and create the chart
         var chartData = []; // Array to store the chart data
   
         // Iterate over the received data and format it for the chart
         for (var i = 0; i < data.length; i++) {
           var item = data[i];
           chartData.push({ x: item.yr, value: item.starRate });
         }
   
         // Create a chart
         var chart = anychart.line();
   
         // Create a spline series and set the data
         var series = chart.spline(chartData);
   
         // Set the chart title
         // chart.title("Star-rating graph");
         
         // Set the stroke color to blue and thickness to 5px
         series.stroke({
           color: "#7599FF",
           thickness: "5px"
         });
      
        // Create an area series for filling the area below the line
        var areaSeries = chart.area();
         areaSeries.data(series.data());
         

      
        /// create a linear gradient fill for the area series
         var gradient = {
           'keys': [
             { color: '#7599FF50', offset: '0%' }, // start color (fully opaque blue) at 0% offset
             { color: '#BDBDBD00', offset: '100%' } // end color (fully transparent blue) at 100% offset
           ],
           'angle': 270 // angle of the gradient (270 degrees for vertical gradient)
         };
         areaSeries.fill(gradient);
         
         
         areaSeries.fill(gradient);
         
        // Set the stroke of the area series to transparent
         areaSeries.stroke('none');
         

         //투명하게
         chart.background().fill("rgba(0, 0, 0, 0)");
   
         // Set the titles of the axes
         var xAxis = chart.xAxis();
         xAxis.title("Year")
         var yAxis = chart.yAxis();
         yAxis.title("Rating")


         
         
         // x축과 y축 눈금 레이블 크기 조정
         chart.xAxis().labels().fontSize(16).fontWeight("bold"); // x축 눈금 레이블 폰트 크기 조정
         chart.yAxis().labels().fontSize(16).fontWeight("bold"); // y축 눈금 레이블 폰트 크기 조정
   
         // x축과 y축 라벨 크기 조정
         chart.xAxis().title().fontSize(18).fontColor("black"); // x축 라벨 폰트 크기 조정
         chart.yAxis().title().fontSize(18).fontColor("black"); // y축 라벨 폰트 크기 조정
   
         // Set the container id
         chart.container("container3");
   
         // Initiate drawing the chart
         chart.draw();
       },
       passive: true
     });
   });
}



/* 버블 차트 */
function bubbleChart(){
   const ctx = document.getElementById('myChart');

   var fromDate = document.getElementById("fromDate").value;
   var toDate = document.getElementById("toDate").value;
   
   $.ajax({
     url: 'bubble',
     method: 'GET',
     data:{
      fromDate: fromDate,
      toDate: toDate
     },
     dataType: 'json',
     success: function (data) {
          
       const inputData = data;
   
       const datasets = inputData.map(item => ({
         x: item.count1,
         y: -item.count0,
         r: Math.sqrt(item.count0 * item.count1),
         companyName: item.companyName,
       }));
   
       const maxRadius = Math.max(...datasets.map(item => item.r));
   
       const scaleFactor = 50 / maxRadius;
       const maxAllowedRadius = 50;
   
       const normalizedDatasets = datasets.map(item => ({
         x: item.x,
         y: item.y,
         r: Math.min(item.r * scaleFactor, maxAllowedRadius),
         companyName: item.companyName,
       }));
   
       const chartData = {
         datasets: normalizedDatasets.map((item, index) => ({
           label: item.companyName,
           data: [{ x: item.x, y: item.y, r: item.r }],
           backgroundColor: getRandomPastelColor(index),
           borderColor: 'rgba(0, 0, 0, 0)',
           borderWidth: 0,
         }))
       };
    
      var myChart = '';
      
       myChart = new Chart(ctx, {
         type: 'bubble',
         data: chartData,
         options: {
             scales: {
                   xAxes: [{
                       ticks: {
                           fontSize: 14, // X 축 눈금 레이블 폰트 크기
                           fontStyle: 'bold', // X 축 눈금 레이블 폰트 스타일
                       }
                   }],
                   yAxes: [{
                       ticks: {
                           fontSize: 14, // Y 축 눈금 레이블 폰트 크기
                           fontStyle: 'bold', // Y 축 눈금 레이블 폰트 스타일
                       }
                   }]
               },
   
              plugins: {
                tooltip: {
                  callbacks: {
                    title: function () {
                      return '';
                    },
                    label: function (context) {
                      var datasetLabel = '';
                      var companyName = context.dataset.label || '';
                      var data = context.dataset.data[context.dataIndex];
                      return datasetLabel + ': (' + data.x + ', ' + data.y + ', ' + data.r + ') - ' + companyName;
                    }
                  }
                }
           },
           layout: { // 레이아웃 조정
             padding: { // 그래프와 범례 사이 여백
               top: 0,
               right: 50,
               bottom: 20,
               left: 0
             }
           },
           legend: {
             position: 'top' // 범례를 그래프 상단에 위치
           },
           elements: {
             point: {
               radius: function (context) {
                 return context.dataset.data[context.dataIndex].r;
               },
               hoverRadius: function (context) {
                 return context.dataset.data[context.dataIndex].r + 2;
               },
               hitRadius: 10,
               borderWidth: 0,
               backgroundColor: function (context) {
                 return context.dataset.backgroundColor;
               },
               hoverBackgroundColor: function (context) {
                 return context.dataset.backgroundColor;
               },
               pointStyle: 'circle'
             }
           }
         }
       });
       
     }
   });
}

function updateChart(chart, chartData) {
  chart.data = chartData;
  chart.update();
}

// 버블 차트를 위한 파스텔 color
function getRandomPastelColor(index) {
  const hue = (index * 57) % 360; // 인덱스를 이용해 고유한 색상을 생성
  const pastelColor = 'hsl(' + hue + ', 70%, 80%)';
  return pastelColor;
}







/* 마지막에서 두 번째 그래프 */
function spline2(){
   

   anychart.onDocumentReady(function () {
      
      var fromDate = document.getElementById("fromDate").value;
      var toDate = document.getElementById("toDate").value;
      
     $.ajax({
       url: 'spline2',
       method: 'get',
       data:{
        fromDate: fromDate,
        toDate: toDate
       },
       success: function (data) {
         
         // 차트 컨테이너 비우기
          var container = $('#container4');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Sales Tend by Year</p>');
          container.append(paragraph);
         
         // create data
         var chartData = [];
         for (var i = 0; i < data.length; i++) {
           var item = data[i];
           chartData.push({ x: item.reviewYr, value: item.reviews });
         }
   
         // create a chart
         var chart = anychart.line();
   
         // create a spline series and set the data
         var series = chart.spline(chartData);
   
         // set the chart title
         // chart.title("Sales trend by year");
         
         // Set the stroke color to blue and thickness to 5px
         series.stroke({
           color: "#7599FF",
           thickness: "5px"
         });
      
         
         // create an area series for filling the area below the line
         var areaSeries = chart.area(chartData);
         
         // create a linear gradient fill for the area series
         var gradient = {
           'keys': [
             { color: '#7599FF50', offset: '0%' }, // start color (fully opaque blue) at 0% offset
             { color: '#BDBDBD00', offset: '100%' } // end color (fully transparent blue) at 100% offset
           ],
           'angle': 270 // angle of the gradient (270 degrees for vertical gradient)
         };
         areaSeries.fill(gradient);
         
         // set the stroke of the area series to transparent
         areaSeries.stroke('none');
   
         //투명하게
         chart.background().fill("rgba(0, 0, 0, 0)");
         // set the titles of the axes
         var xAxis = chart.xAxis();
         xAxis.title("Year");
         var yAxis = chart.yAxis();
         yAxis.title("Sales(Reviews)");
   
         // x축과 y축 눈금 레이블 크기 조정
         chart.xAxis().labels().fontSize(17).fontWeight("bold"); // x축 눈금 레이블 폰트 크기 조정
         chart.yAxis().labels().fontSize(17).fontWeight("bold"); // y축 눈금 레이블 폰트 크기 조정
   
         // x축과 y축 라벨 크기 조정
         chart.xAxis().title().fontSize(18).fontColor("black"); // x축 라벨 폰트 크기 조정
         chart.yAxis().title().fontSize(18).fontColor("black"); // y축 라벨 폰트 크기 조정
   
         // set the container id
         chart.container("container4");
   
         // initiate drawing the chart
         chart.draw();
       },
       passive: true
     });
   });
}





/* 마지막 그래프 */
function spline3(){
   anychart.onDocumentReady(function () {
         var fromDate = document.getElementById("fromDate").value;
         var toDate = document.getElementById("toDate").value;
         
     // Create a chart
     var chart = anychart.line();
   
     // Create a container for the legend
     var legend = chart.legend();
     legend.title("Brands"); // Set the legend title
   
     $.ajax({
       url: 'spline3',
       method: 'get',
       data:{
           fromDate: fromDate,
           toDate: toDate
         },
       success: function (data) {

         // 차트 컨테이너 비우기
          var container = $('#container5');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Brands\' Sale trends by Year</p>');
          container.append(paragraph);
         
         // Iterate over the data and create a series for each brand
         var brands = [];
         for (var i = 0; i < data.length; i++) {
           var item = data[i];
           if (!brands.includes(item.brand)) {
             brands.push(item.brand);
             var filteredData = data.filter(function (d) {
               return d.brand === item.brand;
             }).map(function (d) {
               return {
                 x: d.salesYr,
                 value: d.sales
               };
             });
             var series = chart.line(filteredData);
             series.name(item.brand);
             
      
   
             // Configure tooltip for each series
             var tooltip = series.tooltip();
             tooltip.title().text(item.brand); // Set the tooltip title as the brand
             tooltip.format("{%x}: {%value}"); // Set the tooltip format for x (year) and value (sales)
           }
         }
   
         // Set the chart title
         // chart.title("Sales trend by year");
   
         //투명하게
         chart.background().fill("rgba(0, 0, 0, 0)");
   
         // Set the titles of the axes
         var xAxis = chart.xAxis();
         xAxis.title("Year");
         var yAxis = chart.yAxis();
         yAxis.title("Sales");
   
         // x축과 y축 눈금 레이블 크기 조정
         chart.xAxis().labels().fontSize(17).fontWeight("bold"); // x축 눈금 레이블 폰트 크기 조정
         chart.yAxis().labels().fontSize(17).fontWeight("bold"); // y축 눈금 레이블 폰트 크기 조정
   
         // x축과 y축 라벨 크기 조정
         chart.xAxis().title().fontSize(18).fontColor("black"); // x축 라벨 폰트 크기 조정
         chart.yAxis().title().fontSize(18).fontColor("black"); // y축 라벨 폰트 크기 조정
   
         // Set the container id
         chart.container("container5");
   
         // Configure the legend
         chart.legend()
           .enabled(true) // Enable the legend
           .position("bottom") // Position the legend at the top
           .itemsLayout("horizontal") // Display legend items vertically
           .align("center") // Right-align the legend items
           .itemsSpacing(10) // Add spacing between legend items
           .padding([0, 0, 20, 0]); // Add padding to the legend (top, right, bottom, left)
   
         // Initiate drawing the chart
         chart.draw();
       }
     });
   });     
}
    