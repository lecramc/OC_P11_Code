import axios from "axios";
import { useState } from "react";
import { z } from "zod";
const getSpecialitiesDTO = z.array(
  z.object({
    id: z.number(),
    name: z.string(),
  })
);

export const GetAllSpecialities = () => {
  const [specialities, setSpecialities] = useState<
    {
      id: number;
      name: string;
    }[]
  >();
  const getSpecialities = async () => {
    await axios
      .get(`${import.meta.env.VITE_API_URL}/specialities`)
      .then((response) => {
        const result = getSpecialitiesDTO.safeParse(response.data);
        if (result.success) setSpecialities(result.data);

        throw result.error;
      });
  };

  return { getSpecialities, specialities };
};
