import Map, {
  NavigationControl,
  GeolocateControl,
  ViewStateChangeEvent,
} from "react-map-gl";
import GeocoderControl from "./geocoder-control";
import { useState } from "react";
const TOKEN = import.meta.env.VITE_MAPBOX_KEY;

export type LocationType = {
  longitude: number;
  latitude: number;
};

type MapLocationProps = {
  onLocationChange?: (location: LocationType) => void;
};

export const MapLocation: React.FC<MapLocationProps> = ({
  onLocationChange,
}) => {
  const [viewState, setViewState] = useState({
    longitude: -0.094151,
    latitude: 51.509093,
    zoom: 6,
  });

  const handleMove = (evt: ViewStateChangeEvent) => {
    const { longitude, latitude } = evt.viewState;
    setViewState(evt.viewState);
    if (onLocationChange) {
      onLocationChange({ longitude, latitude });
    }
  };
  return (
    <Map
      {...viewState}
      onMove={handleMove}
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
