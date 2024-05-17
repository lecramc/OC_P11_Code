// get-all-specialities.usecase.ts
import { SpecialityEntity } from "./speciality.entity";
import { SpecialityInterface } from "./speciality.interface";

export class GetAllSpecialitiesUseCase {
  constructor(private specialityInterface: SpecialityInterface) {}

  async execute(): Promise<SpecialityEntity[]> {
    return this.specialityInterface.getAllSpecilities();
  }
}
