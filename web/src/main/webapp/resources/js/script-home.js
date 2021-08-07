function crearMapa(lugares) {

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            mapboxgl.accessToken = 'pk.eyJ1IjoiY3Jpc3RoaWFuMDAiLCJhIjoiY2tyZmd5MWU1M3prcDJvbjNlMjBqM3ozdyJ9.VtY6dHuMqZSNPlZeKHKPtQ';

            let bounds = new mapboxgl.LngLatBounds();
            let markers = [];

            let markerAct = new mapboxgl.Marker().setLngLat([position.coords.longitude, position.coords.latitude]);

            for (let l of lugares) {
                let marker = new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup()
                    .setHTML("<p:card style='width: 25em'><f:facet name='header'>" +
                        "<img alt='' style='width: 180px; height: 160px' src='/uploads/" + l.imagen + "'/></f:facet>" +
                        "<br/><f:facet name='title'><strong>" + l.nombre + "</strong></f:facet>" +
                        "<f:facet name='raiting'><p:rating value='" + l.raiting + "' readonly='true'/></f:facet>" +
                        "<p>" + l.descripcion + "</p>" +
                        "<f:facet name='footer'><a href='/detalleLugar.xhtml?lugar=" + l.id + "'>Ver detalles</a></f:facet>" +
                        "</p:card>"
                    ))
                console.log(markerAct.getLngLat().distanceTo(marker.getLngLat()))

                if (markerAct.getLngLat().distanceTo(marker.getLngLat()) < 8000) {
                    markers.push(marker);
                    bounds.extend([l.lng, l.lat]);
                }
            }
            if (bounds.isEmpty()) {
                var map = new mapboxgl.Map({
                    container: 'map',
                    style: 'mapbox://styles/mapbox/streets-v11',
                    center: [-72.309, 4.473],
                    zoom: 5
                });
            } else {
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
        })
    }
}