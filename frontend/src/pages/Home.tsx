import { SelectSpeciality } from "@/components/speciality/select-speciality";
import { Button } from "@/components/ui/button";
import { LocationType, MapLocation } from "@/components/map/Map";
import { useNearestHospital } from "@/components/search/useNearestHospital";
import { useState } from "react";
import { SearchNearestHospitalEntity } from "@/lib/search-hospital/search-nearest-hospital.entity";
import { Toaster } from "sonner";
export const Home = () => {
  const [coordinates, setCoordinates] = useState<LocationType>({
    longitude: 0,
    latitude: 0,
  });

  const [specialityId, setSpecialityId] = useState<number | null>(null);

  const { nearestHospital, searchNearestHospital } = useNearestHospital();

  const handleLocationChange = (newLocation: LocationType) => {
    setCoordinates(newLocation);
  };
  const handleSearchClick = () => {
    console.log(coordinates);
    if (
      coordinates.longitude &&
      coordinates.latitude &&
      specialityId !== null
    ) {
      const research: SearchNearestHospitalEntity = {
        longitude: coordinates.longitude,
        latitude: coordinates.latitude,
        specialityId,
      };
      searchNearestHospital(research);
    }
  };
  return (
    <div className='h-full'>
      <Toaster position='top-right' richColors />

      <div className='flex flex-col gap-5 w-fit my-0 mx-auto justify-center'>
        <MapLocation onLocationChange={handleLocationChange} />
        <SelectSpeciality setSpeciality={setSpecialityId} />
        <Button onClick={handleSearchClick}>Rechercher</Button>
      </div>
      {nearestHospital && (
        <div>
          <h3>Nearest Hospital</h3>
          <p>Name: {nearestHospital.name}</p>
          <p>Address: {nearestHospital.address1}</p>
          <p>Beds: {nearestHospital.availableBeds}</p>
        </div>
      )}
    </div>
  );
};
