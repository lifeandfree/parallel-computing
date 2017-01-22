<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="RU">
<head>
    <title>${title}</title>
    <meta name="keywords" content="${keywords}" />
	<meta name="description" content="${description}" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="//api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
	 <script src="//yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	ymaps.ready(init);
	var myMap, myGeoObject, myRectangle, myCircle;
	var flagShape, firstPointRect, secondPointRect, myCircleCoor, centerCoor, circleRadiusSize;
	var flagShowRect;
	flagShape = 'circle';
	
	function isInt(value) {
		  return !isNaN(value) && 
		         parseInt(Number(value)) == value && 
		         !isNaN(parseInt(value, 10));
		}
	
	function init () {

		function clearShape(){
			firstPointRect = null;
			secondPointRect = null;
			circleRadiusSize = null;
			myCircleCoor = null;
			myMap.geoObjects.removeAll();
		}
		
		var circleRadius = document.getElementById("circleRadius");
		document.getElementById("circle").addEventListener("click", function(){
			flagShape = 'circle';
			clearShape();
		});
		document.getElementById("rectangle").addEventListener("click", function(){
			flagShape = 'rectangle';
			clearShape();
		});

		var controlPanel = ['zoomControl', 'typeSelector',  'fullscreenControl', 'geolocationControl'];
	    ymaps.geolocation.get().then(function (res) {
	        var mapContainer = $('#map'),
	            bounds = res.geoObjects.get(0).properties.get('boundedBy'),
	            // Рассчитываем видимую область для текущей положения пользователя.
	            mapState = ymaps.util.bounds.getCenterAndZoom(
	                bounds,
	                [mapContainer.width(), mapContainer.height()]
	            );
	        mapState.controls = controlPanel;
	        createMap(mapState);
	    }, function (e) {
	        // Если местоположение невозможно получить, то просто создаем карту.
	        createMap({
	            center: [55.751574, 37.573856],
	            zoom: 10,
	            controls: controlPanel
	        });
	    });
	    
	    function createMap (state) {
	    	myMap = new ymaps.Map('map', state);
	    
		
// 	    myMap = new ymaps.Map('map', {
//             center: [55.751574, 37.573856],
//             zoom: 10,
//             controls: ['zoomControl', 'typeSelector',  'fullscreenControl', 'geolocationControl']
//         });

// 	    myCircle.events.add('change', function () {
// 	        alert('Геометрия изменилась');
// 	    });
	    
		   myMap.events.add('click', function (e) {
		    	
		    	var coords = e.get('coords');
		    	if (flagShape === 'circle'){
		    		circleRadiusSize = circleRadius.value;
		    		if (!isInt(circleRadiusSize)){
		    			circleRadiusSize = 10000;
		    		}
		    		if (circleRadiusSize){
		    			
		    		}
		    		myCircleCoor = [coords[0].toPrecision(6), coords[1].toPrecision(6)];
		    		if (myCircle == null){
					    myCircle = new ymaps.Circle([myCircleCoor, circleRadiusSize]);
					    myMap.geoObjects.add(myCircle);	 	    			
		    		}else{
		    			
					    myCircle.geometry.freeze();
					    myCircle.geometry.setCoordinates(myCircleCoor);
					    myCircle.geometry.setRadius(circleRadiusSize);
				    	myCircle.geometry.unfreeze();
				    	myMap.geoObjects.add(myCircle);
		    		}
		    	}
		    	if (flagShape === 'rectangle'){ 
	    			flagShowRect = true;	
					if (firstPointRect != null &&  secondPointRect != null){
						firstPointRect = [coords[0].toPrecision(6), coords[1].toPrecision(6)];
						secondPointRect = null;
		    			myMap.geoObjects.remove(myRectangle);
		    			flagShowRect = false;
					}
	
					if (firstPointRect != null &&  secondPointRect == null && flagShowRect == true){
						
						secondPointRect = [coords[0].toPrecision(6), coords[1].toPrecision(6)];
			    		if (myRectangle == null)
			    		{
				    	    myRectangle = new ymaps.Rectangle([
				    	    	firstPointRect,
				    	    	secondPointRect
				    	    ], {
				    	        //Свойства
				    	        hintContent: 'Меня перетаскивать нельзя!',
				    	        balloonContent: 'Прямоугольник 1'
				    	    }, {
				    	        // Опции.
				    	        // Цвет и прозрачность заливки.
				    	        fillColor: '#7df9ff33',
				    	        // Дополнительная прозрачность заливки..
				    	        // Итоговая прозрачность будет не #33(0.2), а 0.1(0.2*0.5).
				    	        fillOpacity: 0.5,
				    	        // Цвет обводки.
				    	        strokeColor: '#0000FF',
				    	        // Прозрачность обводки.
				    	        strokeOpacity: 0.5,
				    	        // Ширина линии.
				    	        strokeWidth: 2,
				    	        // Радиус скругления углов.
				    	        // Данная опция принимается только прямоугольником.
				    	        borderRadius: 6
				    	    });
				    		
				    	    myMap.geoObjects
				    	        .add(myRectangle);
			    		}
			    		else
			    		{
			     			myMap.geoObjects.add(myRectangle);
			    			myRectangle.geometry.setCoordinates([firstPointRect,  	secondPointRect]);	
			    		}
					}
					
		
	
					
					if (firstPointRect == null &&  secondPointRect == null){
						
						firstPointRect = [coords[0].toPrecision(6), coords[1].toPrecision(6)];
						
					}
					
	
		    	}
	// 		    myMap.geoObjects.add(myCircle);
	// 	        if (!myMap.balloon.isOpen()) {
	// 	            var coords = e.get('coordPosition');
	// 	            myCircle.setCoordinates([coords[0].toPrecision(6),
	//                     coords[1].toPrecision(6)]);
		            
	// 	        }
	// 	        else {
	// 	            myMap.balloon.close();
	// 	        }
		    });

	    }
// 	    myMap.geoObjects
// 	        .add(myRectangle)
// 	        .add(myGeoObject);
	}

	</script>
	<style>
        html, body, #map {
            width: 100%; height: 100%; padding: 0; margin: 0;
        }
        h1, h2 {
        	margin: 0;
        }
        h1{
        	text-align: center;
        }
    </style>	
</head>
<body>
		${HEADER}
		${body}
	<div>
		<span><h2>Выберите название модели: </h2></span>
		${selcamera}
		<input type="button" value="Получить клиентов">
	</div>		
	<div>
		<span>Выберите место или область на карте (опционально): </span>
	</div>
	<div>
		<span>Способ выделения области</span>
		<form action="">
		  <input id="circle" type="radio" name="gender" value="circle" checked> Круг <input type="text" id="circleRadius" pattern="0123456789" value="10000"><span> м</span><br>
		  <input id="rectangle" type="radio" name="gender" value="rectangle"> Прямоугольник<br>
		</form>
	</div>
	<div id="map"></div>
		${footer}
</body>
</html>
