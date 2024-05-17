import { SpecialityEntity } from "./speciality.entity";

export interface SpecialityInterface {
  getAllSpecilities(): Promise<SpecialityEntity[]>;
}
