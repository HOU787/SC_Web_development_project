
function showCharts(button) {
  var brand_name;
  if(button){
	  brand_name = button.dataset.brand;
  } else{
	  brand_name="toto";
  }
  getBarChart(brand_name);
  getPieChart(brand_name);
  radarChart(brand_name);
  bubbleChart(brand_name);
}

$(document).ready(function(){
	showCharts();
});
function getBarChart(brand_name){
	
	anychart.onDocumentReady(function () {
	  $.ajax({
	    url: 'brand_bar',
	    method: 'get',
	    data:{
			brand_name: brand_name
		},
	    success: function (data) {
			
	      // 차트 컨테이너 비우기
          var container = $('#bargraph1');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Total number of reviews by Product</p>');
          container.append(paragraph);
          
	      // 데이터를 처리하고 차트 생성
	
	      var chartData = []; // 차트 데이터를 저장할 배열
	
	      // 데이터를 순회하며 차트 데이터 형식에 맞게 추가
	      for (var i = 0; i < data.length; i++) {
	        var item = data[i];
	        chartData.push([item.companyNn, item.reviewCnt]);
	      }
		
	      // 차트 생성
	      var chart = anychart.column();
	
	      // 컬럼 시리즈 생성 및 데이터 설정
	      var series = chart.column(chartData);
	
	      //투명하게
	      chart.background().fill("rgba(0, 0, 0, 0)");
		
		
		
	      // 차트 제목 설정
	      // chart.title("Total number of reviews by product");
	
	      // x축과 y축의 제목 설정
	      chart.xAxis().title("Products");
	      chart.yAxis().title("Number of Review");
	
	      // x축과 y축 눈금 레이블 크기 조정
	      chart.xAxis().labels().fontSize(10).fontWeight("bold"); // x축 눈금 레이블 폰트 크기 조정
	      chart.yAxis().labels().fontSize(13).fontWeight("bold"); // y축 눈금 레이블 폰트 크기 조정
	
	      // x축과 y축 라벨 크기 조정
	      chart.xAxis().title().fontSize(15).fontColor("black"); // x축 라벨 폰트 크기 조정
	      chart.yAxis().title().fontSize(18).fontColor("black"); // y축 라벨 폰트 크기 조정
	
			
	      // 컨테이너 ID 설정
	      chart.container("bargraph1");
	
	      // 차트 그리기
	      chart.draw();
	    }
	  });
	});
}
function getPieChart(brand_name){
	
	
	anychart.onDocumentReady(function () {
	  $.ajax({
	    url: 'brand_pie',
	    method: 'get',
	    data:{
			brand_name: brand_name
		},
	    success: function (data) {
			
	      // 차트 컨테이너 비우기
          var container = $('#piegraph1');
          container.empty();

          // <p> 요소 추가
          var paragraph = $('<p class="title">Comparison of mentions by model name</p>');
          container.append(paragraph);
          
	      // 데이터를 처리하고 차트 생성
	
	      var chartData = []; // 차트 데이터를 저장할 배열
	
	      // 데이터를 순회하며 차트 데이터 형식에 맞게 추가
	      for (var i = 0; i < data.length; i++) {
	        var item = data[i];
	        chartData.push([item.companyNn, item.reviewCnt]);
	      }
		
	      // 차트 생성
	      var chart = anychart.pie(chartData);
	
	      // 컬럼 시리즈 생성 및 데이터 설정
	      //var series = chart.pie(chartData);
	
	      //투명하게
	      chart.background().fill("rgba(0, 0, 0, 0)");
		
		
	      // 차트 제목 설정
	      // chart.title("Total number of reviews by product");

	
			
	      // 컨테이너 ID 설정
	      chart.container("piegraph1");
	
	      // 차트 그리기
	      chart.draw();
	    }
	  });
	});
}

/* RADAR */
function radarChart(brand_name){
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
		

	  $.ajax({
	    url: 'brand_radar',
	    method: 'get',
	    data: {
			brand_name: brand_name
		},
	    success: function (data) {
			
	      // 차트 컨테이너 비우기
          var container = $('#radargraph1');
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
	
	      // Iterate over the received data and format it for the chart
	      for (var i = 0; i < data.length; i++) {
	        var item = data[i];
	        chartData.push({ x: item.company, value: item.rate });
	      }
	      if(data.length>=3){
			  // create a chart
		      var chart = anychart.radar();
		
		      // create an area series and set the data
		      var series = chart.area(chartData);
		
		      //투명하게
		      chart.background().fill("rgba(0, 0, 0, 0)");
		
		      // !!! 제목 !!!
		      // chart.title("Positive Sentiment Rate");
		
		      //폰트 크기 
		      chart.xAxis().labels().fontSize(10).fontWeight('bold');
		      chart.yAxis().labels().fontSize(10).fontWeight('bold').fontColor("#0A58CA");
		
		      // set the container id
		      chart.container("radargraph1");
		
		      // initiate drawing the chart
		      chart.draw();
		  } else {
			  container.append('<p>Statistics cannot be calculated due to insufficient number of models for the product.<p>')
		  }
	
	      
	    }
	  });
	});
}
var myChart='';
/* 버블 차트 */
function bubbleChart(brand_name){

	
	const ctx = document.getElementById('myChart');



	$.ajax({
	  url: 'brand_bubble',
	  method: 'GET',
	  data:{
		brand_name: brand_name
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
	    const maxAllowedRadius = 100;
	
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
 		ctx.width="100%";
        if (myChart){
			myChart.destroy();
		}

		
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