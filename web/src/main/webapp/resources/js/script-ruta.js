window.onload = function () {

    // TO MAKE THE MAP APPEAR YOU MUST
    // ADD YOUR ACCESS TOKEN FROM
    // https://account.mapbox.com

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';
            const map = new mapboxgl.Map({
                container: 'map',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [position.coords.longitude, position.coords.latitude],
                zoom: 12
            });

            map.addControl(new mapboxgl.GeolocateControl({
                positionOptions: {
                    enableHighAccuracy: true
                },
                trackUserLocation: true
            }));

            map.addControl(new mapboxgl.NavigationControl());

            map.addControl(
                new MapboxDirections({
                    accessToken: mapboxgl.accessToken
                }),
                'top-left'
            );
        })
    } else {
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [-72.309, 4.473],
            zoom: 4.5
        });
    }
}