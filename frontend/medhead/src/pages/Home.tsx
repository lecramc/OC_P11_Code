import { SelectSpeciality } from "@/components/custom/select-speciality";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export default function Home() {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "5px",
        width: "400px",
        margin: "0 auto",
        justifyContent: "center",
        height: "100%",
      }}
    >
      <Input placeholder='Localisation' />
      <SelectSpeciality />
      <Button>Rechercher</Button>
    </div>
  );
}
