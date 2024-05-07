import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export function SelectSpeciality() {
  return (
    <Select>
      <SelectTrigger>
        <SelectValue placeholder='Selectionner une spécialité' />
      </SelectTrigger>
      <SelectContent>
        <SelectGroup>
          <SelectItem value='cardio'>Cardiologie</SelectItem>
          <SelectItem value='immu'>Immunologie</SelectItem>
          <SelectItem value='neuro'>Neuropathologie</SelectItem>
          <SelectItem value='diag'>Diagnostic</SelectItem>
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}
