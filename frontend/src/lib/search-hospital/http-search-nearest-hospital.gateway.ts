import axios from "@/axios";
import { z } from "zod";
import { SearchNearestHospitalInterface } from "./search-nearest-hospital.interface";
import {
  NearestHospitalEntity,
  SearchNearestHospitalEntity,
} from "./search-nearest-hospital.entity";

const getNearestHospitalDto = z.object({
  id: z.number(),
  name: z.string(),
  address1: z.string(),
  address2: z.string(),
  address3: z.string(),
  city: z.string(),
  postCode: z.string(),
  availableBeds: z.number(),
  latitude: z.number(),
  longitude: z.number(),
  specialities: z.array(z.object({ id: z.number(), name: z.string() })),
});

export class HttpSearchNearestHospitalGateway
  implements SearchNearestHospitalInterface
{
  async getNearestHospital(
    coordinates: SearchNearestHospitalEntity
  ): Promise<NearestHospitalEntity> {
    const { specialityId, latitude, longitude } = coordinates;
    const unvalidatedResponse = await axios.get(
      `${import.meta.env.VITE_API_URL}/hospitals/search`,
      {
        params: {
          speciality: specialityId,
          latitude,
          longitude,
        },
      }
    );
    const result = getNearestHospitalDto.safeParse(unvalidatedResponse.data);
    if (result.success) {
      return result.data;
    }
    throw result.error;
  }
}
