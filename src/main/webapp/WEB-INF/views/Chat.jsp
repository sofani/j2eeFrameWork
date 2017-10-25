<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
<title>Home</title>
</head>
<body >
<button onclick="markicons()">Click</button>
    <script>
    
    
     var map;
     var marker;
    var watchID;
    var geoLoc;
    var latitude;
    var longitude;
    getLocationUpdate()
    function showLocation(position) {
        latitude = position.coords.latitude;
        longitude = position.coords.longitude;
       alert("Latitude : " + latitude + " Longitude: " + longitude);    
       
       //registerSSE();
       
    }
    
    
    function errorHandler(err) {
       if(err.code == 1) {
          alert("Error: Access is denied!");
       }
       
       else if( err.code == 2) {
          alert("Error: Position is unavailable!");
       }
    }
    
    function getLocationUpdate(){
       if(navigator.geolocation){
          // timeout at 60000 milliseconds (60 seconds)
          var options = {timeout:60000};
          geoLoc = navigator.geolocation;
          watchID = geoLoc.watchPosition(showLocation, errorHandler, options);
       }
       
       else{
          alert("Sorry, browser does not support geolocation!");
       }
    }
    
     
        function registerSSE()
        {         
        	
            var source = new EventSource('http://localhost:8080/SpringMVCHibernate/chat1?lat='+latitude+'&lon='+longitude);            
            source.onmessage=function(event)         
            {       	
               document.getElementById("result").innerHTML+=event.data + "<br />";
                
            };

            /*source.addEventListener('server-time',function (e){
                alert('ea');
            },true);*/
        }
        
        function InitializeMap() {
            
            var latlng = new google.maps.LatLng(latitude, longitude);
            var myOptions = {
                zoom: 15,
                center: latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map"), myOptions);
            
        }
        function markicons() {   

           InitializeMap();  
           
                var geocoder = new google.maps.Geocoder;
                var infowindow = new google.maps.InfoWindow({
                content: 'Location info:<br/>Country Name:<br/>LatLng:'
            });
                var ltlng = [];
                
                ltlng.push(new google.maps.LatLng(latitude, longitude));
                ltlng.push(new google.maps.LatLng(37.845, -122.269));
                ltlng.push(new google.maps.LatLng(37.845, -122.267));

                map.setCenter(ltlng[0]);
                for (var i = 0; i <= ltlng.length; i++) {
                    marker = new google.maps.Marker({
                        map: map,
                        position: ltlng[i],
                        title: 'Click to cab No '+i
                    });

                    (function (i, marker) {

                        google.maps.event.addListener(marker, 'click', function () {
                             
                            if (!infowindow) {
                                infowindow = new google.maps.InfoWindow();
                            }

                            infowindow.setContent("Message to Cab No " +i+" at position "+ ltlng[i]);

                            infowindow.open(map, marker);
                           
                        });

                    })(i, marker);    

                }
        }

     window.onload = markicons;
    </script>
    
<p>here</p>
    <div id ="result"></div>
    <div id ="map"></div>
</body>
</html>





