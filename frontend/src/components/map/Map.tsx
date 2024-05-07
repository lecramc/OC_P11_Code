import Map, { NavigationControl, GeolocateControl } from "react-map-gl";
import GeocoderControl from "./geocoder-control";
import { useState } from "react";
const TOKEN = import.meta.env.VITE_MAPBOX_KEY;

export const MapLocation = () => {
  const [viewState, setViewState] = useState({
    longitude: 2.333333,
    latitude: 48.866667,
    zoom: 3.5,
  });
  return (
    <Map
      {...viewState}
      onMove={(evt) => setViewState(evt.viewState)}
      style={{ width: 600, height: 400 }}
      mapStyle='mapbox://styles/mapbox/streets-v12'
      mapboxAccessToken={TOKEN}
      attributionControl={false}
    >
      <GeolocateControl position='top-left' trackUserLocation={true} />
      <NavigationControl position='top-left' />
      <GeocoderControl mapboxAccessToken={TOKEN} position='top-right' />
    </Map>
  );
};
