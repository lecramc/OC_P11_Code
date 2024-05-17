import axios from "@/axios";
import { SpecialityEntity } from "./speciality.entity";
import { SpecialityInterface } from "./speciality.interface";
import { z } from "zod";

const getSpecialitiesDTO = z.array(
  z.object({
    id: z.number(),
    name: z.string(),
  })
);

export class HttpSpecialityGateway implements SpecialityInterface {
  async getAllSpecilities(): Promise<SpecialityEntity[]> {
    const unvalidatedResponse = await axios.get(`/specialities`);
    const result = getSpecialitiesDTO.safeParse(unvalidatedResponse.data);
    if (result.success) {
      return result.data;
    }
    throw result.error;
  }
}
