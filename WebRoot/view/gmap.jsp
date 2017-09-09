<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Google Maps JavaScript API DEMO WinZhong</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
    <script>

var map;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 39.90293, lng: 116.46459},
    zoom: 8
  });
} 

    </script>
     <!-- <script src="http://ditu.google.cn/maps/api/js?key=&sessor=false"></script> -->
    <script src="http://ditu.google.cn/maps/api/js?key=AIzaSyB6vAn1dCaX31oN65hCBeSYRJmEmFjVzYc&callback=initMap"
        async defer></script>
        
      <!--   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB6vAn1dCaX31oN65hCBeSYRJmEmFjVzYc&callback=initMap"
        async defer></script> -->
  </body>
</html>