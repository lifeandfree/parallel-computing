<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
<html lang="RU">
<head>
    <title>${title}</title>
    <meta name="keywords" content="${keywords}" />
	<meta name="description" content="${description}" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="//api-maps.yandex.ru/2.0/?load=package.standard,package.geoObjects&lang=ru-RU" type="text/javascript"></script>
	<script type="text/javascript">
	ymaps.ready(init);
	var myMap, myGeoObject, myRectangle;

	function init () {
	    myMap = new ymaps.Map('map', {
	        center: [55.674, 37.601],
	        zoom: 11
	    });
	    
	    var myCircle = new ymaps.Circle([1, 1], 10);
	    
	    myCircle.events.add('change', function () {
	        alert('Геометрия изменилась');
	    });
	    myMap.geoObjects.add(myCircle);
	    myCircle.freeze();
	    myCircle.setCoordinates([1,1]);
	    myCircle.unfreeze();
	    

	    myMap.events.add('click', function (e) {
	        if (!myMap.balloon.isOpen()) {
	            var coords = e.get('coordPosition');
	            myCircle.setCoordinates([coords[0].toPrecision(6),
                    coords[1].toPrecision(6)]);
	            
	        }
	        else {
	            myMap.balloon.close();
	        }
	    });


	    myMap.geoObjects
	        .add(myRectangle)
	        .add(myGeoObject);
	}

	</script>
</head>
<body>
		${HEADER}
		${body}

    <div id="map" style="width:400px; height:300px"></div>
		${footer}
</body>
</html>
