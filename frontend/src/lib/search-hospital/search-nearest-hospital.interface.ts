import {
  NearestHospitalEntity,
  SearchNearestHospitalEntity,
} from "./search-nearest-hospital.entity";

export interface SearchNearestHospitalInterface {
  getNearestHospital(
    research: SearchNearestHospitalEntity
  ): Promise<NearestHospitalEntity>;
}
