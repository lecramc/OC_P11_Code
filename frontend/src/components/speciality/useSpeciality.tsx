import { useState } from "react";
import { SpecialityInterface } from "@/lib/speciality/speciality.interface";
import { GetAllSpecialitiesUseCase } from "@/lib/speciality/get-all-specialities.usecase";
import { HttpSpecialityGateway } from "@/lib/speciality/http-speciality.gateway"; // ou FakeSpecialityService pour des tests locaux
import { SpecialityEntity } from "@/lib/speciality/speciality.entity";

export const useSpeciality = () => {
  const [specialities, setSpecialities] = useState<SpecialityEntity[]>([]);
  const specialityService: SpecialityInterface = new HttpSpecialityGateway(); // ou new FakeSpecialityService()
  const getAllSpecialitiesUseCase = new GetAllSpecialitiesUseCase(
    specialityService
  );

  const getSpecialities = async () => {
    const specialities = await getAllSpecialitiesUseCase.execute();
    setSpecialities(specialities);
  };

  return {
    getSpecialities,
    specialities,
  };
};
