import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { GetAllSpecialities } from "@/lib/speciality/get-all-specialities";
import { useEffect } from "react";

export function SelectSpeciality() {
  const { getSpecialities, specialities } = GetAllSpecialities();

  useEffect(() => {
    getSpecialities();
  }, []);

  return (
    <Select>
      <SelectTrigger>
        <SelectValue placeholder='Selectionner une spécialité' />
      </SelectTrigger>
      <SelectContent>
        <SelectGroup>
          {specialities?.map((spe: { id: number; name: string }) => (
            <SelectItem key={spe.id} value={String(spe.id)}>
              {spe.name}
            </SelectItem>
          ))}
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}
