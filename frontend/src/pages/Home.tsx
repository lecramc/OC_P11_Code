import { SelectSpeciality } from "@/components/custom/select-speciality";
import { Button } from "@/components/ui/button";
import { MapLocation } from "@/components/map/Map";
export default function Home() {
  return (
    <div className='h-full'>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "5px",
          width: "400px",
          margin: "0 auto",
          justifyContent: "center",
        }}
      >
        {/* <MapLocation /> */}
        <SelectSpeciality />
        <Button>Rechercher</Button>
      </div>
    </div>
  );
}
