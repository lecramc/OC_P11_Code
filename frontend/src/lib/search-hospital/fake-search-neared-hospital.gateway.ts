// fake-search-nearest-hospital.gateway.ts
import { SearchNearestHospitalInterface } from "./search-nearest-hospital.interface";
import {
  NearestHospitalEntity,
  SearchNearestHospitalEntity,
} from "./search-nearest-hospital.entity";

export class FakeSearchNearestHospitalGateway
  implements SearchNearestHospitalInterface
{
  private hospitals: {
    id: number;
    name: string;
    address1: string;
    address2: string;
    address3: string;
    city: string;
    postCode: string;
    latitude: number;
    longitude: number;
    availableBeds: number;
    specialities: { id: number; name: string }[];
  }[] = [
    {
      id: 1,
      name: "General Hospital",
      address1: "123 Main St",
      address2: "",
      address3: "",
      city: "New York",
      postCode: "10001",
      latitude: 40.712776,
      longitude: -74.005974,
      availableBeds: 50,
      specialities: [
        { id: 1, name: "Cardiology" },
        { id: 2, name: "Neurology" },
      ],
    },
    {
      id: 2,
      name: "Community Hospital",
      address1: "456 Maple Ave",
      address2: "",
      address3: "",
      city: "New York",
      postCode: "10002",
      latitude: 40.73061,
      longitude: -73.935242,
      availableBeds: 30,
      specialities: [
        { id: 3, name: "Orthopedics" },
        { id: 1, name: "Cardiology" },
      ],
    },
    {
      id: 3,
      name: "City Hospital",
      address1: "789 Broadway",
      address2: "",
      address3: "",
      city: "New York",
      postCode: "10003",
      latitude: 40.712217,
      longitude: -74.016058,
      availableBeds: 20,
      specialities: [
        { id: 2, name: "Neurology" },
        { id: 4, name: "Pediatrics" },
      ],
    },
  ];

  async getNearestHospital(
    research: SearchNearestHospitalEntity
  ): Promise<NearestHospitalEntity> {
    const { specialityId } = research;
    // Return the first hospital that has the requested specialityId
    const hospital = this.hospitals.find((h) =>
      h.specialities.some((s) => s.id === specialityId)
    );

    if (!hospital) {
      throw new Error("No hospital found");
    }

    return {
      id: hospital.id,
      name: hospital.name,
      address1: hospital.address1,
      address2: hospital.address2,
      address3: hospital.address3,
      city: hospital.city,
      postCode: hospital.postCode,
      latitude: hospital.latitude,
      longitude: hospital.longitude,
      availableBeds: hospital.availableBeds,
      specialities: hospital.specialities,
    };
  }
}
