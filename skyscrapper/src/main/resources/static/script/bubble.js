const ctx = document.getElementById('bubbleGraph');

$.ajax({
    url: 'bubble',
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        const inputData = data; // 데이터는 이미 JavaScript 객체이므로 파싱할 필요가 없음

        const datasets = inputData.map(item => ({
            x: item.count0,
            y: item.count1,
            r: Math.sqrt(item.count0 * item.count1)  // 반지름 계산
        }));

        const labels = inputData.map(item => item.label); // 각 원에 대한 레이블
        
        const maxRadius = Math.max(...datasets.map(item => item.r));  // 데이터의 최대 반지름 계산

        const scaleFactor = 50 / maxRadius;  // 원의 크기를 조정할 스케일 팩터 계산
        const maxAllowedRadius = 50;  // 최대 허용 반지름 값 설정

        const normalizedDatasets = datasets.map(item => ({
            x: item.x,
            y: item.y,
            r: Math.min(item.r * scaleFactor, maxAllowedRadius)  // 크기 정규화 및 최대 반지름 값과 비교하여 크기 제한
        }));

        const chartData = {
            datasets: [{
                label: 'First Dataset',
                data: normalizedDatasets,
                backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(255, 205, 86)', 'rgb(100, 205, 86)', 'rgb(255, 85, 86)', 'rgb(255, 105, 86)']
            }]
        };

        new Chart(ctx, {
            type: 'bubble',
            data: chartData,
            options: {
                plugins:{
                    legend:{
                        display:true,
                        labels:{
                            generateLabels: function (chart){
                                const data= chart.data;
                                if (data.labels.length &&data.datasets.length){
                                    return data.labels.map(function (label, i){
                                        const meta = chart.getDatasetMeta(0);
                                        const style = meta.controller.getStyle(i);
                                        return {
                                            text:label,
                                            fillStyle: style.backgroundColor,
                                            strokeStyle: style.borderColor,
                                            lineWidth: style.borderWidth,
                                            hidden: isNaN(chart.data.datasets[0].data[i].x) || isNaN(chart.data.datasets[0].data[i].y) || isNan(chart.data.datasets[0].data[i].r),
                                            index:i
                                        };
                                    });
                                }
                                return [];
                            }
                        }
                    }
                }
                
            }
        });
    }
});

