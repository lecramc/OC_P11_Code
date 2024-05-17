import {
  NearestHospitalEntity,
  SearchNearestHospitalEntity,
} from "./search-nearest-hospital.entity";
import { SearchNearestHospitalInterface } from "./search-nearest-hospital.interface";

export class GetNearestHospitalUseCase {
  constructor(
    private searchHospitalInterface: SearchNearestHospitalInterface
  ) {}

  async execute(
    research: SearchNearestHospitalEntity
  ): Promise<NearestHospitalEntity> {
    return this.searchHospitalInterface.getNearestHospital(research);
  }
}
