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
	var telModel;
	flagShape = 'none';
	
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
		document.getElementById("none").addEventListener("click", function(){
			flagShape = 'none';
			clearShape();
		});
		
		jQuery(document).ready(function(){                          
		    $('#getClient').click(function(){ 
		    	var myCircleCoorX, myCircleCoorY, firstPointRectX, firstPointRectY,secondPointRectX, secondPointRectY, fShape;
		    	telModel = document.getElementById('modelSelect').value;
		    	if (myCircleCoor != null) {myCircleCoorX =myCircleCoor[0];
		    	myCircleCoorY = myCircleCoor[1];
		    	}
		    	if (firstPointRect != null){firstPointRectX = firstPointRect[0];
		    	firstPointRectY = firstPointRect[1];
		    	}
		    	if (secondPointRect != null){secondPointRectX = secondPointRect[0];
		    	secondPointRectY = secondPointRect[1];
		    	}
		    	if(flagShape != 'none'){fShape = flagShape.charAt(0);}
		    	$.get( "localhost:8080/tarcam/clients", { tm:telModel, t:fShape, ccx: myCircleCoorX, ccy: myCircleCoorY,cr: circleRadiusSize, 
		    		fprx: firstPointRectX, fpry: firstPointRectY, sprx: secondPointRectX, spry: secondPointRectY } );
		    	//tm:Модель телефона t тип выделения, ccx центр круга X, ccy: центр круга Y,cr: радиус, fprx: левый верх. X, fpry: левый верх Y,
		    	//sprx: правый нижний X, spry: правый нижний Y
		    	})
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
				    	    ],  {
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
        h1, h2, h3 {
        	margin: 0;
        }
        h1{
        	text-align: center;
        }
        body {
		    background: #f2f2f2;
	    }
	    #form-fields > select{
	    font-weight: 700;
			  color: white;
			  text-decoration: none;
			  padding: .8em 1em calc(.8em + 3px);
			  border-radius: 3px;
			  background: rgb(64,199,129);
			  box-shadow: 0 -3px rgb(53,167,110) inset;
			  transition: 0.2s;
	    }
	    #form-fields > input {
			  font-weight: 700;
			  color: white;
			  text-decoration: none;
			  padding: .8em 1em calc(.8em + 3px);
			  border-radius: 3px;
			  background: rgb(64,199,129);
			  box-shadow: 0 -3px rgb(53,167,110) inset;
			  transition: 0.2s;
		} 
		#form-fields > input:hover { background: rgb(53, 167, 110); }
		#form-fields > input:active {
		  background: rgb(33,147,90);
		  box-shadow: 0 3px rgb(33,147,90) inset;
		}
    </style>	
</head>
<body>
		${HEADER}
		${body}
	<div>
		<span><h3>Выберите название модели: </h3></span>
		<div id="form-fields">
		${selcamera}
		<input type="button" id = "getClient" value="Получить клиентов"></div>
	</div>		
	<br>
	<div>
		<span><h3>Выберите место или область на карте (опционально): </h3></span>
	</div>
	<div>
		<span>Способ выделения области</span>
		<form action="">
		  <input id="none" type="radio" name="gender" value="none" checked> Нет геолокации <br>
		  <input id="circle" type="radio" name="gender" value="circle"> Круг <input type="text" id="circleRadius" pattern="0123456789" value="10000"><span> м</span><br>
		  <input id="rectangle" type="radio" name="gender" value="rectangle"> Прямоугольник<br>
		</form>
	</div>
	<div id="map"></div>
		${footer}
</body>
</html>
