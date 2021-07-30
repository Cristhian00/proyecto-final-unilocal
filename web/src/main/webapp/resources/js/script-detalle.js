function crearMapa(lugar) {

    mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lugar.lng, lugar.lat],
        zoom: 10
    });

    new mapboxgl.Marker().setLngLat([lugar.lng, lugar.lat]).setPopup(new mapboxgl.Popup()).addTo(map);

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    map.addControl(new mapboxgl.NavigationControl());
/*
    map.on("load", function (t) {
        new mapboxgl.Marker().setLngLat([lugar.lng, lugar.lat]).setPopup(new mapboxgl.Popup()).addTo(map);
    })

 */
}