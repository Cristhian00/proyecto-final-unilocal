function crearMapa(lugares) {

    console.log("Que hago aqui?")
    mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 4.5
    });

    map.on("load", function (t) {
        ubicarLugares(lugares, map);
    })
}

function ubicarLugares(lugares, map) {

    let bounds = new mapboxgl.LngLatBounds();
    for (let l of lugares) {
        new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup()
            .setHTML("<strong>" + l.nombre + "</strong><br>" + l.descripcion +
                "<br><a href='http://localhost:8080/detalleLugar.xhtml?lugar=" + l.id + "'>Ver detalles</a>")).addTo(map).togglePopup();
        bounds.extend([l.lng, l.lat]);
    }
    map.fitBounds(bounds, {padding: 100});
}