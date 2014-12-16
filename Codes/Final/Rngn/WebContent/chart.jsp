<script type="text/javascript">
			var chart;
			jQuery(document).ready(function() {
				var colors = Highcharts.getOptions().colors,
					categories = [
					 
					'201405',  
					 
					'201406',  
					 
					'201407',  
					 
					'201408',  
					 
					'201409',  
					 
					'201410',  
					 
					],
					data = [{ 
							y: 108.10,
							color: colors[0]
						}, {
						 	y: 18.00,
							color: colors[0]
						}, {
							y: 18.48,
							color: colors[0] 
						}, {
							y: 39.90,
							color: colors[0] 
						}, {
							y: 58.60,
							color: colors[0] 
						},{
						    y: 18.00,
							color: colors[0] 
						}];
				chart = new Highcharts.Chart({
				    legend: {
				   		enabled: false
        			},
					chart: {
						renderTo: 'container', 
						type: 'column'
					},
					exporting: {
						buttons:{
						 exportButton:{
						  enabled:false
						 },
						 printButton:{
						  enabled:false
						 }
						}
					},
					title: {
						text: '近六个月消费趋势图（单位：元）',
						style:{
						 		fontSize:'10px'
						}
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: categories							
					},
					yAxis: {
						title: {
							text: ''
						}
					},
					credits:{
						enabled:false
					},
					tooltip: {
						formatter: function() {
							var point = this.point,
								s = this.x +'<br/>　　消费:<b>'+ this.y.toFixed(2) +'　元</b>';
							return s;
						}
					},
					plotOptions: {
						column: {
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: colors[0],
								style: {
									fontWeight: 'bold'
								},
								formatter: function() {
									return this.y.toFixed(2);
								}
							}					
						}
					},
					series: [{
						data: data,
						color: 'white'
					}]
				});				
			});
		</script>  
		<div id="container" style="width: 440px; height: 200px; margin: 0 auto"></div>
    
    	<script type="text/javascript">
			var chart;
			jQuery(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container2',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					credits:{
						enabled:false
					},					
					exporting: {
						buttons:{
						 exportButton:{
						  enabled:false
						 },
						 printButton:{
						  enabled:false
						 }
						}
					},
					title: {
						text: '当月费用结构图',
						style:{
						 		fontSize:'10px'
						}
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
						}
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								fontSize: '10px',
								color: '#4572A7',
								formatter: function() {
								return  this.percentage.toFixed(2) +' %';
						}
							},
							showInLegend: true
						}
					},
				    series: [{
						type: 'pie',
						name: '',
						fontSize: '10px',
						data: [
							['套餐及固定费',18.00],
							['语音通信费',15.20],
							['上网费',0.00],
							{	name: '短彩信费',
								y: 6.70,
								sliced: false,
								selected: false
							},
							['增值业务A类',0.00],
							['增值业务B类',0.00],
							['代收费',0.00],
							['其他',0.00]
						]
					}]
				});
			});			
		</script>
		 <div id="container2" style="width: 440px; height: 300px; margin: 0 auto"></div>