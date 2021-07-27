function crearMapa(lugar) {

    console.log("Lugar: " + lugar.id + " - " + lugar.nombre)
    mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 14
    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    //map.addControl(new mapboxgl.NavigationControl());

    map.on("load", function (t) {
        ubicarLugar(lugar, map);
    })
}

function ubicarLugar(lugar, map) {

    let bounds = new mapboxgl.LngLatBounds();

    new mapboxgl.Marker().setLngLat([lugar.lng, lugar.lat]).setPopup(new mapboxgl.Popup()).addTo(map);
    bounds.extend([lugar.lng, lugar.lat]);

    map.fitBounds(bounds, {padding: 100});
}