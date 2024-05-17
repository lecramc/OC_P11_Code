import {
  SearchNearestHospitalEntity,
  NearestHospitalEntity,
} from "./search-nearest-hospital.entity";
import { SearchNearestHospitalInterface } from "./search-nearest-hospital.interface";

export class FakeSearchNearestHospitalGateway
  implements SearchNearestHospitalInterface
{
  private hospitals: {
    id: number;
    name: string;
    address: string;
    availableBeds: number;
    longitude: number;
    latitude: number;
  }[] = [
    {
      id: 1,
      name: "General Hospital",
      address: "123 Main St",
      availableBeds: 50,
      latitude: -74.005974,
      longitude: 40.712776,
    },
    {
      id: 2,
      name: "Community Hospital",
      address: "456 Maple Ave",
      availableBeds: 30,
      longitude: 40.73061,
      latitude: -73.935242,
    },
    {
      id: 3,
      name: "City Hospital",
      address: "789 Broadway",
      availableBeds: 20,
      longitude: 40.712217,
      latitude: -74.016058,
    },
  ];

  private calculateDistance(
    lat1: number,
    lon1: number,
    lat2: number,
    lon2: number
  ): number {
    const p = 0.017453292519943295; // Math.PI / 180
    const c = Math.cos;
    const a =
      0.5 -
      c((lat2 - lat1) * p) / 2 +
      (c(lat1 * p) * c(lat2 * p) * (1 - c((lon2 - lon1) * p))) / 2;
    return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
  }

  async getNearestHospital(
    research: SearchNearestHospitalEntity
  ): Promise<NearestHospitalEntity> {
    const researchLat = research.latitude;
    const researchLon = research.longitude;

    let nearestHospital = this.hospitals[0];
    let shortestDistance = this.calculateDistance(
      researchLat,
      researchLon,
      nearestHospital.latitude,
      nearestHospital.longitude
    );

    for (const hospital of this.hospitals) {
      const distance = this.calculateDistance(
        researchLat,
        researchLon,
        hospital.latitude,
        hospital.longitude
      );
      if (distance < shortestDistance) {
        nearestHospital = hospital;
        shortestDistance = distance;
      }
    }

    return nearestHospital;
  }
}
