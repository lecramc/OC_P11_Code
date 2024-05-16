import { SelectSpeciality } from "@/components/custom/select-speciality";
import { Button } from "@/components/ui/button";
import { MapLocation } from "@/components/map/Map";
export const Home = () => {
  return (
    <div className='h-full'>
      <div className='flex flex-col gap-5 w-fit my-0 mx-auto justify-center'>
        <MapLocation />
        <SelectSpeciality />
        <Button>Rechercher</Button>
      </div>
    </div>
  );
};
