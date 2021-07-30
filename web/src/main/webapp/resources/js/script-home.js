function crearMapa(lugares) {

    mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';

    let bounds = new mapboxgl.LngLatBounds();
    let markers = [];

    for (let l of lugares) {
        markers.push( new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup()
            .setHTML("<strong>" + l.nombre + "</strong><br>" + l.descripcion +
                "<br><a href='/detalleLugar.xhtml?lugar=" + l.id + "'>Ver detalles</a>") ));
        bounds.extend([l.lng, l.lat]);
    }
    if(bounds.isEmpty()){
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [-72.309, 4.473],
            zoom: 8
        });
    } else{
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [-72.309, 4.473],
            zoom: 7, bounds: bounds
        });
        map.fitBounds(bounds, {padding: 100});
    }

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    map.addControl(new mapboxgl.NavigationControl());

    map.on("load", function (t) {
        //luego se agregan al mapa
        markers.forEach(m => {
            m.addTo(map).togglePopup();
        })
    })

}