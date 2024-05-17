import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { useEffect } from "react";
import { useSpeciality } from "./useSpeciality";
interface SelectSpecialityProps {
  setSpeciality: (id: number | null) => void;
}
export const SelectSpeciality: React.FC<SelectSpecialityProps> = ({
  setSpeciality,
}) => {
  const { getSpecialities, specialities } = useSpeciality();
  useEffect(() => {
    getSpecialities();
  }, []);

  const handleSpecialityChange = (value: string) => {
    const id = Number(value);
    setSpeciality(id);
  };

  return (
    <Select onValueChange={handleSpecialityChange}>
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
};
