<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>

		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">

		<title>Travel Information</title>
		<!-- Header file included containing navbar-->
		<?php include ("inc/header.php"); ?>
    <div class="container-fluid">
      <div class="row">
        <h1>Travel Information</h1>
        <hr>
					<h3>Airports</h3>
					<p>The major airports serving international flights are Punta Cana International Airport(PUJ) located in Punta Cana, and Las Americas International Airport(SDQ) located in Santo Domingo. </p>
					<h3>Transportation</h3>
					<p>There are several ways of transportation. The ones tourist frequent the most are Uber and Taxi. There's also buses, bike ridesharing, and public car ridesharing. </p>
					<h3>Stays</h3>
					<p>There's multiple places you can stay at such as: Motels, Hotels or Airbnb apartments.</p>
					<h3>Map</h3>
					<div id="map">
					</div>
      </div>
				<!-- End of Row-->
  	</div>
		<script>
      function initMap() {
        var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA5VE7h60gjN070v6O7kr7m9hzaNiRUANc&callback=initMap">
    </script>
		<!-- End of Container-->
	<!-- Footer file included-->
  <?php include ("inc/footer.php"); ?>
