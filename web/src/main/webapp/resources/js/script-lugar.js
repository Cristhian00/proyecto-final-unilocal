window.onload = function () {

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {


            let enable = true;
            mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';
            var map = new mapboxgl.Map({
                container: 'mapa',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [position.coords.longitude, position.coords.latitude],
                zoom: 20
            });

            map.addControl(new mapboxgl.GeolocateControl({
                positionOptions: {
                    enableHighAccuracy: true
                },
                trackUserLocation: true
            }));

            map.addControl(new mapboxgl.NavigationControl());

            map.on("click", function (t) {
                if (enable) {
                    setLatLng(t.lngLat.lat, t.lngLat.lng);
                    enable = false;
                    let marker = new mapboxgl.Marker({draggable: true}).setLngLat([t.lngLat.lng, t.lngLat.lat]).addTo(map);

                    marker.on("dragend", function () {
                        var lngLat = marker.getLngLat();
                        setLatLng(lngLat.lat, lngLat.lng);
                        console.log('Longitude: ' + lngLat.lng + ', Latitude: ' + lngLat.lat);
                    })
                }
            });
        })
    }
}

function setLatLng(lat, lng) {
    document.getElementById("crear-lugar:lat-lugar").value = lat;
    document.getElementById("crear-lugar:lng-lugar").value = lng;
}