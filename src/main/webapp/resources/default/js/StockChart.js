function drawLineChart(xhr, status, args) {
    var ysetlabel = JSON.parse(args.ysetlabel);
    var xlabels = JSON.parse(args.xlabels);
    var yset = JSON.parse(args.yset);
    var xset = JSON.parse(args.xset);
	
	lineChartData = {}; //declare an object
	lineChartData.labels = xlabels  //add 'labels' element to object (X axis)
	//[ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	lineChartData.datasets = []; //add 'datasets' array element to object

	
	lineChartData.datasets.push({}); //create a new line dataset
	dataset = lineChartData.datasets[0]
	dataset.pointBorderColor = "rgba(0,0,0,0)";
	dataset.borderColor = "rgba(185,244,50,1)";
	dataset.label = ysetlabel
    //"BTC-EUR";
	dataset.data = yset; //contains the 'Y; axis data
	//yset = [ 84.5, 86, 84.8, 87, 83.3, 85.9, 87.4, 80.6, 82.0, 84.6, 88.6, 90.0 ];

	var ctx = document.getElementById("myChart").getContext("2d");
	var myChart = new Chart(ctx, {
		type : 'line',
		data : lineChartData,
		options : {}
	});
}