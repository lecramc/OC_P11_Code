// useNearestHospital.ts
import { useState } from "react";
import { GetNearestHospitalUseCase } from "@/lib/search-hospital/get-search-nearest-hospital.usecase";
import {
  SearchNearestHospitalEntity,
  NearestHospitalEntity,
} from "@/lib/search-hospital/search-nearest-hospital.entity";
import { SearchNearestHospitalInterface } from "@/lib/search-hospital/search-nearest-hospital.interface";
import { HttpSearchNearestHospitalGateway } from "@/lib/search-hospital/http-search-nearest-hospital.gateway"; // Utiliser un service fake pour les tests
import { toast } from "sonner";

export const useNearestHospital = () => {
  const [nearestHospital, setNearestHospital] =
    useState<NearestHospitalEntity | null>(null);

  const hospitalService: SearchNearestHospitalInterface =
    new HttpSearchNearestHospitalGateway();
  const getNearestHospitalUseCase = new GetNearestHospitalUseCase(
    hospitalService
  );

  const searchNearestHospital = async (
    research: SearchNearestHospitalEntity
  ) => {
    const promise = async () => {
      const hospital = await getNearestHospitalUseCase.execute(research);
      setNearestHospital(hospital);
      return hospital;
    };

    toast.promise(promise(), {
      loading: "Patientez...",
      success: (data) => `Hopital le plus proche trouvé : ${data.name}`,
      error: "Erreur lors de la recherche",
    });
  };
  return {
    nearestHospital,
    searchNearestHospital,
  };
};
